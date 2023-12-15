package chtmod.Miscellaneous;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon {
	public static BlockTicketChecker tc = new BlockTicketChecker();
	public static ItemBlock itc = (ItemBlock) new ItemBlock(tc).setRegistryName(tc.getRegistryName());
	public static BlockTicketSeller tcs = new BlockTicketSeller();
	public static ItemBlock itcs = (ItemBlock) new ItemBlock(tcs).setRegistryName(tcs.getRegistryName());
	public static ItemTicket ticket = new ItemTicket();

	public static void preInitCommon() {
		GameRegistry.register(ticket);
		GameRegistry.register(tc);
		GameRegistry.register(itc);
		GameRegistry.register(tcs);
		GameRegistry.register(itcs);
	}
}