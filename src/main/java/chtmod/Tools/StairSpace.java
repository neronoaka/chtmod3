package chtmod.Tools;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StairSpace extends Item {
	public StairSpace(String arg1) {
		this.setMaxStackSize(1);
		this.setUnlocalizedName(arg1);
		this.setRegistryName(arg1);
		this.setCreativeTab(chtmod.CustomCreativeTabs.toolTab);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing f, float hitX, float hitY, float hitZ) {
		int h = 9;
		Block stage = Blocks.DOUBLE_STONE_SLAB;
		IBlockState s = Blocks.STONE.getDefaultState();
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		EnumFacing facing1 = playerIn.getHorizontalFacing(), facing = EnumFacing.WEST;
		switch (facing1) {
		case EAST:
			facing = EnumFacing.NORTH;
			break;
		case SOUTH:
			facing = EnumFacing.EAST;
			break;
		case WEST:
			facing = EnumFacing.SOUTH;
			break;
		default:
			break;
		}
		for (int i = 0; i < 4; i++) {
			worldIn.setBlockState(pos.offset(facing, i), s);
			worldIn.setBlockState(pos.offset(facing, i).offset(EnumFacing.UP, 10), s);
			for (int j = 1; j < 10; j++)
				worldIn.setBlockToAir(pos.offset(facing, i).offset(EnumFacing.UP, j));
		}
		return EnumActionResult.SUCCESS;
	}
}
