package chtmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.client.model.obj.OBJLoader;

public class ClientOnlyProxy extends CommonProxy {
	public void preInit() {
		super.preInit();
		OBJLoader.INSTANCE.addDomain("chtmod");
		chtmod.WorldTools.StartupClientOnly.preInitClientOnly();
		chtmod.BlockRoad.StartupClientOnly.preInitClientOnly();
		chtmod.Pole.StartupClientOnly.preInitClientOnly();
		chtmod.RoadLight.StartupClientOnly.preinitClientOnly();
		chtmod.Sign.StartupClientOnly.preInitClientOnly();
		chtmod.Tools.StartupClientOnly.preInitClientOnly();
		chtmod.SoundBlock.StartupClientOnly.preInitClientOnly();
		chtmod.RoadLines.StartupClientOnly.preInitClientOnly();
		chtmod.RoadLines.Curved.StartupClientOnly.preInitClientOnly();
		chtmod.TrafficLight.StartupClientOnly.preInitClientOnly();
		chtmod.GlassDoor.StartupClientOnly.preInitClientOnly();
		chtmod.Electricity.StartupClientOnly.preInitClientOnly();
		chtmod.Miscellaneous.StartupClientOnly.preInitClientOnly();
	}

	public void init() {
		super.init();
		chtmod.RoadLines.Curved.StartupClientOnly.initClientOnly();
		chtmod.Sign.StartupClientOnly.initClientOnly();
		chtmod.Electricity.StartupClientOnly.initClientOnly();
	}

	public void postInit() {
		super.postInit();
	}

	@Override
	public boolean playerIsInCreativeMode(EntityPlayer player) {
		if (player instanceof EntityPlayerMP) {
			EntityPlayerMP entityPlayerMP = (EntityPlayerMP) player;
			return entityPlayerMP.interactionManager.isCreative();
		} else if (player instanceof EntityPlayerSP) {
			return Minecraft.getMinecraft().playerController.isInCreativeMode();
		}
		return false;
	}

	@Override
	public boolean isDedicatedServer() {
		return false;
	}
}