package chtmod.Tools;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PutStoneUnder extends Item {

	public PutStoneUnder(String arg0) {
		this.setMaxStackSize(1);
		this.setUnlocalizedName(arg0);
		this.setRegistryName(arg0);
		this.setCreativeTab(chtmod.CustomCreativeTabs.toolTab);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		for (int i = 0; i <= pos.getY(); i++) {
			BlockPos p = new BlockPos(pos.getX(), i, pos.getZ());
			Block b = worldIn.getBlockState(p).getBlock();
			boolean replace = (b == Blocks.BEDROCK || b == Blocks.STONE);
			if (!replace)
				worldIn.setBlockState(p, Blocks.STONE.getDefaultState());
		}
		return EnumActionResult.SUCCESS;
	}
}
