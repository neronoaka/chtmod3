package chtmod.Tools;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemRuler extends Item {
	private int _type;

	public ItemRuler(String arg0, int arg1) {
		_type = arg1;
		this.setMaxStackSize(1);
		this.setUnlocalizedName(arg0);
		this.setRegistryName(arg0);
		this.setCreativeTab(chtmod.CustomCreativeTabs.toolTab);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing f, float hitX, float hitY, float hitZ) {
		IBlockState s = Block.getBlockFromName("chtmod:RoadRulerBlock").getDefaultState();
		int len = 11;
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		EnumFacing facing = playerIn.getHorizontalFacing();
		if (_type == 1) {
			s = Blocks.STONE.getDefaultState();
		}
		if (_type == 2) {
			s = Blocks.STONE.getDefaultState();
			len = 21;
		}
		worldIn.setBlockState(pos, s);
		worldIn.setBlockState(pos.offset(facing, len), s);
		return EnumActionResult.SUCCESS;
	}
}
