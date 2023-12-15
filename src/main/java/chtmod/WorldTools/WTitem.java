package chtmod.WorldTools;

import java.util.ArrayList;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;

public class WTitem extends Item {
	public static ArrayList<WTPlayerData> playerData = new ArrayList();

	WTitem() {
		this.setMaxStackSize(1);
		this.setCreativeTab(chtmod.CustomCreativeTabs.toolTab);
	}

	public static int createData(String username) {
		for (int i = 0; i < playerData.size() - 1; i++) {
			if (((WTPlayerData) playerData.get(i)).getUsername().equals(username)) {
				return i;
			}
		}
		playerData.add(new WTPlayerData(username));
		return playerData.size() - 1;
	}

	public static void setSelecting(boolean value, EntityPlayer player) {
		int index = createData(player.getDisplayNameString());
		((WTPlayerData) playerData.get(index)).setSelecting(value);
	}

	public static boolean getSelecting(EntityPlayer player) {
		int index = createData(player.getDisplayNameString());
		return ((WTPlayerData) playerData.get(index)).getSelecting();
	}

	public static void setPos1(BlockPos value, EntityPlayer player) {
		int index = createData(player.getDisplayNameString());
		((WTPlayerData) playerData.get(index)).setPos1(value);
	}

	public static BlockPos getPos1(EntityPlayer player) {
		int index = createData(player.getDisplayNameString());
		return ((WTPlayerData) playerData.get(index)).getPos1();
	}

	public static void setPos2(BlockPos value, EntityPlayer player) {
		int index = createData(player.getDisplayNameString());
		((WTPlayerData) playerData.get(index)).setPos2(value);
	}

	public static BlockPos getPos2(EntityPlayer player) {
		int index = createData(player.getDisplayNameString());
		return ((WTPlayerData) playerData.get(index)).getPos2();
	}

	public static void createBlocks(IBlockState[][][] blocks, EntityPlayer player) {
		int index = createData(player.getDisplayNameString());
		((WTPlayerData) playerData.get(index)).createBlocks(blocks);
	}

	public static void changeBlocks(int x, int y, int z, IBlockState state, EntityPlayer player) {
		int index = createData(player.getDisplayNameString());
		((WTPlayerData) playerData.get(index)).changeBlocks(x, y, z, state);
	}

	public static IBlockState[][][] getBlocks(EntityPlayer player) {
		int index = createData(player.getDisplayNameString());
		return ((WTPlayerData) playerData.get(index)).getBlocks();
	}

	public static void setOffset(BlockPos pos, EntityPlayer player) {
		int index = createData(player.getDisplayNameString());
		((WTPlayerData) playerData.get(index)).setOffset(pos);
	}

	public static BlockPos getOffset(EntityPlayer player) {
		int index = createData(player.getDisplayNameString());
		return ((WTPlayerData) playerData.get(index)).getOffset();
	}
}
