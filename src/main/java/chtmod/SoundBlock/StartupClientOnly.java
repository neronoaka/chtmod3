package chtmod.SoundBlock;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupClientOnly {
	public static ItemSoundBlock isb = new ItemSoundBlock();
	public static ItemTimer it = new ItemTimer();

	public static void preInitClientOnly() {
		GameRegistry.register(isb);
		GameRegistry.register(it);
		ModelLoader.setCustomModelResourceLocation(StartupCommon.isb1, 0,
				new ModelResourceLocation("chtmod:SoundBlock", "inventory"));
		ModelLoader.setCustomModelResourceLocation(isb, 0,
				new ModelResourceLocation("chtmod:ToolSoundBlock", "inventory"));
		ModelLoader.setCustomModelResourceLocation(it, 0,
				new ModelResourceLocation("chtmod:ToolTimerBlock", "inventory"));
	}
}