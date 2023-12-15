package chtmod.BlockRoad;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBitumen extends Block {
	private int style;

	public BlockBitumen(String arg0, int arg1) {
		super(Material.ROCK);
		this.setUnlocalizedName(arg0);
		this.setRegistryName(arg0);
		this.setCreativeTab(chtmod.CustomCreativeTabs.blockTab);
		this.setHardness(1.5F);
		this.setTickRandomly(false);
		style = arg1;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.SOLID;
	}

	@Override
	public boolean isFullBlock(IBlockState iBlockState) {
		return true;
	}

	@Override
	public boolean isOpaqueCube(IBlockState iBlockState) {
		return true;
	}

	@Override
	public boolean isFullCube(IBlockState iBlockState) {
		return true;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random ran) {
		boolean b1 = ran.nextInt() % 2 == 0;
		if (style != 1 && b1) {
			IBlockState b = StartupCommon.blocks1[1].getDefaultState();
			worldIn.setBlockState(pos, b);
		}
	}
}