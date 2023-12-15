package chtmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CustomCreativeTabs {
	public static CreativeTabs lineTab = new CreativeTabs("cht_linetab") {
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Item.getByNameOrId("chtmod:SignNoenter");
		}
	};
	public static CreativeTabs blockTab = new CreativeTabs("cht_blocktab") {
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Item.getByNameOrId("chtmod:BlockBitumen");
		}
	};

	public static CreativeTabs trafficTab = new CreativeTabs("cht_traffictab") {
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Item.getByNameOrId("chtmod:TrafficLightLeft");
		}
	};

	public static CreativeTabs toolTab = new CreativeTabs("cht_tooltab") {
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Item.getByNameOrId("chtmod:WTCur");
		}
	};
	public static CreativeTabs miscellaneousTab = new CreativeTabs("cht_miscellaneoustab") {
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Item.getByNameOrId("chtmod:SoundBlock");
		}
	};
	public static CreativeTabs electricityTab = new CreativeTabs("cht_electricitytab") {
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return Item.getByNameOrId("chtmod:ElectricityOldSwitch");
		}
	};
}
