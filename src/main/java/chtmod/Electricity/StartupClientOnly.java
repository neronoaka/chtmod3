package chtmod.Electricity;

import chtmod.Electricity.Sign.ItemElectricitySign;
import chtmod.Electricity.Sign.TileEntityElectricitySignEntity;
import chtmod.Electricity.Sign.TileEntityElectricitySignRenderer;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupClientOnly {
	public static ItemElectricitySign ies = new ItemElectricitySign();

	public static void preInitClientOnly() {
		GameRegistry.register(ies);
		ModelLoader.setCustomModelResourceLocation(ies, 0,
				new ModelResourceLocation("chtmod:electricity/Sign", "inventory"));
		ModelLoader.setCustomModelResourceLocation(StartupCommon.itrans, 0,
				new ModelResourceLocation("chtmod:electricity/Transformer", "inventory"));
		ModelLoader.setCustomModelResourceLocation(StartupCommon.isw, 0,
				new ModelResourceLocation("chtmod:electricity/Switch", "inventory"));
		ModelLoader.setCustomModelResourceLocation(StartupCommon.iosw, 0,
				new ModelResourceLocation("chtmod:electricity/OldSwitch", "inventory"));
		ModelLoader.setCustomModelResourceLocation(StartupCommon.ibox, 0,
				new ModelResourceLocation("chtmod:electricity/Box", "inventory"));
		ModelLoader.setCustomModelResourceLocation(StartupCommon.iep, 0,
				new ModelResourceLocation("chtmod:electricity/ElectricityPole", "inventory"));
		ModelLoader.setCustomModelResourceLocation(StartupCommon.iep2, 0,
				new ModelResourceLocation("chtmod:electricity/ElectricityPole2", "inventory"));
		ModelLoader.setCustomModelResourceLocation(StartupCommon.ieph, 0,
				new ModelResourceLocation("chtmod:electricity/ElectricityPoleHorizontal", "inventory"));
		for (int i = 0; i < 4; i++)
			ModelLoader.setCustomModelResourceLocation(StartupCommon.iib, i,
					new ModelResourceLocation("chtmod:electricity/InsulatorBase" + i, "inventory"));
		for (int i = 0; i < 4; i++)
			ModelLoader.setCustomModelResourceLocation(StartupCommon.ielp, i,
					new ModelResourceLocation("chtmod:electricity/LightPole" + i, "inventory"));
	}

	public static void initClientOnly() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityElectricitySignEntity.class,
				new TileEntityElectricitySignRenderer());
	}
}