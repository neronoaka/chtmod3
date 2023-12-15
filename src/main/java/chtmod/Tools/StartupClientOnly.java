package chtmod.Tools;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class StartupClientOnly {
	public static void preInitClientOnly() {
		for (int i = 0; i < 3; i++)
			ModelLoader.setCustomModelResourceLocation(StartupCommon.bs, i,
					new ModelResourceLocation("chtmod:tools/BuildStation", "inventory"));
		for (int i = 0; i < 2; i++)
			ModelLoader.setCustomModelResourceLocation(StartupCommon.st, i,
					new ModelResourceLocation("chtmod:tools/SubwayTurn", "inventory"));
		for (int i = 0; i < 12; i++)
			ModelLoader.setCustomModelResourceLocation(StartupCommon.pl, i,
					new ModelResourceLocation("chtmod:tools/PutLight" + i, "inventory"));
		ModelResourceLocation ruler = new ModelResourceLocation("chtmod:tools/BlockRuler", "inventory");
		ModelLoader.setCustomModelResourceLocation(StartupCommon.ir, 0, ruler);
		ModelLoader.setCustomModelResourceLocation(StartupCommon.is, 0, ruler);
		ModelLoader.setCustomModelResourceLocation(StartupCommon.is2, 0, ruler);
		ModelResourceLocation shovel2 = new ModelResourceLocation("chtmod:tools/Shovel2", "inventory");
		ModelLoader.setCustomModelResourceLocation(StartupCommon.ppolek5, 0, shovel2);
		ModelLoader.setCustomModelResourceLocation(StartupCommon.ppolek10, 0, shovel2);
		ModelLoader.setCustomModelResourceLocation(StartupCommon.ppolen5, 0, shovel2);
		ModelResourceLocation pickaxe = new ModelResourceLocation("chtmod:tools/Pickaxe", "inventory");
		ModelLoader.setCustomModelResourceLocation(StartupCommon.rpb, 0, pickaxe);
		ModelLoader.setCustomModelResourceLocation(StartupCommon.psl1, 0, pickaxe);
		ModelLoader.setCustomModelResourceLocation(StartupCommon.psu, 0, pickaxe);
		ModelLoader.setCustomModelResourceLocation(StartupCommon.ss, 0, pickaxe);
		ModelResourceLocation shovel = new ModelResourceLocation("chtmod:tools/Shovel", "inventory");
		ModelLoader.setCustomModelResourceLocation(StartupCommon.br, 0, shovel);
		ModelLoader.setCustomModelResourceLocation(StartupCommon.br, 1, shovel);
		ModelLoader.setCustomModelResourceLocation(StartupCommon.br, 2, shovel);
		ModelLoader.setCustomModelResourceLocation(StartupCommon.pc, 0,
				new ModelResourceLocation("chtmod:tools/PutCurved", "inventory"));

	}
}
