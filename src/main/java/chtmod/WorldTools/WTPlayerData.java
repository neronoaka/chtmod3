package chtmod.WorldTools;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;

public class WTPlayerData {
	private String username;
	private boolean selecting;
	private BlockPos pos1;
	private BlockPos pos2;
	private IBlockState[][][] blocks;
	private BlockPos offset;

	WTPlayerData(String username) {
		this.username = username;
		this.selecting = true;
	}

	public String getUsername() {
		return this.username;
	}

	public void setSelecting(boolean value) {
		this.selecting = value;
	}

	public boolean getSelecting() {
		return this.selecting;
	}

	public void setPos1(BlockPos value) {
		this.pos1 = value;
	}

	public BlockPos getPos1() {
		return this.pos1;
	}

	public void setPos2(BlockPos value) {
		this.pos2 = value;
	}

	public void createBlocks(IBlockState[][][] blocks) {
		this.blocks = blocks;
	}

	public BlockPos getPos2() {
		return this.pos2;
	}

	public void changeBlocks(int x, int y, int z, IBlockState state) {
		this.blocks[x][y][z] = state;
	}

	public IBlockState[][][] getBlocks() {
		return this.blocks;
	}

	public void setOffset(BlockPos pos) {
		this.offset = new BlockPos(pos);
	}

	public BlockPos getOffset() {
		return this.offset;
	}
}