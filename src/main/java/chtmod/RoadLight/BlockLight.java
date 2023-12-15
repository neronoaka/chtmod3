package chtmod.RoadLight;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLight extends Block {
	public boolean connectedPole;
	protected int style;

	public BlockLight(String arg0, int arg1) {
		super(Material.ROCK);
		this.setUnlocalizedName(arg0);
		this.setRegistryName(arg0);
		this.setSoundType(SoundType.GLASS);
		this.setCreativeTab(chtmod.CustomCreativeTabs.blockTab);
		this.setHardness(1);
		this.setLightLevel(2);
		connectedPole = true;
		style = arg1;
	}

	public BlockLight(String arg0, int arg1, boolean arg2) {
		this(arg0, arg1);
		connectedPole = arg2;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch (style) {
		case 1:
			return new AxisAlignedBB(0, 4 / 16f, 0, 1, 11 / 16f, 1);
		case 2:
			return new AxisAlignedBB(-5 / 16f, -80 / 16f, -5 / 16f, 21 / 16f, 6 / 16f, 21 / 16f);
		case 3:
			return new AxisAlignedBB(-27 / 16f, 0, -27 / 16f, 43 / 16f, 80 / 16f, 43 / 16f);
		default:
			return FULL_BLOCK_AABB;
		}
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.SOLID;
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
}
