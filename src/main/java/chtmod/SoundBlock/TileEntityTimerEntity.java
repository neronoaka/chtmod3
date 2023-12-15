package chtmod.SoundBlock;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityTimerEntity extends TileEntity implements ITickable {
	public int onTime, offTime;
	public boolean start;

	public TileEntityTimerEntity() {
		start = false;
	}

	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(pos, 0, getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("on", onTime);
		compound.setInteger("off", offTime);
		compound.setBoolean("start", start);
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		onTime = compound.getInteger("on");
		offTime = compound.getInteger("off");
		start = compound.getBoolean("start");
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	@Override
	public void update() {
		if (onTime <= 0 && offTime <= 0)
			start = false;
		if (start) {
			int maximumValue = onTime + offTime;
			int second = (int) (this.getWorld().getWorldTime() / 20) % maximumValue;
			IBlockState state = this.getWorld().getBlockState(this.pos);
			if (state.getBlock() instanceof BlockTimer) {
				boolean b = (second < onTime);
				this.getWorld().setBlockState(getPos(), state.withProperty(BlockTimer.PROPERTYPOWER, b));
			}
		}
	}
}