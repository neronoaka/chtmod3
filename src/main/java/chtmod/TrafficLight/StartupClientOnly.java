package chtmod.TrafficLight;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupClientOnly {
	public static ItemCycle ic = new ItemCycle();

	public static void preInitClientOnly() {
		GameRegistry.register(ic);
		for (int i = 0; i < 3; i++)
			ModelLoader.setCustomModelResourceLocation(ic, i,
					new ModelResourceLocation("chtmod:ItemCycle", "inventory"));
		ModelLoader.setCustomModelResourceLocation(StartupCommon.ct, 0,
				new ModelResourceLocation("chtmod:ConnectTraffic", "inventory"));
		for (ItemBlock itb : StartupCommon.itl) {
			String iname = itb.getBlock().getRegistryName().getResourcePath();
			ModelLoader.setCustomModelResourceLocation(itb, 0,
					new ModelResourceLocation("chtmod:trafficlight/" + iname, "inventory"));
		}
	}
}
