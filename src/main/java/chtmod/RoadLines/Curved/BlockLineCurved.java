package chtmod.RoadLines.Curved;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * This part of code was copied from Minecraft-Transit-Railway mod
 * 
 * https://github.com/jonafanho/Minecraft-Transit-Railway
 */
public class BlockLineCurved extends Block implements ITileEntityProvider {

	public static final PropertyInteger ROTATION = PropertyInteger.create("rotation", 0, 3);

	public BlockLineCurved() {
		super(Material.ROCK);
		setCreativeTab(null);
		this.setUnlocalizedName("RoadLineCurved");
		this.setRegistryName("RoadLineCurved");
		setHardness(0.5F);
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(StartupCommon.icl);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0, 0, 0, 1, 2 / 16F, 1);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(ROTATION, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(ROTATION);
	}

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { ROTATION });
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public EnumPushReaction getMobilityFlag(IBlockState state) {
		return EnumPushReaction.IGNORE;
	}

	private void change(World worldIn, BlockPos pos, int straight) {
		int direction = worldIn.getBlockState(pos).getValue(ROTATION);
		int x = 0, z = 0;
		switch (direction) {
		case 0:
			z = 1;
			break;
		case 1:
			x = 1;
			z = -1;
			break;
		case 2:
			x = 1;
			break;
		case 3:
			x = 1;
			z = 1;
			break;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityLineEntity();
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block block) {
		if (worldIn.getBlockState(pos.down()).getBlock() == Blocks.AIR)
			worldIn.setBlockToAir(pos);
	}
}