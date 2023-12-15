package chtmod.Miscellaneous;

import net.minecraft.item.Item;

public class ItemTicket extends Item {
	public ItemTicket() {
		this.setMaxStackSize(1);
		this.setCreativeTab(chtmod.CustomCreativeTabs.miscellaneousTab);
		this.setRegistryName("Ticket");
		this.setUnlocalizedName("Ticket");
	}

}
