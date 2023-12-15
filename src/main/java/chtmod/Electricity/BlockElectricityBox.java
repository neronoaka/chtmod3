package chtmod.Electricity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockElectricityBox extends BlockElectricityFacingable {

	public BlockElectricityBox() {
		super("Box");
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(PROPERTYFACING);
		AxisAlignedBB ns = new AxisAlignedBB(0, -7 / 16f, 4 / 16f, 1, 1, 12 / 16f);
		AxisAlignedBB we = new AxisAlignedBB(4 / 16f, -7 / 16f, 0, 12 / 16f, 1, 1);
		switch (facing) {
		case EAST:
			return we;
		case NORTH:
			return ns;
		case SOUTH:
			return ns;
		case WEST:
			return we;
		default:
			return FULL_BLOCK_AABB;
		}
	}
}
