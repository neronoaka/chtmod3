package chtmod.TrafficLight;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
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

public class BlockTrafficLight extends Block {
	private int styleID;
	public static final PropertyDirection PROPERTYFACING = PropertyDirection.create("facing",
			EnumFacing.Plane.HORIZONTAL);
	public static final PropertyEnum PROPERTYCOLOUR = PropertyEnum.create("colour", EnumColour.class);

	public BlockTrafficLight(String arg0, int arg1) {
		super(Material.ROCK);
		this.setCreativeTab(chtmod.CustomCreativeTabs.trafficTab);
		this.setRegistryName("TrafficLight" + arg0);
		this.setUnlocalizedName("TrafficLight" + arg0);
		this.setHardness(1);
		styleID = arg1;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public int getLightValue(IBlockState state) {
		EnumColour color = (EnumColour) state.getValue(PROPERTYCOLOUR);
		if (color == EnumColour.EMPTY)
			return 0;
		else
			return 15;
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
		return true;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(PROPERTYFACING);
		int x = 16, z = 16, top = 0, height = 16;
		switch (styleID) {
		case 1:
			z = 13;
			break;
		case 2:
			x = 12;
			top = -14;
			height = 44;
			break;
		case 3:
			x = 14;
			z = 12;
			top = -10;
			height = 36;
			break;
		case 4:
			x = 38;
			z = 12;
			top = 1;
			height = 14;
			break;
		case 5:
			z = 21;
			height = 32;
			break;
		case 6:
			z = 21;
			x = 20;
			height = 32;
			break;
		case 7:
			top = -16;
			height = 48;
			break;
		case 8:
			x = 48;
			break;
		case 9:
			x = 48;
			z = 12;
			break;
		case 10:
			x = 32;
			break;
		case 11:
			height = 32;
			z = 15;
			break;
		}
		return chtmod.Helper.AABB.RotationBox(facing, x, z, top, height);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing facing = EnumFacing.getHorizontal(meta);
		int colourbits = (meta & 0x0c) >> 2;
		EnumColour colour = EnumColour.byMetadata(colourbits);
		return this.getDefaultState().withProperty(PROPERTYCOLOUR, colour).withProperty(PROPERTYFACING, facing);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		EnumFacing facing = (EnumFacing) state.getValue(PROPERTYFACING);
		EnumColour colour = (EnumColour) state.getValue(PROPERTYCOLOUR);
		int facingbits = facing.getHorizontalIndex();
		int colourbits = colour.getMetadata() << 2;
		return facingbits | colourbits;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { PROPERTYFACING, PROPERTYCOLOUR });
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer) {
		EnumColour colour = EnumColour.byMetadata(meta);
		EnumFacing enumfacing = (placer == null) ? EnumFacing.NORTH : EnumFacing.fromAngle(placer.rotationYaw);
		return this.getDefaultState().withProperty(PROPERTYFACING, enumfacing).withProperty(PROPERTYCOLOUR, colour);
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityTrafficLightEntity();
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
}