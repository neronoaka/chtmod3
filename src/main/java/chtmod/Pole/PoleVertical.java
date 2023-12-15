package chtmod.Pole;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PoleVertical extends BlockPoleBase {

	private int width, height, top;
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");
	public static final PropertyBool EAST = PropertyBool.create("east");

	public PoleVertical(String arg0, int width, int height, int top) {
		super(arg0);
		this.width = width;
		this.height = height;
		this.top = top;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState stateIn, IBlockAccess source, BlockPos pos) {
		IBlockState state = stateIn.getActualState(source, pos);
		float xz1 = (8 - (width / 2)) / 16F;
		float xz2 = (8 + (width / 2)) / 16F;
		float y1 = top / 16F;
		float y2 = (top + height) / 16F;
		AxisAlignedBB normalAABB = new AxisAlignedBB(xz1, y1, xz1, xz2, y2, xz2);
		AxisAlignedBB northAABB = new AxisAlignedBB(xz1, y1, 0, xz2, y2, xz1);
		AxisAlignedBB southAABB = new AxisAlignedBB(xz1, y1, xz2, xz2, y2, 1);
		AxisAlignedBB westAABB = new AxisAlignedBB(0, y1, xz1, xz1, y2, xz2);
		AxisAlignedBB eastAABB = new AxisAlignedBB(xz2, y1, xz1, 1, y2, xz2);
		if (state.getValue(NORTH))
			normalAABB = normalAABB.union(northAABB);
		if (state.getValue(SOUTH))
			normalAABB = normalAABB.union(southAABB);
		if (state.getValue(WEST))
			normalAABB = normalAABB.union(westAABB);
		if (state.getValue(EAST))
			normalAABB = normalAABB.union(eastAABB);
		return normalAABB;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		boolean e = ((meta & 0x08) > 0);
		boolean n = ((meta & 0x04) > 0);
		boolean s = ((meta & 0x02) > 0);
		boolean w = ((meta & 0x01) > 0);
		return this.getDefaultState().withProperty(EAST, e).withProperty(NORTH, n).withProperty(SOUTH, s)
				.withProperty(WEST, w);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int e = (state.getValue(EAST) ? 1 : 0) << 3;
		int n = (state.getValue(NORTH) ? 1 : 0) << 2;
		int s = (state.getValue(SOUTH) ? 1 : 0) << 1;
		int w = (state.getValue(WEST) ? 1 : 0);
		return e | n | s | w;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess access, BlockPos pos) {
		boolean n = isValidBlock(access, pos.north());
		boolean s = isValidBlock(access, pos.south());
		boolean w = isValidBlock(access, pos.west());
		boolean e = isValidBlock(access, pos.east());
		return state.withProperty(EAST, e).withProperty(NORTH, n).withProperty(SOUTH, s).withProperty(WEST, w);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { EAST, NORTH, SOUTH, WEST });
	}
}