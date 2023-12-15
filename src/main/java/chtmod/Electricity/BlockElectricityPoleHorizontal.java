package chtmod.Electricity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockElectricityPoleHorizontal extends BlockElectricityFacingable {

	public BlockElectricityPoleHorizontal() {
		super("PoleHorizontal");
		this.setHardness(1);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(PROPERTYFACING);
		if (facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH)
			return new AxisAlignedBB(7 / 16F, 7 / 16F, 0, 9 / 16F, 9 / 16F, 1);
		else
			return new AxisAlignedBB(0, 7 / 16F, 7 / 16F, 1, 9 / 16F, 9 / 16F);
	}
}
