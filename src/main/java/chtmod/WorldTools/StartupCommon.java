package chtmod.WorldTools;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon {
	public static WTCur wtcur = new WTCur();
	public static WTEra wtera = new WTEra();
	public static WTHmr wthmr = new WTHmr();
	public static WTCpy wtcpy = new WTCpy();
	public static WTPst wtpst = new WTPst();

	public static void preInitCommon() {
		GameRegistry.register(wtcur);
		GameRegistry.register(wtera);
		GameRegistry.register(wthmr);
		GameRegistry.register(wtcpy);
		GameRegistry.register(wtpst);
	}
}
