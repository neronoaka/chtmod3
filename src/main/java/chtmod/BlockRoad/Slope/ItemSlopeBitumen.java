package chtmod.BlockRoad.Slope;

import java.util.List;

import chtmod.BlockRoad.StartupCommon;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemSlopeBitumen extends Item {
	public ItemSlopeBitumen() {
		this.maxStackSize = 1;
		this.hasSubtypes = true;
		this.setRegistryName("ItemSlopeBitumen");
		this.setUnlocalizedName("ItemSlopeBitumen");
		this.setCreativeTab(chtmod.CustomCreativeTabs.toolTab);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		Block bs[] = StartupCommon.sbv2;
		EnumFacing f = playerIn.getHorizontalFacing();
		for (int i = 0; i < 5; i++) {
			IBlockState ibs = bs[stack.getMetadata()].getDefaultState().withProperty(BlockSlopeBitumen.PROPERTYLEVEL, i)
					.withProperty(BlockSlopeBitumen.PROPERTYFACING, f).withProperty(BlockSlopeBitumen.HEAD, i == 0);
			worldIn.setBlockState(pos.up().offset(f, i), ibs);
		}
		return EnumActionResult.SUCCESS;
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		for (int i = 0; i <= 4; i++)
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
}
