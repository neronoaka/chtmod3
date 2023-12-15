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

public class ItemSignCustom extends Item {

	public ItemSignCustom() {
		this.setMaxStackSize(1);
		this.setUnlocalizedName("ItemSignCustom");
		this.setRegistryName("ItemSignCustom");
		this.setCreativeTab(chtmod.CustomCreativeTabs.lineTab);
		this.hasSubtypes = true;
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		for (int i = 0; i < 7; i++)
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
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		if (!worldIn.isRemote)
			Minecraft.getMinecraft().displayGuiScreen(new GUISetSign(itemStackIn));
		return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		EnumFacing pf = playerIn.getHorizontalFacing();
		BlockPos p = pos;
		switch (pf) {
		case EAST:
			p = pos.west();
			break;
		case NORTH:
			p = pos.south();
			break;
		case SOUTH:
			p = pos.north();
			break;
		case WEST:
			p = pos.east();
			break;
		default:
			break;
		}
		if (stack.hasTagCompound()) {
			if (worldIn.isAirBlock(p)) {
				worldIn.setBlockState(p, StartupCommon.sp.getDefaultState().withProperty(BlockSign.PROPERTYFACING, pf));
				try {
					TileEntitySignEntity tese = (TileEntitySignEntity) worldIn.getTileEntity(p);
					tese.size = stack.getMetadata();
					tese.facing = pf.getHorizontalIndex();
					tese.texture = stack.getTagCompound().getString("TEXTURE");
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		} else {
			if (worldIn.isRemote)
				playerIn.addChatComponentMessage(new TextComponentString(I18n.format("gui.noinput", new Object[0])));
		}
		return EnumActionResult.SUCCESS;
	}
}