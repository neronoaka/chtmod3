package chtmod.Electricity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
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

public class BlockInsulator extends Block {
	public static final PropertyDirection PROPERTYFACING = PropertyDirection.create("facing",
			EnumFacing.Plane.HORIZONTAL);
	public static final PropertyInteger PROPERTYSTYLE = PropertyInteger.create("style", 0, 3);

	public BlockInsulator(String arg0) {
		super(Material.ROCK);
		this.setUnlocalizedName(arg0);
		this.setRegistryName(arg0);
		this.setCreativeTab(chtmod.CustomCreativeTabs.electricityTab);
		this.setHardness(1);
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.SOLID;
	}

	@Override
	public boolean isFullBlock(IBlockState iBlockState) {
		return false;
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
	public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(PROPERTYFACING);
		switch (state.getValue(PROPERTYSTYLE)) {
		case 0:
			return new AxisAlignedBB(5 / 16F, 7 / 16F, 5 / 16F, 11 / 16F, 1, 11 / 16F);
		case 1:
			switch (facing) {
			case EAST:
				return new AxisAlignedBB(10 / 16f, 0, 5 / 16f, 1, 1, 11 / 16f);
			case NORTH:
				return new AxisAlignedBB(5 / 16f, 0, 0, 11 / 16f, 1, 5 / 16f);
			case SOUTH:
				return new AxisAlignedBB(5 / 16f, 0, 11 / 16f, 11 / 16f, 1, 1);
			case WEST:
				return new AxisAlignedBB(0, 0, 5 / 16f, 6 / 16f, 1, 11 / 16f);
			default:
				break;
			}
		case 2:
			switch (facing) {
			case EAST:
				return new AxisAlignedBB(0, 4 / 16f, 4 / 16f, 1, 12 / 16f, 12 / 16f);
			case NORTH:
				return new AxisAlignedBB(4 / 16f, 4 / 16f, 0, 12 / 16f, 12 / 16f, 1);
			case SOUTH:
				return new AxisAlignedBB(4 / 16f, 4 / 16f, 0, 12 / 16f, 12 / 16f, 1);
			case WEST:
				return new AxisAlignedBB(0, 4 / 16f, 4 / 16f, 1, 12 / 16f, 12 / 16f);
			default:
				break;
			}
		case 3:
			switch (facing) {
			case EAST:
				return new AxisAlignedBB(7 / 16f, 0, 5 / 16f, 1, 1, 11 / 16f);
			case NORTH:
				return new AxisAlignedBB(5 / 16f, 0, 0, 11 / 16f, 1, 9 / 16f);
			case SOUTH:
				return new AxisAlignedBB(5 / 16f, 0, 7 / 16f, 11 / 16f, 1, 1);
			case WEST:
				return new AxisAlignedBB(0, 0, 5 / 16f, 9 / 16f, 1, 11 / 16f);
			default:
				break;
			}
		default:
			return FULL_BLOCK_AABB;
		}
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		int meta = state.getValue(PROPERTYSTYLE);
		return new ItemStack(StartupCommon.iib, 1, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
		for (int i = 0; i < 4; i++)
			list.add(new ItemStack(itemIn, 1, i));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing facing = EnumFacing.getHorizontal(meta & 0x3);
		int style = (meta & 0x0c) >> 2;
		return this.getDefaultState().withProperty(PROPERTYSTYLE, style).withProperty(PROPERTYFACING, facing);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int facingbits = state.getValue(PROPERTYFACING).getHorizontalIndex();
		int style = state.getValue(PROPERTYSTYLE) << 2;
		return facingbits | style;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { PROPERTYFACING, PROPERTYSTYLE });
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer) {
		EnumFacing enumfacing = (placer == null) ? EnumFacing.NORTH : EnumFacing.fromAngle(placer.rotationYaw);
		return this.getDefaultState().withProperty(PROPERTYFACING, enumfacing).withProperty(PROPERTYSTYLE, meta);
	}
}