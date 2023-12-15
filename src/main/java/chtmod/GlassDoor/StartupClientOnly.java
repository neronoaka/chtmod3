package chtmod.GlassDoor;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class StartupClientOnly {
	public static void preInitClientOnly() {
			ModelLoader.setCustomModelResourceLocation(StartupCommon.igd, 0,
					new ModelResourceLocation("chtmod:GlassDoor", "inventory"));
	}
}