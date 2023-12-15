package chtmod.TrafficLight;

import java.util.ArrayList;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon {
	public static BlockTrafficLight[] tl = { new BlockTrafficLight("B", 0), new BlockTrafficLight("R", 0), new BlockTrafficLight("T", 0),
			new BlockTrafficLight("Straight", 1), new BlockTrafficLight("Straight1", 2), new BlockTrafficLight("Straight2", 7),
			new BlockTrafficLight("Straight3", 8), new BlockTrafficLight("Straight4", 3), new BlockTrafficLight("Straight5", 8),
			new BlockTrafficLight("Straight6", 7), new BlockTrafficLight("Straight7", 7), new BlockTrafficLight("Left", 1),
			new BlockTrafficLight("Left1", 2), new BlockTrafficLight("Left2", 7), new BlockTrafficLight("Left3", 8),
			new BlockTrafficLight("Left4", 3), new BlockTrafficLight("Left5", 8), new BlockTrafficLight("Left6", 7),
			new BlockTrafficLight("Left7", 7), new BlockTrafficLight("Right", 1), new BlockTrafficLight("Bike", 1),
			new BlockTrafficLight("Bike1", 9), new BlockTrafficLight("Bike2", 8), new BlockTrafficLight("Bike3", 4),
			new BlockTrafficLight("Bike4", 10), new BlockTrafficLight("Bike5", 0), new BlockTrafficLight("Back", 1),
			new BlockTrafficLight("Circle", 1), new BlockTrafficLight("Circle1", 3), new BlockTrafficLight("Circle2", 7),
			new BlockTrafficLight("Circle3", 8), new BlockTrafficLight("Circle4", 8), new BlockTrafficLight("Circle5", 7),
			new BlockTrafficLight("Circle6", 8), new BlockTrafficLight("Circle7", 7), new BlockTrafficLight("People", 1),
			new BlockTrafficLight("People1", 5), new BlockTrafficLight("People2", 6), new BlockTrafficLight("People3", 11),
			new BlockTrafficLight("People4", 0), new BlockTrafficLight("People5", 5), new BlockTrafficLight("L", 0) };
	public static ItemBlock[] itl;
	public static BlockSignalProvider sp = new BlockSignalProvider();
	public static ItemConnectTraffic ct = new ItemConnectTraffic();

	public static void preInitCommon() {
		GameRegistry.registerTileEntity(TileEntityTrafficLightEntity.class, "TileEntityTrafficLightEntity");
		GameRegistry.registerTileEntity(TileEntitySignalProviderEntity.class, "TileEntitySignalProviderEntity");
		GameRegistry.register(ct);
		GameRegistry.register(sp);
		ItemBlock[] itb = new ItemBlock[tl.length];
		for (int i = 0; i < tl.length; i++) {
			GameRegistry.register(tl[i]);
			itb[i] = (ItemBlock) new ItemBlock(tl[i]).setRegistryName(tl[i].getRegistryName());
			GameRegistry.register(itb[i]);
		}
		itl = itb;
	}
}