package chtmod.Tools;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon {
	public static ItemRuler ir = new ItemRuler("ToolRoadRuler", 0);
	public static ItemRuler is = new ItemRuler("ToolBlockRuler", 1);
	public static ItemRuler is2 = new ItemRuler("ToolBlockRuler2", 2);
	public static ItemRuler is3 = new ItemRuler("ToolBlockRuler3", 3);
	public static PutPole ppolek5 = new PutPole(5, "chtmod:PoleVerticalThick", "ToolPoleThick5");
	public static PutPole ppolek10 = new PutPole(10, "chtmod:PoleVerticalThick", "ToolPoleThick10");
	public static PutPole ppolen5 = new PutPole(5, "chtmod:PoleVerticalThin", "ToolPoleThin5");
	public static RepeatBlock rpb = new RepeatBlock(20);
	public static BuildStation bs = new BuildStation();
	public static BuildRoad br = new BuildRoad();
	public static PutLight pl = new PutLight();
	public static PutStoneLine psl1 = new PutStoneLine(Blocks.STONE, "ToolPutStone10");
	public static SubwayTurn st = new SubwayTurn("ToolSubwayTurn");
	public static RoadRuler rr = new RoadRuler("RoadRulerBlock");
	public static RoadRulerCurved rrc = new RoadRulerCurved("RoadRulerCurvedBlock");
	public static PutCurved pc = new PutCurved("ToolPutCurved");
	public static StairSpace ss = new StairSpace("ToolStairSpace");
	public static PutStoneUnder psu = new PutStoneUnder("ToolPutStoneUnder");

	public static void preInitCommon() {
		GameRegistry.register(rr);
		GameRegistry.register(rrc);
		GameRegistry.register(ir);
		GameRegistry.register(is);
		GameRegistry.register(is2);
		GameRegistry.register(is3);
		GameRegistry.register(ppolen5);
		GameRegistry.register(ppolek5);
		GameRegistry.register(ppolek10);
		GameRegistry.register(rpb);
		GameRegistry.register(bs);
		GameRegistry.register(br);
		GameRegistry.register(pl);
		GameRegistry.register(psl1);
		GameRegistry.register(st);
		GameRegistry.register(pc);
		GameRegistry.register(ss);
		GameRegistry.register(psu);
	}
}