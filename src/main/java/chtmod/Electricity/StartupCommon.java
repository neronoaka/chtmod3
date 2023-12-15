package chtmod.Electricity;

import chtmod.Electricity.Sign.BlockElectricitySign;
import chtmod.Electricity.Sign.TileEntityElectricitySignEntity;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StartupCommon {
	public static BlockElectricityPole ep = new BlockElectricityPole("ElectricityPole");
	public static ItemBlock iep = (ItemBlock) new ItemBlock(ep).setRegistryName(ep.getRegistryName());
	public static BlockElectricityPole ep2 = new BlockElectricityPole("ElectricityPole2");
	public static ItemBlock iep2 = (ItemBlock) new ItemBlock(ep2).setRegistryName(ep2.getRegistryName());
	public static BlockInsulator ib = new BlockInsulator("InsulatorBase");
	public static ItemInsulator iib = new ItemInsulator(ib);
	public static BlockElectricityLightPole elp = new BlockElectricityLightPole();
	public static ItemInsulator ielp = new ItemInsulator(elp);
	public static BlockElectricityPoleHorizontal eph = new BlockElectricityPoleHorizontal();
	public static ItemBlock ieph = (ItemBlock) new ItemBlock(eph).setRegistryName(eph.getRegistryName());
	public static BlockElectricitySign es = new BlockElectricitySign();
	public static BlockTransformer trans = new BlockTransformer();
	public static BlockSwitch sw = new BlockSwitch();
	public static BlockSwitch osw = new BlockSwitch("OldSwitch");
	public static BlockElectricityBox box = new BlockElectricityBox();
	public static ItemBlock ibox = (ItemBlock) new ItemBlock(box).setRegistryName(box.getRegistryName());
	public static ItemBlock itrans = (ItemBlock) new ItemBlock(trans).setRegistryName(trans.getRegistryName());
	public static ItemBlock isw = (ItemBlock) new ItemBlock(sw).setRegistryName(sw.getRegistryName());
	public static ItemBlock iosw = (ItemBlock) new ItemBlock(osw).setRegistryName(osw.getRegistryName());

	public static void preInitCommon() {
		GameRegistry.registerTileEntity(TileEntityElectricitySignEntity.class, "TileEntityElectricitySignEntity");
		GameRegistry.register(es);
		GameRegistry.register(ep);
		GameRegistry.register(iep);
		GameRegistry.register(ep2);
		GameRegistry.register(iep2);
		GameRegistry.register(ib);
		GameRegistry.register(iib);
		GameRegistry.register(eph);
		GameRegistry.register(ieph);
		GameRegistry.register(elp);
		GameRegistry.register(ielp);
		GameRegistry.register(trans);
		GameRegistry.register(itrans);
		GameRegistry.register(sw);
		GameRegistry.register(isw);
		GameRegistry.register(osw);
		GameRegistry.register(iosw);
		GameRegistry.register(box);
		GameRegistry.register(ibox);
	}
}