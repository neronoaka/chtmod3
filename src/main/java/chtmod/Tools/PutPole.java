package chtmod.Tools;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PutPole extends Item {
	private Block block;
	private int len;

	public PutPole(int arg0, String arg1, String arg2) {
		this.setMaxStackSize(1);
		this.setUnlocalizedName(arg2);
		this.setRegistryName(arg2);
		this.setCreativeTab(chtmod.CustomCreativeTabs.toolTab);
		block = Block.getBlockFromName(arg1);
		len = arg0;
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		for (int y = 1; y <= len; y++)
			worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY() + y, pos.getZ()), block.getDefaultState());
		return EnumActionResult.SUCCESS;
	}
}