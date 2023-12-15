package chtmod.RoadLight;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon {
	public static BlockLight lights[] = new BlockLight[] { new BlockLight("RoadLight", 1),
			new BlockLight("RoadLight0", 0, false), new BlockRotationLight("RoadLight1", 0),
			new BlockRotationLight("RoadLight2", 1), new BlockRotationLight("RoadLight3", 0),
			new BlockRotationLight("RoadLight4", 2), new BlockRotationLight("RoadLight5", -1, false),
			new BlockRotationLight("RoadLight6", -1, false), new BlockRotationLight("RoadLight7", 3),
			new BlockRotationLight("RoadLight8", 4, false), new BlockLight("RoadLight9", 2, false),
			new BlockLight("RoadLight10", 3, false), new BlockLight("RoadLight11", 0, false),
			new BlockRotationLight("RoadLight12", 5), new BlockRotationLight("RoadLight13", 6),
			new BlockRotationLight("RoadLight14", 7) };
	public static ItemBlock[] inrlights = new ItemBlock[lights.length];

	public static void preInitCommon() {
		for (int i = 0; i < lights.length; i++) {
			ItemBlock light = (ItemBlock) new ItemBlock(lights[i]).setRegistryName(lights[i].getRegistryName());
			GameRegistry.register(lights[i]);
			inrlights[i] = light;
			GameRegistry.register(light);
		}
	}
}