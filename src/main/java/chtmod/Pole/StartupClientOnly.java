package chtmod.Pole;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;

public class StartupClientOnly {
	public static void preInitClientOnly() {
		for (ItemBlock i : StartupCommon.items) {
			String n = "chtmod:pole/" + i.getRegistryName().getResourcePath().replace("Pole", "");
			ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(n, "inventory"));
		}
		for (ItemPole ip : StartupCommon.iprt) {
			String n = "chtmod:pole/" + ip.getRegistryName().getResourcePath().replace("Pole", "");
			for (int j = 0; j < 4; j++)
				ModelLoader.setCustomModelResourceLocation(ip, j, new ModelResourceLocation(n + j, "inventory"));
		}
		String npc = "chtmod:pole/Centre";
		for (int k = 0; k < 5; k++)
			ModelLoader.setCustomModelResourceLocation(StartupCommon.ipc, k,
					new ModelResourceLocation(npc + k, "inventory"));

	}
}
