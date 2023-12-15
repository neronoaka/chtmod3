package chtmod.Electricity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockElectricityLightPole extends BlockInsulator {

	public BlockElectricityLightPole() {
		super("ElectricityLightPole");
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		int meta = state.getValue(PROPERTYSTYLE);
		return new ItemStack(StartupCommon.ielp, 1, meta);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(PROPERTYFACING);
		float y1 = 0;
		float y2 = 1;
		switch (facing) {
		case EAST:
			return new AxisAlignedBB(0, y1, 7 / 16f, 1, y2, 9 / 16f);
		case NORTH:
			return new AxisAlignedBB(7 / 16f, y1, 0, 9 / 16f, y2, 1);
		case SOUTH:
			return new AxisAlignedBB(7 / 16f, y1, 0, 9 / 16f, y2, 1);
		case WEST:
			return new AxisAlignedBB(0, y1, 7 / 16f, 1, y2, 9 / 16f);
		default:
			return FULL_BLOCK_AABB;
		}
	}
}
