package chtmod.Electricity;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockElectricityPole extends Block {
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");
	public static final PropertyBool EAST = PropertyBool.create("east");

	protected BlockElectricityPole(String arg0) {
		super(Material.ROCK);
		this.setUnlocalizedName(arg0);
		this.setRegistryName(arg0);
		this.setCreativeTab(chtmod.CustomCreativeTabs.electricityTab);
		this.setHardness(2);
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
		return new AxisAlignedBB(0.25F, 0, 0.25F, 0.75F, 1, 0.75F);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		boolean n = (meta & 0x1) == 1;
		boolean s = (meta & 0x2) >> 1 == 1;
		boolean w = (meta & 0x4) >> 2 == 1;
		boolean e = (meta & 0x8) >> 3 == 1;
		return this.getDefaultState().withProperty(NORTH, n).withProperty(SOUTH, s).withProperty(WEST, w)
				.withProperty(EAST, e);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int n = state.getValue(NORTH) ? 1 : 0;
		int s = state.getValue(SOUTH) ? 1 : 0;
		int w = state.getValue(WEST) ? 1 : 0;
		int e = state.getValue(EAST) ? 1 : 0;
		return n | s << 1 | w << 2 | e << 3;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		boolean n = getBlockType(worldIn.getBlockState(pos.north()).getBlock());
		boolean s = getBlockType(worldIn.getBlockState(pos.south()).getBlock());
		boolean w = getBlockType(worldIn.getBlockState(pos.west()).getBlock());
		boolean e = getBlockType(worldIn.getBlockState(pos.east()).getBlock());
		return state.withProperty(NORTH, n).withProperty(SOUTH, s).withProperty(WEST, w).withProperty(EAST, e);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { NORTH, SOUTH, WEST, EAST });
	}

	private boolean getBlockType(Block b) {
		if (b instanceof BlockElectricityPoleHorizontal)
			return true;
		else if (b instanceof BlockElectricityLightPole)
			return false;
		else if (b instanceof BlockInsulator)
			return true;
		else
			return false;
	}
}