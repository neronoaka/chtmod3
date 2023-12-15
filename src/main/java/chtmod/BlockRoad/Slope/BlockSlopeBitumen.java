package chtmod.BlockRoad.Slope;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

import chtmod.BlockRoad.StartupCommon;

public class BlockSlopeBitumen extends Block {
	public static final PropertyDirection PROPERTYFACING = PropertyDirection.create("facing",
			EnumFacing.Plane.HORIZONTAL);
	public static final PropertyInteger PROPERTYLEVEL = PropertyInteger.create("level", 0, 4);
	public static final PropertyBool HEAD = PropertyBool.create("head");
	private int meta;

	public BlockSlopeBitumen(int arg0) {
		super(Material.ROCK);
		this.setCreativeTab(null);
		this.setUnlocalizedName("BlockSlopeBitumen" + arg0);
		this.setRegistryName("BlockSlopeBitumen" + arg0);
		this.setHardness(2);
		meta = arg0;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(StartupCommon.isb, 1, meta);
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
		IBlockState astate = state.getActualState(source, pos);
		int level = astate.getValue(PROPERTYLEVEL) + 1;
		return new AxisAlignedBB(0, 0, 0, 1, level / 5F, 1);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing facing = EnumFacing.getHorizontal(meta & 0x3);
		return this.getDefaultState().withProperty(PROPERTYFACING, facing).withProperty(PROPERTYLEVEL, 0)
				.withProperty(HEAD, (meta & 0x4) >> 2 == 1);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int head = state.getValue(HEAD) ? 1 << 2 : 0;
		return state.getValue(PROPERTYFACING).getHorizontalIndex() | head;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess access, BlockPos pos) {
		EnumFacing facing = state.getValue(PROPERTYFACING);
		BlockPos posHead = pos;
		for (int i = 0; i < 5; i++) {
			IBlockState state1 = access.getBlockState(pos.offset(facing, -i));
			if (state1.getBlock() instanceof BlockSlopeBitumen)
				if (state1.getValue(HEAD))
					return state.withProperty(PROPERTYLEVEL, i);
		}
		return state;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { PROPERTYFACING, PROPERTYLEVEL, HEAD });
	}

	@Override
	public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosion) {
		for (EnumFacing facing : EnumFacing.HORIZONTALS)
			for (int i = 0; i < 5; i++)
				worldIn.setBlockToAir(pos.offset(facing, i));
	}

	@Override
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		Delete(worldIn, pos, state.getActualState(worldIn, pos));
	}

	private void Delete(World world, BlockPos pos, IBlockState state) {
		int level = state.getValue(PROPERTYLEVEL);
		EnumFacing facing = state.getValue(PROPERTYFACING);
		BlockPos actuallypos = pos.offset(facing, -level);
		for (int i = 0; i < 5; i++) {
			BlockPos setpos = actuallypos.offset(facing, i);
			if (world.getBlockState(setpos).getBlock() instanceof BlockSlopeBitumen)
				world.setBlockToAir(setpos);
		}
	}
}
