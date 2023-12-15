package chtmod.TrafficLight;

import java.util.List;

import chtmod.Helper.MathTools;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
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
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;

public class ItemCycle extends Item {

	public ItemCycle() {
		this.setMaxStackSize(1);
		this.setUnlocalizedName("ItemCycle");
		this.setRegistryName("ItemCycle");
		this.hasSubtypes = true;
		this.setCreativeTab(chtmod.CustomCreativeTabs.trafficTab);
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		for (int i = 0; i < 3; i++)
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

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		if (stack.getMetadata() == 0)
			if (!worldIn.isRemote)
				Minecraft.getMinecraft().displayGuiScreen(new GUISetCycle(stack));
		return new ActionResult(EnumActionResult.SUCCESS, stack);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.getBlockState(pos).getBlock() instanceof BlockTrafficLight) {
			try {
				TileEntityTrafficLightEntity tetle = (TileEntityTrafficLightEntity) worldIn.getTileEntity(pos);
				if (stack.hasTagCompound() && stack.getMetadata() == 0) {
					NBTTagCompound tag = stack.getTagCompound();
					tetle.times = tag.getIntArray("TIME");
					tetle.colors = tag.getIntArray("COLOR");
					tetle.maximumValue = tag.getInteger("MAX");
					tetle.start = true;
					if (worldIn.isRemote)
						playerIn.addChatComponentMessage(
								new TextComponentString(I18n.format("gui.setsuccess", new Object[0])));
				} else if (stack.getMetadata() == 1) {
					tetle.times = new int[0];
					tetle.colors = new int[0];
					tetle.maximumValue = 4;
					tetle.start = false;
					IBlockState state = worldIn.getBlockState(pos);
					worldIn.setBlockState(pos, state.withProperty(BlockTrafficLight.PROPERTYCOLOUR, EnumColour.EMPTY));
				} else if (stack.getMetadata() == 2) {
					String shstr[] = { "gui.setcycle", "gui.settime", "gui.setcolor",
							String.valueOf(tetle.maximumValue), MathTools.arr2Str(tetle.times),
							MathTools.arr2Str(tetle.colors) };
					if (worldIn.isRemote)
						for (int i = 0; i <= 2; i++)
							playerIn.addChatComponentMessage(
									new TextComponentString(I18n.format(shstr[i], new Object[0]) + ":" + shstr[i + 3]));
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
				return EnumActionResult.FAIL;
			}
		}
		return EnumActionResult.SUCCESS;
	}
}