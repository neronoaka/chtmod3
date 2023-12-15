package chtmod.RoadLines;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemRoadLine extends ItemBlock {
	public ItemRoadLine(Block block) {
		super(block);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setRegistryName(block.getRegistryName());
	}

	@Override
	public int getMetadata(int metadata) {
		return metadata;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		EnumAngle angle = EnumAngle.byMetadata(stack.getMetadata());
		return super.getUnlocalizedName() + "." + angle.toString();
	}
}
