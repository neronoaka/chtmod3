package chtmod.Sign;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
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

public class ItemMetroSign extends Item {

	public ItemMetroSign() {
		this.setMaxStackSize(1);
		this.setUnlocalizedName("ItemMetroSign");
		this.setRegistryName("ItemMetroSign");
		this.setCreativeTab(chtmod.CustomCreativeTabs.lineTab);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		if (!worldIn.isRemote)
			Minecraft.getMinecraft().displayGuiScreen(new GUISetMetroSign(itemStackIn));
		return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		EnumFacing pf = playerIn.getHorizontalFacing();
		EnumFacing place = EnumFacing.UP;
		BlockPos pos2 = pos.offset(facing);
		if (stack.hasTagCompound()) {
			NBTTagCompound nbt = stack.getTagCompound();
			int start = nbt.getInteger("START");
			int end = nbt.getInteger("END");
			boolean lr = nbt.getBoolean("LtoR");
			String line = nbt.getString("LINE");
			switch (pf) {
			case EAST:
				if (lr)
					place = EnumFacing.SOUTH;
				else
					place = EnumFacing.NORTH;
				break;
			case NORTH:
				if (lr)
					place = EnumFacing.EAST;
				else
					place = EnumFacing.WEST;
				break;
			case SOUTH:
				if (lr)
					place = EnumFacing.WEST;
				else
					place = EnumFacing.EAST;
				break;
			case WEST:
				if (lr)
					place = EnumFacing.NORTH;
				else
					place = EnumFacing.SOUTH;
				break;
			default:
				break;
			}
			int len = Math.abs(end - start) + 1;
			for (int i = 0; i < len; i++) {
				BlockPos p = pos2.offset(place, i);
				if (worldIn.isAirBlock(p)) {
					worldIn.setBlockState(p, StartupCommon.sp.getDefaultState().withProperty(BlockSign.PROPERTYFACING, pf));
					int step = end > start ? i : -i;
					int index = start + step;
					try {
						TileEntitySignEntity tese = (TileEntitySignEntity) worldIn.getTileEntity(p);
						tese.size = 0;
						tese.facing = pf.getHorizontalIndex();
						tese.texture = "metro/" + line + "/" + index + "/ess";
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
				} else {
					break;
				}
			}
		} else {
			if (worldIn.isRemote)
				playerIn.addChatComponentMessage(new TextComponentString(I18n.format("gui.noinput", new Object[0])));
		}
		return EnumActionResult.SUCCESS;
	}
}