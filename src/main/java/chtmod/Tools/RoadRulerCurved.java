package chtmod.Tools;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * This part of code was copied from Minecraft-Transit-Railway mod
 * 
 * https://github.com/jonafanho/Minecraft-Transit-Railway
 */
public class RoadRulerCurved extends RoadRuler {
	public static final PropertyInteger ROTATION = PropertyInteger.create("rotation", 0, 11);

	public RoadRulerCurved(String arg0) {
		super(arg0);
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(StartupCommon.pc);
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
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, @Nullable ItemStack stack, EnumFacing facing, float x, float y, float z) {
		try {
			breakOtherBlocks(worldIn, pos, Block.getBlockFromItem(stack.getItem()));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			if (worldIn.isRemote)
				playerIn.addChatMessage(new TextComponentString("Get Item Error!"));
		}
		return true;
	}

	public void breakOtherBlocks(World worldIn, BlockPos pos, Block b) {
		worldIn.setBlockState(pos, b.getDefaultState());
		BlockPos pos1 = pos.add(1, 0, 0), pos2 = pos.add(0, 0, 1), pos3 = pos.add(-1, 0, 0), pos4 = pos.add(0, 0, -1);
		BlockPos pos5 = pos.add(1, 0, 1), pos6 = pos.add(1, 0, -1), pos7 = pos.add(-1, 0, 1), pos8 = pos.add(-1, 0, -1);
		Block block1 = worldIn.getBlockState(pos1).getBlock();
		Block block2 = worldIn.getBlockState(pos2).getBlock();
		Block block3 = worldIn.getBlockState(pos3).getBlock();
		Block block4 = worldIn.getBlockState(pos4).getBlock();
		Block block5 = worldIn.getBlockState(pos5).getBlock();
		Block block6 = worldIn.getBlockState(pos6).getBlock();
		Block block7 = worldIn.getBlockState(pos7).getBlock();
		Block block8 = worldIn.getBlockState(pos8).getBlock();
		breakOtherBlocks2(worldIn, block1, pos1, b);
		breakOtherBlocks2(worldIn, block2, pos2, b);
		breakOtherBlocks2(worldIn, block3, pos3, b);
		breakOtherBlocks2(worldIn, block4, pos4, b);
		breakOtherBlocks2(worldIn, block5, pos5, b);
		breakOtherBlocks2(worldIn, block6, pos6, b);
		breakOtherBlocks2(worldIn, block7, pos7, b);
		breakOtherBlocks2(worldIn, block8, pos8, b);
	}

	private void breakOtherBlocks2(World worldIn, Block block, BlockPos pos, Block b) {
		if (block instanceof RoadRulerCurved)
			breakOtherBlocks(worldIn, pos, b);
	}
}