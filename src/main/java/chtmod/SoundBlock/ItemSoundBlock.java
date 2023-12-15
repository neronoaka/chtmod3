package chtmod.SoundBlock;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemSoundBlock extends Item {

	public ItemSoundBlock() {
		this.setMaxStackSize(1);
		this.setUnlocalizedName("ToolSoundBlock");
		this.setRegistryName("ToolSoundBlock");
		this.setCreativeTab(chtmod.CustomCreativeTabs.miscellaneousTab);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		if (!worldIn.isRemote)
			Minecraft.getMinecraft().displayGuiScreen(new GUISetSounds(itemStackIn));
		return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (stack.hasTagCompound() && worldIn.getBlockState(pos).getBlock() instanceof BlockSound) {
			String place = stack.getTagCompound().getString("PLACE");
			int id = stack.getTagCompound().getInteger("SOUNDID");
			try {
				TileEntitySoundEntity tes = (TileEntitySoundEntity) worldIn.getTileEntity(pos);
				tes.place = place;
				tes.soundID = id;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			if (worldIn.isRemote)
				playerIn.addChatComponentMessage(new TextComponentString(
						I18n.format("gui.setsuccess", new Object[0]) + ",PLACE:" + place + ",ID:" + id));
		} else {
			if (worldIn.isRemote)
				playerIn.addChatComponentMessage(
						new TextComponentString(I18n.format("gui.soundnotset", new Object[0])));
		}
		return EnumActionResult.SUCCESS;
	}
}