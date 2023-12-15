package chtmod.WorldTools;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class WTCur extends WTitem {
	public WTCur() {
		this.setUnlocalizedName("WTCur");
		this.setRegistryName("WTCur");
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		getSelecting(playerIn);
		ITextComponent itc;
		String dispName = stack.getDisplayName().split(" ")[0];
		if (!worldIn.isRemote) {
			if (getSelecting(playerIn)) {
				setSelecting(false, playerIn);
				setPos1(pos, playerIn);
				playerIn.addChatMessage(new TextComponentString(I18n.format("gui.1stselect", new Object[0]) + "("
						+ pos.getX() + "," + pos.getY() + "," + pos.getZ() + ")"));
				stack.setStackDisplayName(dispName);
			} else {
				setSelecting(true, playerIn);
				setPos2(pos, playerIn);
				String secondselect = I18n.format("gui.2ndselect", new Object[0]) + "(" + pos.getX() + "," + pos.getY()
						+ "," + pos.getZ() + ")";
				playerIn.addChatMessage(new TextComponentString(secondselect));
				stack.setStackDisplayName(dispName + " " + secondselect);
			}
		}
		return EnumActionResult.SUCCESS;
	}
}