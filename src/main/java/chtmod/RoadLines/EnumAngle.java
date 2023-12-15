package chtmod.RoadLines;

import net.minecraft.util.IStringSerializable;

public enum EnumAngle implements IStringSerializable {
	A(0, "a"), B(1, "b"), C(2, "c"), D(3, "d");

	public int getMetadata() {
		return this.meta;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public static EnumAngle byMetadata(int meta) {
		if (meta < 0 || meta >= META_LOOKUP.length) {
			meta = 0;
		}

		return META_LOOKUP[meta];
	}

	public String getName() {
		return this.name;
	}

	private final int meta;
	private final String name;
	private static final EnumAngle[] META_LOOKUP = new EnumAngle[values().length];

	private EnumAngle(int i_meta, String i_name) {
		this.meta = i_meta;
		this.name = i_name;
	}

	static {
		for (EnumAngle angle : values()) {
			META_LOOKUP[angle.getMetadata()] = angle;
		}
	}
}