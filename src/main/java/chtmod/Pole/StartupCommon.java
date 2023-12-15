package chtmod.Pole;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class StartupCommon {
	public static PoleRotation[] prt = { new PoleRotation("Fence", new int[] { 16, 2, 16, 2, 16, 2, 16, 5 }, true),
			new PoleRotation("Slope2", new int[] { 4, 16, 4, 16, 4, 26, 4, 26 }, false,
					new int[] { 6, 4, 6, 4, 9, 16, -10, 16 }),
			new PoleRotation("Slope4", new int[] { 4, 10, 4, 16, 4, 11, 4, 16 }, false,
					new int[] { 0, 10, 6, 10, 12, 14, 6, 4 }),
			new PoleRotation("Connector", new int[] { 4, 10, 4, 10, 16, 2, 16, 2 }, false,
					new int[] { 0, 1, 15, 1, 7, 2, 7, 2 }),
			new PoleRotation("Light1", new int[] { 16, 78, 4, 26, 6, 50, 16, 32 }, false,
					new int[] { 0, 22, 11, 25, -9, 34, 6, 16 }),
			new PoleRotation("Light2", new int[] { 4, 10, 4, 10, 4, 48, 4, 32 }, true,
					new int[] { 0, 10, 6, 10, 4, 36, 4, 21 }),
			new PoleRotation("Light3", new int[] { 24, 90, 24, 56, 4, 16, 4, 16 }, false,
					new int[] { 4, 60, 4, 42, 6, 4, 6, 4 }) };
	public static ItemPole[] iprt = new ItemPole[prt.length];

	public static PoleCentre pc = new PoleCentre("Centre", new int[] { 2, 16, 2, 16, 4, 16, 4, 16, 4, 16 });
	public static ItemPole ipc = new ItemPole(pc);
	public static BlockPoleBase[] blocks = { new PoleVertical("VerticalThick", 6, 16, 0),
			new PoleVertical("VerticalThickThin", 6, 16, 0), new PoleVertical("RoadFence1", 4, 16, 0),
			new PoleVertical("RoadFence2", 4, 16, 0), new PoleVertical("RoadFence3", 4, 16, 0),
			new PoleVertical("RoadFence4", 4, 16, 0), new PoleVertical("VerticalThin", 4, 16, 0),
			new PoleVertical("VerticalSlope", 6, 10, 0), new PoleVertical("VerticalCentre", 4, 4, 6),
			new PoleVertical("VerticalUpper", 4, 10, 6), new PoleVertical("VerticalLower", 4, 10, 0),
			new PoleHorizontal("HorizontalThin", 4, 4, 6), new PoleHorizontal("HorizontalDouble", 4, 16, 0),
			new PoleSide("SideThin", 4) };

	public static ItemBlock[] items = new ItemBlock[blocks.length];

	public static void preInitCommon() {
		for (int i = 0; i < blocks.length; i++) {
			GameRegistry.register(blocks[i]);
			ItemBlock ib = (ItemBlock) new ItemBlock(blocks[i]).setRegistryName(blocks[i].getRegistryName());
			GameRegistry.register(ib);
			items[i] = ib;
		}
		GameRegistry.register(pc);
		GameRegistry.register(ipc);
		for (int j = 0; j < prt.length; j++) {
			GameRegistry.register(prt[j]);
			ItemPole ip = new ItemPole(prt[j]);
			GameRegistry.register(ip);
			iprt[j] = ip;
		}
	}
}