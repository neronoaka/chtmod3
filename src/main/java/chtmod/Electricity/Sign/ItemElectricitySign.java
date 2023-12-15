package chtmod.Electricity.Sign;

import java.util.List;

import chtmod.Electricity.StartupCommon;
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

public class ItemElectricitySign extends Item {

	public ItemElectricitySign() {
		this.setMaxStackSize(1);
		this.setUnlocalizedName("ItemElectricitySign");
		this.setRegistryName("ItemElectricitySign");
		this.setCreativeTab(chtmod.CustomCreativeTabs.electricityTab);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		if (!worldIn.isRemote)
			Minecraft.getMinecraft().displayGuiScreen(new GUISetElectricitySign(itemStackIn));
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
				worldIn.setBlockState(p,
						StartupCommon.es.getDefaultState().withProperty(BlockElectricitySign.PROPERTYFACING, pf));
				try {
					TileEntityElectricitySignEntity tese = (TileEntityElectricitySignEntity) worldIn.getTileEntity(p);
					NBTTagCompound nbt = new NBTTagCompound();
					int n = stack.getTagCompound().getInteger("NUMBER");
					String tex = stack.getTagCompound().getString("TEXTURE");
					tese.number = n;
					tese.facing = pf.getHorizontalIndex();
					tese.texture = tex;
					nbt.setString("TEXTURE", tex);
					nbt.setInteger("NUMBER", n + 1);
					stack.setTagCompound(nbt);
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