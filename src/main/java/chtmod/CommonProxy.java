package chtmod;

import chtmod.SoundBlock.TileEntitySoundEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class CommonProxy {
	public void preInit() {
		chtmod.WorldTools.StartupCommon.preInitCommon();
		chtmod.BlockRoad.StartupCommon.preInitCommon();
		chtmod.Pole.StartupCommon.preInitCommon();
		chtmod.RoadLight.StartupCommon.preInitCommon();
		chtmod.Sign.StartupCommon.preInitCommon();
		chtmod.Tools.StartupCommon.preInitCommon();
		chtmod.SoundBlock.StartupCommon.preInitCommon();
		chtmod.RoadLines.StartupCommon.preInitCommon();
		chtmod.RoadLines.Curved.StartupCommon.preInitCommon();
		chtmod.TrafficLight.StartupCommon.preInitCommon();
		chtmod.GlassDoor.StartupCommon.preInitCommon();
		chtmod.Electricity.StartupCommon.preInitCommon();
		chtmod.Miscellaneous.StartupCommon.preInitCommon();
	}

	public void init() {
	}

	public void postInit() {
	}

	abstract public boolean playerIsInCreativeMode(EntityPlayer player);

	abstract public boolean isDedicatedServer();
}