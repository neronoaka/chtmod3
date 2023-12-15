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

public class TileEntitySignalProviderEntity extends TileEntity implements ITickable {

	public boolean start;
	public int[] position;

	public TileEntitySignalProviderEntity() {
		start = false;
		position = new int[3];
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
		compound.setIntArray("position", position);
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		start = compound.getBoolean("start");
		position = compound.getIntArray("position");
	}

	@Override
	public void update() {
		if (start) {
			IBlockState state = this.getWorld().getBlockState(new BlockPos(position[0], position[1], position[2]));
			IBlockState provider = this.getWorld().getBlockState(getPos());
			if (state.getBlock() instanceof BlockTrafficLight) {
				boolean isgreen = (state.getValue(BlockTrafficLight.PROPERTYCOLOUR) == EnumColour.GREEN);
				this.getWorld().setBlockState(getPos(),
						provider.withProperty(BlockSignalProvider.PROPERTYPOWER, isgreen));
			}
		}
	}

	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}
}
