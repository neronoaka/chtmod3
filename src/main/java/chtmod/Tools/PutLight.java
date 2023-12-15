package chtmod.Tools;

import java.util.List;

import chtmod.Pole.PoleHorizontal;
import chtmod.Pole.PoleRotation;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
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

public class PutLight extends Item {
	private int style;

	public PutLight() {
		this.setMaxStackSize(1);
		this.hasSubtypes = true;
		this.setUnlocalizedName("ToolPutLight");
		this.setRegistryName("ToolPutLight");
		this.setCreativeTab(chtmod.CustomCreativeTabs.toolTab);
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		for (int i = 0; i < 12; i++)
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
			EnumHand hand, EnumFacing f, float hitX, float hitY, float hitZ) {
		IBlockState l = chtmod.RoadLight.StartupCommon.lights[0].getDefaultState();
		IBlockState thick = Block.getBlockFromName("chtmod:PoleVerticalThick").getDefaultState();
		IBlockState thin = Block.getBlockFromName("chtmod:PoleVerticalThin").getDefaultState();
		IBlockState cen0 = Block.getBlockFromName("chtmod:PoleHorizontalThin").getDefaultState();
		IBlockState bottom = Block.getBlockFromName("chtmod:PoleVerticalLower").getDefaultState();
		IBlockState top = Block.getBlockFromName("chtmod:PoleVerticalUpper").getDefaultState();
		IBlockState lattern = Blocks.SEA_LANTERN.getDefaultState();
		int x = pos.getX();
		int y = pos.getY() + 1;
		int z = pos.getZ();
		EnumFacing facing = playerIn.getHorizontalFacing();
		IBlockState cen = cen0.withProperty(PoleHorizontal.PROPERTYNS,
				!(facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH));
		int fac1 = 0, fac2 = 0;
		switch (facing) {
		case EAST:
			fac1 = 4;
			fac2 = 2;
			break;
		case NORTH:
			fac1 = 3;
			fac2 = 5;
			break;
		case SOUTH:
			fac1 = 5;
			fac2 = 3;
			break;
		case WEST:
			fac1 = 2;
			fac2 = 4;
			break;
		default:
			break;
		}
		switch (stack.getMetadata()) {
		case 0:
			for (int i = 0; i <= 20; i++)
				worldIn.setBlockState(new BlockPos(x, y + i, z), thick);
			y += 19;
			switch (facing) {
			case NORTH:
				worldIn.setBlockState(new BlockPos(x - 1, y, z), l);
				worldIn.setBlockState(new BlockPos(x - 2, y, z), l);
				break;
			case WEST:
				worldIn.setBlockState(new BlockPos(x, y, z + 1), l);
				worldIn.setBlockState(new BlockPos(x, y, z + 2), l);
				break;
			case EAST:
				worldIn.setBlockState(new BlockPos(x, y, z - 1), l);
				worldIn.setBlockState(new BlockPos(x, y, z - 2), l);
				break;
			case SOUTH:
				worldIn.setBlockState(new BlockPos(x + 1, y, z), l);
				worldIn.setBlockState(new BlockPos(x + 2, y, z), l);
				break;
			default:
				break;
			}
			break;
		case 1:
			for (int i = 0; i < 10; i++)
				worldIn.setBlockState(new BlockPos(x, y + i, z), thick);
			worldIn.setBlockState(new BlockPos(x, y + 10, z), bottom);
			y += 10;
			switch (facing) {
			case NORTH:
				worldIn.setBlockState(new BlockPos(x - 1, y, z), l);
				worldIn.setBlockState(new BlockPos(x - 2, y, z), l);
				break;
			case WEST:
				worldIn.setBlockState(new BlockPos(x, y, z + 1), l);
				worldIn.setBlockState(new BlockPos(x, y, z + 2), l);
				break;
			case EAST:
				worldIn.setBlockState(new BlockPos(x, y, z - 1), l);
				worldIn.setBlockState(new BlockPos(x, y, z - 2), l);
				break;
			case SOUTH:
				worldIn.setBlockState(new BlockPos(x + 1, y, z), l);
				worldIn.setBlockState(new BlockPos(x + 2, y, z), l);
				break;
			default:
				break;
			}
			break;
		case 2:
			for (int i = 0; i < 10; i++)
				if (i < 5)
					worldIn.setBlockState(new BlockPos(x, y + i, z), thick);
				else
					worldIn.setBlockState(new BlockPos(x, y + i, z), thin);
			y += 9;
			switch (facing) {
			case NORTH:
				worldIn.setBlockState(new BlockPos(x - 1, y, z), l);
				break;
			case WEST:
				worldIn.setBlockState(new BlockPos(x, y, z + 1), l);
				break;
			case EAST:
				worldIn.setBlockState(new BlockPos(x, y, z - 1), l);
				break;
			case SOUTH:
				worldIn.setBlockState(new BlockPos(x + 1, y, z), l);
				break;
			default:
				break;
			}
			break;
		case 3:
			for (int i = 0; i < 15; i++)
				if (i < 10)
					worldIn.setBlockState(new BlockPos(x, y + i, z), thick);
				else
					worldIn.setBlockState(new BlockPos(x, y + i, z), thin);
			y += 14;
			switch (facing) {
			case NORTH:
				worldIn.setBlockState(new BlockPos(x - 1, y, z), cen);
				worldIn.setBlockState(new BlockPos(x + 1, y - 4, z), l);
				for (int j = 2; j <= 3; j++) {
					worldIn.setBlockState(new BlockPos(x - j, y, z), l);
				}
				break;
			case WEST:
				worldIn.setBlockState(new BlockPos(x, y, z + 1), cen);
				worldIn.setBlockState(new BlockPos(x, y - 4, z - 1), l);
				for (int j = 2; j <= 3; j++) {
					worldIn.setBlockState(new BlockPos(x, y, z + j), l);
				}
				break;
			case EAST:
				worldIn.setBlockState(new BlockPos(x, y, z - 1), cen);
				worldIn.setBlockState(new BlockPos(x, y - 4, z + 1), l);
				for (int j = 2; j <= 3; j++) {
					worldIn.setBlockState(new BlockPos(x, y, z - j), l);
				}
				break;
			case SOUTH:
				worldIn.setBlockState(new BlockPos(x + 1, y, z), cen);
				worldIn.setBlockState(new BlockPos(x - 1, y - 4, z), l);
				for (int j = 2; j <= 3; j++) {
					worldIn.setBlockState(new BlockPos(x + j, y, z), l);
				}
				break;
			default:
				break;
			}
			break;
		case 4:
			for (int i = 0; i < 19; i++)
				worldIn.setBlockState(new BlockPos(x, y + i, z), thick);
			y += 19;
			IBlockState vslope = Block.getBlockFromName("chtmod:PoleVerticalSlope").getDefaultState();
			worldIn.setBlockState(new BlockPos(x, y, z), vslope);
			worldIn.setBlockState(new BlockPos(x, y, z).north(), cen.withProperty(PoleHorizontal.PROPERTYNS, true));
			worldIn.setBlockState(new BlockPos(x, y, z).south(), cen.withProperty(PoleHorizontal.PROPERTYNS, true));
			worldIn.setBlockState(new BlockPos(x, y, z).west(), cen.withProperty(PoleHorizontal.PROPERTYNS, false));
			worldIn.setBlockState(new BlockPos(x, y, z).east(), cen.withProperty(PoleHorizontal.PROPERTYNS, false));
			for (int j = 2; j <= 3; j++) {
				worldIn.setBlockState(new BlockPos(x - j, y, z), l);
			}
			for (int j = 2; j <= 3; j++) {
				worldIn.setBlockState(new BlockPos(x, y, z + j), l);
			}
			// worldIn.setBlockState(new BlockPos(x, y, z - 1), cen);
			for (int j = 2; j <= 3; j++) {
				worldIn.setBlockState(new BlockPos(x, y, z - j), l);
			}
			// worldIn.setBlockState(new BlockPos(x + 1, y, z), cen);
			for (int j = 2; j <= 3; j++) {
				worldIn.setBlockState(new BlockPos(x + j, y, z), l);
			}
			break;
		case 5:
			for (int i = 0; i < 10; i++)
				worldIn.setBlockState(new BlockPos(x, y + i, z), thick);
			worldIn.setBlockState(new BlockPos(x, y + 10, z), bottom);
			y += 10;
			switch (facing) {
			case NORTH:
				worldIn.setBlockState(new BlockPos(x - 1, y, z), cen);
				for (int j = 2; j <= 3; j++) {
					worldIn.setBlockState(new BlockPos(x - j, y, z), l);
				}
				break;
			case WEST:
				worldIn.setBlockState(new BlockPos(x, y, z + 1), cen);
				for (int j = 2; j <= 3; j++) {
					worldIn.setBlockState(new BlockPos(x, y, z + j), l);
				}
				break;
			case EAST:
				worldIn.setBlockState(new BlockPos(x, y, z - 1), cen);
				for (int j = 2; j <= 3; j++) {
					worldIn.setBlockState(new BlockPos(x, y, z - j), l);
				}
				break;
			case SOUTH:
				worldIn.setBlockState(new BlockPos(x + 1, y, z), cen);
				for (int j = 2; j <= 3; j++) {
					worldIn.setBlockState(new BlockPos(x + j, y, z), l);
				}
				break;
			default:
				break;
			}
			break;
		case 6:
			for (int i = 0; i < 11; i++)
				worldIn.setBlockState(new BlockPos(x, y + i, z), thick);
			for (int i = 11; i < 16; i++)
				worldIn.setBlockState(new BlockPos(x, y + i, z), thin);
			y += 11;
			switch (facing) {
			case NORTH:
				x -= 1;
				worldIn.setBlockState(new BlockPos(x, y, z), getSmooth(fac1, 2));
				worldIn.setBlockState(new BlockPos(x, y + 1, z), getSmooth(fac1, 1));
				worldIn.setBlockState(new BlockPos(x, y + 4, z), cen);
				worldIn.setBlockState(new BlockPos(x - 1, y + 4, z), l);
				x += 2;
				y += 1;
				worldIn.setBlockState(new BlockPos(x, y, z), l);
				worldIn.setBlockState(new BlockPos(x, y + 2, z), getSmooth(fac2, 2));
				worldIn.setBlockState(new BlockPos(x, y + 3, z), getSmooth(fac2, 1));
				break;
			case WEST:
				z += 1;
				worldIn.setBlockState(new BlockPos(x, y, z), getSmooth(fac1, 2));
				worldIn.setBlockState(new BlockPos(x, y + 1, z), getSmooth(fac1, 1));
				worldIn.setBlockState(new BlockPos(x, y + 4, z), cen);
				worldIn.setBlockState(new BlockPos(x, y + 4, z + 1), l);
				z -= 2;
				y += 1;
				worldIn.setBlockState(new BlockPos(x, y, z), l);
				worldIn.setBlockState(new BlockPos(x, y + 2, z), getSmooth(fac2, 2));
				worldIn.setBlockState(new BlockPos(x, y + 3, z), getSmooth(fac2, 1));
				break;
			case EAST:
				z -= 1;
				worldIn.setBlockState(new BlockPos(x, y, z), getSmooth(fac1, 2));
				worldIn.setBlockState(new BlockPos(x, y + 1, z), getSmooth(fac1, 1));
				worldIn.setBlockState(new BlockPos(x, y + 4, z), cen);
				worldIn.setBlockState(new BlockPos(x, y + 4, z - 1), l);
				z += 2;
				y += 1;
				worldIn.setBlockState(new BlockPos(x, y, z), l);
				worldIn.setBlockState(new BlockPos(x, y + 2, z), getSmooth(fac2, 2));
				worldIn.setBlockState(new BlockPos(x, y + 3, z), getSmooth(fac2, 1));
				break;
			case SOUTH:
				x += 1;
				worldIn.setBlockState(new BlockPos(x, y, z), getSmooth(fac1, 2));
				worldIn.setBlockState(new BlockPos(x, y + 1, z), getSmooth(fac1, 1));
				worldIn.setBlockState(new BlockPos(x, y + 4, z), cen);
				worldIn.setBlockState(new BlockPos(x + 1, y + 4, z), l);
				x -= 2;
				y += 1;
				worldIn.setBlockState(new BlockPos(x, y, z), l);
				worldIn.setBlockState(new BlockPos(x, y + 2, z), getSmooth(fac2, 2));
				worldIn.setBlockState(new BlockPos(x, y + 3, z), getSmooth(fac2, 1));
				break;
			default:
				break;
			}
			break;
		case 7:
			for (int i = 0; i < 15; i++)
				if (i < 10)
					worldIn.setBlockState(new BlockPos(x, y + i, z), thick);
				else
					worldIn.setBlockState(new BlockPos(x, y + i, z), thin);
			y += 10;
			switch (facing) {
			case NORTH:
				x -= 1;
				worldIn.setBlockState(new BlockPos(x, y, z), getSmooth(fac1, 2));
				worldIn.setBlockState(new BlockPos(x, y + 1, z), thin);
				worldIn.setBlockState(new BlockPos(x, y + 2, z), getSmooth(fac1, 1));
				worldIn.setBlockState(new BlockPos(x, y + 4, z), cen);
				x += 2;
				y += 2;
				worldIn.setBlockState(new BlockPos(x, y, z), getSmooth(fac2, 2));
				worldIn.setBlockState(new BlockPos(x, y + 1, z), thin);
				worldIn.setBlockState(new BlockPos(x, y + 2, z), getSmooth(fac2, 1));
				y += 2;
				worldIn.setBlockState(new BlockPos(x - 3, y, z), l);
				worldIn.setBlockState(new BlockPos(x - 4, y, z), l);
				break;
			case WEST:
				z += 1;
				worldIn.setBlockState(new BlockPos(x, y, z), getSmooth(fac1, 2));
				worldIn.setBlockState(new BlockPos(x, y + 1, z), thin);
				worldIn.setBlockState(new BlockPos(x, y + 2, z), getSmooth(fac1, 1));
				worldIn.setBlockState(new BlockPos(x, y + 4, z), cen);
				z -= 2;
				y += 2;
				worldIn.setBlockState(new BlockPos(x, y, z), getSmooth(fac2, 2));
				worldIn.setBlockState(new BlockPos(x, y + 1, z), thin);
				worldIn.setBlockState(new BlockPos(x, y + 2, z), getSmooth(fac2, 1));
				y += 2;
				worldIn.setBlockState(new BlockPos(x, y, z + 3), l);
				worldIn.setBlockState(new BlockPos(x, y, z + 4), l);
				break;
			case EAST:
				z -= 1;
				worldIn.setBlockState(new BlockPos(x, y, z), getSmooth(fac1, 2));
				worldIn.setBlockState(new BlockPos(x, y + 1, z), thin);
				worldIn.setBlockState(new BlockPos(x, y + 2, z), getSmooth(fac1, 1));
				worldIn.setBlockState(new BlockPos(x, y + 4, z), cen);
				z += 2;
				y += 2;
				worldIn.setBlockState(new BlockPos(x, y, z), getSmooth(fac2, 2));
				worldIn.setBlockState(new BlockPos(x, y + 1, z), thin);
				worldIn.setBlockState(new BlockPos(x, y + 2, z), getSmooth(fac2, 1));
				y += 2;
				worldIn.setBlockState(new BlockPos(x, y, z - 3), l);
				worldIn.setBlockState(new BlockPos(x, y, z - 4), l);
				break;
			case SOUTH:
				x += 1;
				worldIn.setBlockState(new BlockPos(x, y, z), getSmooth(fac1, 2));
				worldIn.setBlockState(new BlockPos(x, y + 1, z), thin);
				worldIn.setBlockState(new BlockPos(x, y + 2, z), getSmooth(fac1, 1));
				worldIn.setBlockState(new BlockPos(x, y + 4, z), cen);
				x -= 2;
				y += 2;
				worldIn.setBlockState(new BlockPos(x, y, z), getSmooth(fac2, 2));
				worldIn.setBlockState(new BlockPos(x, y + 1, z), thin);
				worldIn.setBlockState(new BlockPos(x, y + 2, z), getSmooth(fac2, 1));
				y += 2;
				worldIn.setBlockState(new BlockPos(x + 3, y, z), l);
				worldIn.setBlockState(new BlockPos(x + 4, y, z), l);
				break;
			default:
				break;
			}
			break;
		case 8:
			for (int i = 0; i < 12; i++)
				if (i < 10)
					worldIn.setBlockState(new BlockPos(x, y + i, z), thick);
				else
					worldIn.setBlockState(new BlockPos(x, y + i, z), thin);
			y += 9;
			if (facing.name() == "WEST" || facing.name() == "EAST") {
				z -= 2;
				worldIn.setBlockState(new BlockPos(x, y + 2, z), l);
				z += 1;
				worldIn.setBlockState(new BlockPos(x, y + 1, z), thin);
				worldIn.setBlockState(new BlockPos(x, y + 2, z), bottom);
				z += 2;
				worldIn.setBlockState(new BlockPos(x, y + 1, z), thin);
				worldIn.setBlockState(new BlockPos(x, y + 2, z), bottom);
				z += 1;
				worldIn.setBlockState(new BlockPos(x, y + 2, z), l);
			} else {
				x -= 2;
				worldIn.setBlockState(new BlockPos(x, y + 2, z), l);
				x += 1;
				worldIn.setBlockState(new BlockPos(x, y + 1, z), thin);
				worldIn.setBlockState(new BlockPos(x, y + 2, z), bottom);
				x += 2;
				worldIn.setBlockState(new BlockPos(x, y + 1, z), thin);
				worldIn.setBlockState(new BlockPos(x, y + 2, z), bottom);
				x += 1;
				worldIn.setBlockState(new BlockPos(x, y + 2, z), l);
			}
			break;
		case 9:
			for (int i = 0; i < 5; i++)
				worldIn.setBlockState(new BlockPos(x, y + i, z), thin);
			y += 3;
			if (facing.name() == "WEST" || facing.name() == "EAST") {
				z += 1;
				worldIn.setBlockState(new BlockPos(x, y + 1, z), top);
				worldIn.setBlockState(new BlockPos(x, y + 2, z), lattern);
				z -= 2;
				worldIn.setBlockState(new BlockPos(x, y + 1, z), top);
				worldIn.setBlockState(new BlockPos(x, y + 2, z), lattern);
			} else {
				x += 1;
				worldIn.setBlockState(new BlockPos(x, y + 1, z), top);
				worldIn.setBlockState(new BlockPos(x, y + 2, z), lattern);
				x -= 2;
				worldIn.setBlockState(new BlockPos(x, y + 1, z), top);
				worldIn.setBlockState(new BlockPos(x, y + 2, z), lattern);
			}
			break;
		case 10:
			for (int i = 0; i < 5; i++)
				worldIn.setBlockState(new BlockPos(x, y + i, z), thin);
			y += 4;
			worldIn.setBlockState(new BlockPos(x + 1, y, z), top);
			worldIn.setBlockState(new BlockPos(x - 1, y, z), top);
			worldIn.setBlockState(new BlockPos(x, y, z + 1), top);
			worldIn.setBlockState(new BlockPos(x, y, z - 1), top);
			y += 1;
			worldIn.setBlockState(new BlockPos(x + 1, y, z), lattern);
			worldIn.setBlockState(new BlockPos(x - 1, y, z), lattern);
			worldIn.setBlockState(new BlockPos(x, y, z + 1), lattern);
			worldIn.setBlockState(new BlockPos(x, y, z - 1), lattern);
			break;
		case 11:
			for (int i = 0; i < 6; i++)
				worldIn.setBlockState(new BlockPos(x, y + i, z), Blocks.COBBLESTONE_WALL.getDefaultState());
			for (int i = 6; i < 11; i++)
				worldIn.setBlockState(new BlockPos(x, y + i, z), Blocks.DARK_OAK_FENCE.getDefaultState());
			y += 10;
			if (facing.name() == "WEST" || facing.name() == "EAST") {
				z += 1;
				worldIn.setBlockState(new BlockPos(x, y, z), Blocks.DARK_OAK_FENCE.getDefaultState());
				worldIn.setBlockState(new BlockPos(x, y - 1, z), lattern);
				z -= 2;
				worldIn.setBlockState(new BlockPos(x, y, z), Blocks.DARK_OAK_FENCE.getDefaultState());
				worldIn.setBlockState(new BlockPos(x, y - 1, z), lattern);
			} else {
				x += 1;
				worldIn.setBlockState(new BlockPos(x, y, z), Blocks.DARK_OAK_FENCE.getDefaultState());
				worldIn.setBlockState(new BlockPos(x, y - 1, z), lattern);
				x -= 2;
				worldIn.setBlockState(new BlockPos(x, y, z), Blocks.DARK_OAK_FENCE.getDefaultState());
				worldIn.setBlockState(new BlockPos(x, y - 1, z), lattern);
			}
			break;
		}
		return EnumActionResult.SUCCESS;
	}

	private IBlockState getSmooth(int facing, int updown) {
		IBlockState Smooth = chtmod.Pole.StartupCommon.prt[5].getDefaultState()
				.withProperty(PoleRotation.PROPERTYFACING, EnumFacing.getHorizontal(facing));
		return Smooth.withProperty(PoleRotation.PROPERTYSTYLE, updown);
	}
}