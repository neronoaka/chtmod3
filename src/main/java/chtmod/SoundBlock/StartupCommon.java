package chtmod.SoundBlock;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class StartupCommon {
	public static BlockSound sb1 = new BlockSound();
	public static BlockTimer tb = new BlockTimer();
	public static ItemBlock isb1 = (ItemBlock) new ItemBlock(sb1).setRegistryName(sb1.getRegistryName());

	public static void preInitCommon() {
		GameRegistry.registerTileEntity(TileEntityTimerEntity.class, "TileEntityTimerEntity");
		GameRegistry.registerTileEntity(TileEntitySoundEntity.class, "TileEntitySoundEntity");
		GameRegistry.register(sb1);
		GameRegistry.register(tb);
		GameRegistry.register(isb1);
	}
}