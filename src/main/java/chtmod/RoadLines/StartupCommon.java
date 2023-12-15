package chtmod.RoadLines;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon {
	public static String[] blocks = { "White", "WhiteThick", "Yellow", "YellowThick", "YellowDouble",
			"InvaginateYellow", "InvaginateWhite", "InvaginateYellowThick", "InvaginateWhiteThick", "Straight", "Left",
			"Right", "Triangle", "WhiteSlope2", "YellowSlope2", "YellowDoubleSlope2" };
	public static ItemRoadLine[] items = new ItemRoadLine[blocks.length];
	public static ItemPutDottedLine pdl = new ItemPutDottedLine();

	public static void preInitCommon() {
		GameRegistry.register(pdl);
		for (int i = 0; i < blocks.length; i++) {
			BlockRoadLine rl = new BlockRoadLine(blocks[i]);
			GameRegistry.register(rl);
			ItemRoadLine irl = new ItemRoadLine(rl);
			GameRegistry.register(irl);
			items[i] = irl;
		}
	}
}