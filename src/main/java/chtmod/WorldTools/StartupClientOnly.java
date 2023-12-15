package chtmod.WorldTools;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class StartupClientOnly {
	public static void preInitClientOnly() {
		ModelLoader.setCustomModelResourceLocation(StartupCommon.wtcur, 0,
				new ModelResourceLocation("chtmod:WorldTools/WTCur", "inventory"));
		ModelLoader.setCustomModelResourceLocation(StartupCommon.wtera, 0,
				new ModelResourceLocation("chtmod:WorldTools/WTEra", "inventory"));
		ModelLoader.setCustomModelResourceLocation(StartupCommon.wthmr, 0,
				new ModelResourceLocation("chtmod:WorldTools/WTHmr", "inventory"));
		ModelLoader.setCustomModelResourceLocation(StartupCommon.wtcpy, 0,
				new ModelResourceLocation("chtmod:WorldTools/WTCpy", "inventory"));
		ModelLoader.setCustomModelResourceLocation(StartupCommon.wtpst, 0,
				new ModelResourceLocation("chtmod:WorldTools/WTPst", "inventory"));
	}
}
