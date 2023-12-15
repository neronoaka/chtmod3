package chtmod.RoadLines;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockRoadLine extends Block {
	public static final PropertyDirection PROPERTYFACING = PropertyDirection.create("facing",
			EnumFacing.Plane.HORIZONTAL);
	public static final PropertyEnum PROPERTYANGLE = PropertyEnum.create("angle", EnumAngle.class);

	public  BlockRoadLine(String arg0) {
		super(Material.ROCK);
		this.setCreativeTab(chtmod.CustomCreativeTabs.lineTab);
		this.setUnlocalizedName("RoadLine" + arg0);
		this.setRegistryName("RoadLine" + arg0);
		this.setHardness(0.5F);
		this.setLightLevel(0.5F);
	}

	@Override
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing facing) {
		Block blockDown = worldIn.getBlockState(pos.down()).getBlock();
		if (blockDown.getClass() == this.getClass())
			return false;
		else
			return true;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean isOpaqueCube(IBlockState iBlockState) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState iBlockState) {
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState iBlockState) {
		return false;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0, 0, 0, 1, 2 / 16F, 1);
	}

	@Override
	public int damageDropped(IBlockState state) {
		EnumAngle enumAngle = (EnumAngle) state.getValue(PROPERTYANGLE);
		return enumAngle.getMetadata();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
		EnumAngle[] allAngles = EnumAngle.values();
		for (EnumAngle angle : allAngles) {
			list.add(new ItemStack(itemIn, 1, angle.getMetadata()));
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing facing = EnumFacing.getHorizontal(meta);
		int anglebits = (meta & 0x0c) >> 2;
		EnumAngle angle = EnumAngle.byMetadata(anglebits);
		return this.getDefaultState().withProperty(PROPERTYANGLE, angle).withProperty(PROPERTYFACING, facing);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumFacing facing = (EnumFacing) state.getValue(PROPERTYFACING);
		EnumAngle angle = (EnumAngle) state.getValue(PROPERTYANGLE);
		int facingbits = facing.getHorizontalIndex();
		int anglebits = angle.getMetadata() << 2;
		return facingbits | anglebits;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { PROPERTYFACING, PROPERTYANGLE });
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer) {
		EnumAngle angle = EnumAngle.byMetadata(meta);
		EnumFacing enumfacing = (placer == null) ? EnumFacing.NORTH : EnumFacing.fromAngle(placer.rotationYaw);
		return this.getDefaultState().withProperty(PROPERTYFACING, enumfacing).withProperty(PROPERTYANGLE, angle);
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block block) {
		if (worldIn.getBlockState(pos.down()).getBlock() == Blocks.AIR)
			worldIn.setBlockToAir(pos);
	}
}