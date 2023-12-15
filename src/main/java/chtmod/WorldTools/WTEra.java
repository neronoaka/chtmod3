package chtmod.WorldTools;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class WTEra extends WTitem {
	public WTEra() {
		this.setUnlocalizedName("WTEra");
		this.setRegistryName("WTEra");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		if (!worldIn.isRemote) {
			if ((getPos1(playerIn) != null) || (getPos2(playerIn) != null)) {
				BlockPos pos1 = getPos1(playerIn);
				BlockPos pos2 = getPos2(playerIn);
				BlockPos newPos1 = pos1;
				BlockPos newPos2 = pos2;
				if (pos1.getX() > pos2.getX()) {
					newPos1 = new BlockPos(pos2.getX(), newPos1.getY(), newPos1.getZ());
					newPos2 = new BlockPos(pos1.getX(), newPos2.getY(), newPos2.getZ());
				}
				if (pos1.getY() > pos2.getY()) {
					newPos1 = new BlockPos(newPos1.getX(), pos2.getY(), newPos1.getZ());
					newPos2 = new BlockPos(newPos2.getX(), pos1.getY(), newPos2.getZ());
				}
				if (pos1.getZ() > pos2.getZ()) {
					newPos1 = new BlockPos(newPos1.getX(), newPos1.getY(), pos2.getZ());
					newPos2 = new BlockPos(newPos2.getX(), newPos2.getY(), pos1.getZ());
				}
				int area = (newPos2.getX() - newPos1.getX() + 1) * (newPos2.getY() - newPos1.getY() + 1)
						* (newPos2.getZ() - newPos1.getZ() + 1);
				if (area < 32768) {
					for (int x = newPos1.getX(); x <= newPos2.getX(); x++) {
						for (int y = newPos1.getY(); y <= newPos2.getY(); y++) {
							for (int z = newPos1.getZ(); z <= newPos2.getZ(); z++) {
								worldIn.setBlockToAir(new BlockPos(x, y, z));
							}
						}
					}
					playerIn.addChatMessage(
							new TextComponentString(area + "Blocks" + I18n.format("gui.erased", new Object[0])));
				} else {
					playerIn.addChatMessage(
							new TextComponentString(I18n.format("gui.tomanyblocks", new Object[0]) + " 32768<" + area));
				}
			} else {
				playerIn.addChatMessage(new TextComponentString(I18n.format("gui.noselect", new Object[0])));
			}
		}
		return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
	}
}