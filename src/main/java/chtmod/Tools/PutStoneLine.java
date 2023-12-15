package chtmod.Tools;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PutStoneLine extends Item {
	private Block block;

	public PutStoneLine(Block arg0, String arg1) {
		this.setMaxStackSize(1);
		this.setUnlocalizedName(arg1);
		this.setRegistryName(arg1);
		this.setCreativeTab(chtmod.CustomCreativeTabs.toolTab);
		block = arg0;
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		EnumFacing Facing = playerIn.getHorizontalFacing();
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		for(int i=0;i<10;i++)
		switch (Facing) {
		case EAST:
			worldIn.setBlockState(new BlockPos(x + i, y, z), block.getDefaultState());
			break;
		case NORTH:
			worldIn.setBlockState(new BlockPos(x, y, z - i), block.getDefaultState());
			break;
		case SOUTH:
			worldIn.setBlockState(new BlockPos(x, y, z + i), block.getDefaultState());
			break;
		case WEST:
			worldIn.setBlockState(new BlockPos(x - i, y, z), block.getDefaultState());
			break;
		default:
			break;
		}
		return EnumActionResult.SUCCESS;
	}
}
