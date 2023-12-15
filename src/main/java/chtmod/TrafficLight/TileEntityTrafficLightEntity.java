package chtmod.TrafficLight;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityTrafficLightEntity extends TileEntity implements ITickable {

	public int maximumValue;
	public boolean start;
	public int[] times;
	public int[] colors;

	public TileEntityTrafficLightEntity() {
		start = false;
		maximumValue = 4;
		times = new int[0];
		colors = new int[0];
	}

	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.pos, getBlockMetadata(), getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setBoolean("start", start);
		compound.setIntArray("time", times);
		compound.setIntArray("color", colors);
		compound.setInteger("maximum", maximumValue);
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		times = compound.getIntArray("time");
		colors = compound.getIntArray("color");
		maximumValue = compound.getInteger("maximum");
		start = compound.getBoolean("start");
	}

	@Override
	public void update() {
		if (!start)
			return;
		int second = (int) (this.getWorld().getWorldTime() / 10) % maximumValue;
		for (int i = 0; i < times.length; i++) {
			int starttime = times[i], endtime = -1;
			boolean condition = false;
			if (i == times.length - 1)
				endtime = times[0];
			else
				endtime = times[i + 1];
			if (starttime > endtime)
				condition = (second >= starttime && second < maximumValue) || (second < endtime);
			else
				condition = (second >= starttime && second < endtime);
			if (condition) {
				IBlockState state = this.getWorld().getBlockState(this.pos);
				if (state.getBlock() instanceof BlockTrafficLight) {
					EnumColour c = EnumColour.byMetadata(colors[i]);
					this.getWorld().setBlockState(this.pos, state.withProperty(BlockTrafficLight.PROPERTYCOLOUR, c));
					return;
				}
			}
		}
	}

	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}
}
