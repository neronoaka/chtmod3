package chtmod.GlassDoor;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
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

public class ItemGlassDoor extends Item {

	public ItemGlassDoor() {
		this.setMaxStackSize(1);
		this.setUnlocalizedName("ItemGlassDoor");
		this.setRegistryName("ItemGlassDoor");
		this.setCreativeTab(chtmod.CustomCreativeTabs.miscellaneousTab);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		boolean f = playerIn.getHorizontalFacing() == EnumFacing.NORTH
				|| playerIn.getHorizontalFacing() == EnumFacing.SOUTH;
		IBlockState state = StartupCommon.gd0.getDefaultState().withProperty(BlockGlassDoor.OPEN, false)
				.withProperty(BlockGlassDoor.PROPERTYNS, f);
		boolean left = true;
		if (!stack.hasTagCompound()) {
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setBoolean("left", true);
			stack.setTagCompound(nbt);
		} else {
			left = stack.getTagCompound().getBoolean("left");
			stack.getTagCompound().setBoolean("left", left ? false : true);
		}

		for (int i = 1; i <= 2; i++)
			worldIn.setBlockState(pos.up(i),
					state.withProperty(BlockGlassDoor.PROPERTYLAYER, i).withProperty(BlockGlassDoor.LEFT, left));
		return EnumActionResult.SUCCESS;
	}
}