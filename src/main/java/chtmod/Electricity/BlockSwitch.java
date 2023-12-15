package chtmod.Electricity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockSwitch extends BlockElectricityFacingable {

	public BlockSwitch() {
		super("Switch");
	}

	public BlockSwitch(String arg0) {
		super(arg0);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(PROPERTYFACING);
		switch (facing) {
		case WEST:
			return new AxisAlignedBB(-1 / 16f, -7 / 16f, 0, 19 / 16f, 23 / 16f, 1);
		case NORTH:
			return new AxisAlignedBB(0, -7 / 16f, -1 / 16f, 1, 23 / 16f, 19 / 16f);
		case SOUTH:
			return new AxisAlignedBB(0, -7 / 16f, -3 / 16f, 1, 23 / 16f, 17 / 16f);
		case EAST:
			return new AxisAlignedBB(-3 / 16f, -7 / 16f, 0, 17 / 16f, 23 / 16f, 1);
		default:
			return FULL_BLOCK_AABB;
		}
	}
}
