package chtmod.TrafficLight;

import net.minecraft.util.IStringSerializable;

public enum EnumColour implements IStringSerializable {
	EMPTY(0, "empty"), RED(1, "red"), YELLOW(2, "yellow"), GREEN(3, "green");
	private final int meta;
	private final String name;
	private static final EnumColour[] META_LOOKUP = new EnumColour[values().length];

	public int getMetadata() {
		return this.meta;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public static EnumColour byMetadata(int meta) {
		if (meta < 0 || meta >= META_LOOKUP.length) {
			meta = 0;
		}

		return META_LOOKUP[meta];
	}

	public String getName() {
		return this.name;
	}

	private EnumColour(int i_meta, String i_name) {
		this.meta = i_meta;
		this.name = i_name;
	}

	static {
		for (EnumColour colour : values()) {
			META_LOOKUP[colour.getMetadata()] = colour;
		}
	}
}