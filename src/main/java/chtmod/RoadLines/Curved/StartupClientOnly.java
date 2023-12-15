package chtmod.RoadLines.Curved;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class StartupClientOnly {
	public static void preInitClientOnly() {
		ModelLoader.setCustomModelResourceLocation(StartupCommon.icl, 0,
				new ModelResourceLocation("chtmod:line/ToolCurvedWhite", "inventory"));
		ModelLoader.setCustomModelResourceLocation(StartupCommon.icl, 1,
				new ModelResourceLocation("chtmod:line/ToolCurvedYellow", "inventory"));
		ModelLoader.setCustomModelResourceLocation(StartupCommon.icl, 2,
				new ModelResourceLocation("chtmod:line/ToolCurvedYellowDouble", "inventory"));
		ModelLoader.setCustomModelResourceLocation(StartupCommon.icl, 3,
				new ModelResourceLocation("chtmod:line/ToolCurvedWhiteThick", "inventory"));
		ModelLoader.setCustomModelResourceLocation(StartupCommon.icl, 4,
				new ModelResourceLocation("chtmod:line/ToolCurvedYellowThick", "inventory"));
		ModelLoader.setCustomModelResourceLocation(StartupCommon.icl, 5,
				new ModelResourceLocation("chtmod:line/ToolCurvedWhiteDotted", "inventory"));
		ModelLoader.setCustomModelResourceLocation(StartupCommon.icl, 6,
				new ModelResourceLocation("chtmod:line/ToolCurvedYellowDotted", "inventory"));
		ModelLoader.setCustomModelResourceLocation(StartupCommon.icl, 7,
				new ModelResourceLocation("chtmod:line/ToolCurvedYellowDoubleDotted", "inventory"));
		ModelLoader.setCustomModelResourceLocation(StartupCommon.icl, 8,
				new ModelResourceLocation("chtmod:line/ToolCurvedWhiteThickDotted", "inventory"));
		ModelLoader.setCustomModelResourceLocation(StartupCommon.icl, 9,
				new ModelResourceLocation("chtmod:line/ToolCurvedYellowThickDotted", "inventory"));
	}

	public static void initClientOnly() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLineEntity.class, new TileEntityLineRenderer());
	}
}