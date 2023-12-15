package chtmod.Tools;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RepeatBlock extends Item {
	private int len;

	public RepeatBlock(int arg0) {
		this.setMaxStackSize(1);
		this.setUnlocalizedName("ToolRepeatBlock" + arg0);
		this.setRegistryName("ToolRepeatBlock" + arg0);
		this.setCreativeTab(chtmod.CustomCreativeTabs.toolTab);
		len = arg0;
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		EnumFacing pfacing = playerIn.getHorizontalFacing();
		IBlockState ibs = worldIn.getBlockState(pos);
		switch (pfacing) {
		case EAST:
			for (int n = 0; n < len; n++)
				worldIn.setBlockState(new BlockPos(pos.getX() + n, pos.getY(), pos.getZ()), ibs);
			break;
		case NORTH:
			for (int n = 0; n < len; n++)
				worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - n), ibs);
			break;
		case SOUTH:
			for (int n = 0; n < len; n++)
				worldIn.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + n), ibs);
			break;
		case WEST:
			for (int n = 0; n < len; n++)
				worldIn.setBlockState(new BlockPos(pos.getX() - n, pos.getY(), pos.getZ()), ibs);
			break;
		default:
			break;
		}
		return EnumActionResult.SUCCESS;
	}
}