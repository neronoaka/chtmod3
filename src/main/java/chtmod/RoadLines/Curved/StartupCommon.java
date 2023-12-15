package chtmod.RoadLines.Curved;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon {
	public static BlockLineCurved blc = new BlockLineCurved();
	public static BlockLineMarker lm = new BlockLineMarker();
	public static ItemCurvedLine icl = new ItemCurvedLine();

	public static void preInitCommon() {
		GameRegistry.registerTileEntity(TileEntityLineEntity.class, "TileEntityRoadLineCurved");
		GameRegistry.register(blc);
		GameRegistry.register(lm);
		GameRegistry.register(icl);
	}
}