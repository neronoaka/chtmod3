package chtmod.Tools;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SubwayTurn extends Item {
	public SubwayTurn(String arg1) {
		this.setMaxStackSize(1);
		this.setUnlocalizedName(arg1);
		this.setRegistryName(arg1);
		this.hasSubtypes = true;
		this.setCreativeTab(chtmod.CustomCreativeTabs.toolTab);
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		for (int i = 0; i < 2; i++)
			subItems.add(new ItemStack(itemIn, 1, i));
	}

	@Override
	public int getMetadata(int metadata) {
		return metadata;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + stack.getMetadata();
	}

	private void genBlock(World w, int x, int y, int z, Block b) {
		w.setBlockState(new BlockPos(x, y, z), b.getDefaultState());
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		int len1 = 23, len2 = 21, len3 = 32;
		Block stage = Blocks.DOUBLE_STONE_SLAB;
		Block s = Blocks.STONE;
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		EnumFacing facing1 = playerIn.getHorizontalFacing(), left = EnumFacing.WEST, right = EnumFacing.EAST;
		switch (facing1) {
		case EAST:
			left = EnumFacing.NORTH;
			right = EnumFacing.SOUTH;
			break;
		case SOUTH:
			left = EnumFacing.EAST;
			right = EnumFacing.WEST;
			break;
		case WEST:
			left = EnumFacing.SOUTH;
			right = EnumFacing.NORTH;
			break;
		default:
			break;
		}
		EnumFacing turn = stack.getMetadata() == 0 ? left : right;
		for (int i = 0; i < len1; i++)
			for (int j = 0; j <= 9 + i; j++)
				worldIn.setBlockToAir(pos.offset(facing1, i).offset(turn, j));
		for (int k = 0; k < len2; k++)
			for (int l = 0; l < len3; l++)
				worldIn.setBlockToAir(pos.offset(facing1, k + len1).offset(turn, k + 1).offset(turn, l));
		return EnumActionResult.SUCCESS;
	}
}
