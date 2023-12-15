package chtmod.Tools;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BuildRoad extends Item {

	public BuildRoad() {
		this.setMaxStackSize(1);
		this.setUnlocalizedName("ToolBuildRoad");
		this.setRegistryName("ToolBuildRoad");
		this.setCreativeTab(chtmod.CustomCreativeTabs.toolTab);
		this.setHasSubtypes(true);
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		for (int i = 0; i <= 2; i++)
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
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		Block b = Block.getBlockFromName("chtmod:BlockBitumen");
		int length = stack.getMetadata() * 2 + 3;
		int x0 = pos.getX();
		int y = pos.getY();
		int z0 = pos.getZ();
		int x = x0 - ((length - 1) / 2);
		int z = z0 - ((length - 1) / 2);
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				worldIn.setBlockState(new BlockPos(x + i, y, z + j), b.getDefaultState());
			}
		}
		return EnumActionResult.SUCCESS;
	}
}