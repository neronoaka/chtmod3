package chtmod.RoadLines;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class StartupClientOnly {
	public static void preInitClientOnly() {
		for (int i = 0; i < 4; i++)
			ModelLoader.setCustomModelResourceLocation(StartupCommon.pdl, i,
					new ModelResourceLocation("chtmod:line/PutDottedLine" + i, "inventory"));
		for (ItemRoadLine i : StartupCommon.items) {
			String n = "chtmod:line/" + i.getRegistryName().getResourcePath().replace("RoadLine", "");
			ModelLoader.setCustomModelResourceLocation(i, EnumAngle.A.getMetadata(),
					new ModelResourceLocation(n + "_a", "inventory"));
			ModelLoader.setCustomModelResourceLocation(i, EnumAngle.B.getMetadata(),
					new ModelResourceLocation(n + "_b", "inventory"));
			ModelLoader.setCustomModelResourceLocation(i, EnumAngle.C.getMetadata(),
					new ModelResourceLocation(n + "_c", "inventory"));
			ModelLoader.setCustomModelResourceLocation(i, EnumAngle.D.getMetadata(),
					new ModelResourceLocation(n + "_d", "inventory"));
		}
	}
}