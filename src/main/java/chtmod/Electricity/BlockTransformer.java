package chtmod.Electricity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockTransformer extends BlockElectricityFacingable {

	public BlockTransformer() {
		super("Transformer");
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(PROPERTYFACING);
		AxisAlignedBB ns = new AxisAlignedBB(-8 / 16f, -7 / 16f, -2 / 16f, 24 / 16f, 23 / 16f, 18 / 16f);
		AxisAlignedBB we = new AxisAlignedBB(-2 / 16f, -7 / 16f, -8 / 16f, 18 / 16f, 23 / 16f, 24 / 16f);
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