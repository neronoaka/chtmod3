package chtmod.WorldTools;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class WTPst extends WTitem {
	public WTPst() {
		this.setUnlocalizedName("WTPst");
		this.setRegistryName("WTPst");
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			if (getBlocks(playerIn) != null) {
				int area = getBlocks(playerIn).length * getBlocks(playerIn)[0].length
						* getBlocks(playerIn)[0][0].length;
				for (int x = 0; x < getBlocks(playerIn).length; x++) {
					for (int y = 0; y < getBlocks(playerIn)[0].length; y++) {
						for (int z = 0; z < getBlocks(playerIn)[0][0].length; z++) {
							worldIn.setBlockState(new BlockPos(x, y, z).add(pos).subtract(getOffset(playerIn)),
									getBlocks(playerIn)[x][y][z]);
						}
					}
				}
				playerIn.addChatMessage(
						new TextComponentString(area + "Blocks" + I18n.format("gui.pasted", new Object[0]) + "("
								+ pos.getX() + "," + pos.getY() + "," + pos.getZ() + ")"));
			} else {
				playerIn.addChatMessage(new TextComponentString(I18n.format("gui.noselect", new Object[0])));
			}
		}
		return EnumActionResult.SUCCESS;
	}
}