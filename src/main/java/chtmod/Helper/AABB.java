package chtmod.Helper;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;

public class AABB {
	public static AxisAlignedBB RotationBox(EnumFacing facing, int x, int z, int top, int height) {
		float x1 = (8 - (x / 2)) / 16F;
		float x2 = (8 + (x / 2)) / 16F;
		float y1 = top / 16F;
		float y2 = (top + height) / 16F;
		switch (facing) {
		case EAST:
			return new AxisAlignedBB((16 - z) / 16F, y1, x1, 1, y2, x2);
		case NORTH:
			return new AxisAlignedBB(x1, y1, 0, x2, y2, z / 16F);
		case SOUTH:
			return new AxisAlignedBB(x1, y1, (16 - z) / 16F, x2, y2, 1);
		case WEST:
			return new AxisAlignedBB(0, y1, x1, z / 16F, y2, x2);
		default:
			return new AxisAlignedBB(0, 0, 0, 1, 1, 1);
		}
	}
}
