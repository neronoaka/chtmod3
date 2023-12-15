package chtmod.BlockRoad;

import chtmod.BlockRoad.Slope.BlockSlopeBitumen;
import chtmod.BlockRoad.Slope.ItemSlopeBitumen;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class StartupCommon {
	public static ItemSlopeBitumen isb = new ItemSlopeBitumen();
	public static BlockSlopeBitumen[] sbv2 = { new BlockSlopeBitumen(0), new BlockSlopeBitumen(1), new BlockSlopeBitumen(2),
			new BlockSlopeBitumen(3), new BlockSlopeBitumen(4) };
	
	public static BlockBitumen[] blocks1 = { new BlockBitumen("BlockBitumen", 0), new BlockBitumen("BlockBitumen1", 1),
			new BlockBitumen("BlockBitumen2", 2) };
	public static ItemBlock[] items1 = new ItemBlock[blocks1.length];	
	
	public static BlockRoad[] blocks2 = new BlockRoad[] {
			new BlockRoad("RoadBarricate1", new AxisAlignedBB(6 / 16f, 0, 6 / 16f, 10 / 16f, 1, 10 / 16f)),
			new BlockRoad("RoadBarricate2", new AxisAlignedBB(4 / 16f, 0, 4 / 16f, 12 / 16f, 1, 12 / 16f)),
			new BlockRoad("RoadBarricate3", new AxisAlignedBB(2 / 16f, 0, 2 / 16f, 14 / 16f, 1, 14 / 16f)),
			new BlockRoad("RoadBlockYellow", new AxisAlignedBB(0, 0, 0, 1, 1 / 16f, 1)),
			new BlockRoad("RoadBlockWhite", new AxisAlignedBB(0, 0, 0, 1, 1 / 16f, 1)),
			new BlockRoad("LightBottom", new AxisAlignedBB(0, 0, 0, 1, 1, 1)),
			new BlockRoad("BlockCaution1", Block.FULL_BLOCK_AABB), new BlockRoad("Block45Pole", Block.FULL_BLOCK_AABB) };
	public static ItemBlock[] items2 = new ItemBlock[blocks2.length];
	public static BlockCaution bn2 = new BlockCaution();
	public static ItemBlock ibn2 = (ItemBlock) new ItemBlock(bn2).setRegistryName(bn2.getRegistryName());
		
	public static void preInitCommon() {
		for (int i = 0; i < blocks1.length; i++) {
			GameRegistry.register(blocks1[i]);
			ItemBlock ib = (ItemBlock) new ItemBlock(blocks1[i]).setRegistryName(blocks1[i].getRegistryName());
			items1[i] = ib;
			GameRegistry.register(ib);
		}
		
		for (int i = 0; i < blocks2.length; i++) {
			GameRegistry.register(blocks2[i]);
			ItemBlock ib = (ItemBlock) new ItemBlock(blocks2[i]).setRegistryName(blocks2[i].getRegistryName());
			items2[i] = ib;
			GameRegistry.register(ib);
		}
		GameRegistry.register(bn2);
		GameRegistry.register(ibn2);		
		GameRegistry.register(isb);
		for (BlockSlopeBitumen s2 : sbv2)
			GameRegistry.register(s2);
	}
}