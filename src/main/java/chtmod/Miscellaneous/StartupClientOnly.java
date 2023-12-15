package chtmod.Miscellaneous;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupClientOnly {
	public static void preInitClientOnly() {
		ModelLoader.setCustomModelResourceLocation(StartupCommon.ticket, 0,
				new ModelResourceLocation("chtmod:Ticket", "inventory"));
		ModelLoader.setCustomModelResourceLocation(StartupCommon.itc, 0,
				new ModelResourceLocation("chtmod:TicketChecker", "inventory"));
		ModelLoader.setCustomModelResourceLocation(StartupCommon.itcs, 0,
				new ModelResourceLocation("chtmod:TicketSeller", "inventory"));
	}
}