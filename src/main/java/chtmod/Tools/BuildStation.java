package chtmod.Tools;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BuildStation extends Item {

	public BuildStation() {
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
		this.setUnlocalizedName("ToolBuildStation");
		this.setRegistryName("ToolBuildStation");
		this.setCreativeTab(chtmod.CustomCreativeTabs.toolTab);
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
	private void genBlock(World w, int x, int y, int z, Block b) {
		w.setBlockState(new BlockPos(x, y, z), b.getDefaultState());
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		Block stage = Blocks.DOUBLE_STONE_SLAB;
		Block s = Blocks.STONE;
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		EnumFacing facing1 = playerIn.getHorizontalFacing();
		switch (stack.getMetadata()) {
		case 1:
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 26; j++)
					switch (facing1) {
					case EAST:
						genBlock(worldIn, x + j, y, z + i, s);
						genBlock(worldIn, x + j, y + 1, z + i, stage);
						break;
					case NORTH:
						genBlock(worldIn, x + i, y, z - j, s);
						genBlock(worldIn, x + i, y + 1, z - j, stage);
						break;
					case SOUTH:
						genBlock(worldIn, x - i, y, z + j, s);
						genBlock(worldIn, x - i, y + 1, z + j, stage);
						break;
					case WEST:
						genBlock(worldIn, x - j, y, z - i, s);
						genBlock(worldIn, x - j, y + 1, z - i, stage);
						break;
					default:
						break;
					}
			break;
		case 0:
			for (int h = 0; h < 10; h++)
				for (int l = 0; l < 4; l++)
					switch (facing1) {
					case EAST:
						genBlock(worldIn, x + h, y + h, z + l, s);
						break;
					case NORTH:
						genBlock(worldIn, x + l, y + h, z - h, s);
						break;
					case SOUTH:
						genBlock(worldIn, x - l, y + h, z + h, s);
						break;
					case WEST:
						genBlock(worldIn, x - h, y + h, z - l, s);
						break;
					default:
						break;
					}
			break;
		case 2:
			for (int h = 0; h < 10; h++)
				for (int l = 0; l < 4; l++)
					switch (facing1) {
					case EAST:
						worldIn.setBlockToAir(new BlockPos(x + h, y + h, z + l));
						break;
					case NORTH:
						worldIn.setBlockToAir(new BlockPos(x + l, y + h, z - h));
						break;
					case SOUTH:
						worldIn.setBlockToAir(new BlockPos(x - l, y + h, z + h));
						break;
					case WEST:
						worldIn.setBlockToAir(new BlockPos(x - h, y + h, z - l));
						break;
					default:
						break;
					}
			break;
		}
		return EnumActionResult.SUCCESS;
	}
}
