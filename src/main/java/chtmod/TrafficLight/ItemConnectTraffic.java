package chtmod.TrafficLight;

import java.util.List;

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
import net.minecraft.world.World;

public class ItemConnectTraffic extends Item {

	public ItemConnectTraffic() {
		this.setMaxStackSize(1);
		this.setUnlocalizedName("ItemConnectTraffic");
		this.setRegistryName("ItemConnectTraffic");
		this.setCreativeTab(chtmod.CustomCreativeTabs.trafficTab);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		BlockPos p = pos.offset(facing);
		if (stack.hasTagCompound() && worldIn.isAirBlock(p)) {
			worldIn.setBlockState(p, StartupCommon.sp.getDefaultState());
			if (worldIn.getTileEntity(p) instanceof TileEntitySignalProviderEntity) {
				try {
					TileEntitySignalProviderEntity tespe = (TileEntitySignalProviderEntity) worldIn.getTileEntity(p);
					tespe.position = stack.getTagCompound().getIntArray("POS");
					tespe.start = true;
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
				if (worldIn.isRemote)
					playerIn.addChatComponentMessage(
							new TextComponentString(I18n.format("gui.setsuccess", new Object[0])));
				stack.setTagCompound(null);
			}
		} else {
			IBlockState traffic = worldIn.getBlockState(pos);
			if (traffic.getBlock() instanceof BlockTrafficLight) {
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setIntArray("POS", new int[] { pos.getX(), pos.getY(), pos.getZ() });
				stack.setTagCompound(nbt);
				if (worldIn.isRemote)
					playerIn.addChatComponentMessage(
							new TextComponentString(I18n.format("gui.setpossuccess", new Object[0])));
			}
		}
		return EnumActionResult.SUCCESS;
	}
}