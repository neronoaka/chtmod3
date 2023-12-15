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

public class PoleHorizontal extends BlockPoleBase {

	private int width, height, top;
	public static final PropertyBool PROPERTYNS = PropertyBool.create("ns");
	public static final PropertyBool LEFT = PropertyBool.create("left");
	public static final PropertyBool RIGHT = PropertyBool.create("right");

	public PoleHorizontal(String arg0, int width, int height, int top) {
		super(arg0);
		this.width = width;
		this.height = height;
		this.top = top;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState stateIn, IBlockAccess access, BlockPos pos) {
		IBlockState state = stateIn.getActualState(access, pos);
		float xz1 = (8 - (width / 2)) / 16F;
		float xz2 = (8 + (width / 2)) / 16F;
		float y1 = top / 16F;
		float y2 = (top + height) / 16F;
		AxisAlignedBB normalAABB = new AxisAlignedBB(xz1, y1, xz1, xz2, y2, xz2);
		AxisAlignedBB northAABB = new AxisAlignedBB(xz1, y1, 0, xz2, y2, xz1);
		AxisAlignedBB southAABB = new AxisAlignedBB(xz1, y1, xz2, xz2, y2, 1);
		AxisAlignedBB westAABB = new AxisAlignedBB(0, y1, xz1, xz1, y2, xz2);
		AxisAlignedBB eastAABB = new AxisAlignedBB(xz2, y1, xz1, 1, y2, xz2);
		if (state.getValue(PROPERTYNS)) {
			normalAABB = normalAABB.union(northAABB).union(southAABB);
			if (state.getValue(LEFT))
				normalAABB = normalAABB.union(westAABB);
			if (state.getValue(RIGHT))
				normalAABB = normalAABB.union(eastAABB);
		} else {
			normalAABB = normalAABB.union(westAABB).union(eastAABB);
			if (state.getValue(LEFT))
				normalAABB = normalAABB.union(northAABB);
			if (state.getValue(RIGHT))
				normalAABB = normalAABB.union(southAABB);
		}
		return normalAABB;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		boolean ns = ((meta & 0x04) > 0);
		boolean l = ((meta & 0x02) > 0);
		boolean r = ((meta & 0x01) > 0);
		return this.getDefaultState().withProperty(PROPERTYNS, ns).withProperty(LEFT, l).withProperty(RIGHT, r);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int ns = (state.getValue(PROPERTYNS) ? 1 : 0) << 2;
		int l = (state.getValue(LEFT) ? 1 : 0) << 1;
		int r = (state.getValue(RIGHT) ? 1 : 0);
		return ns | l | r;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess access, BlockPos pos) {
		boolean l, r, ns = state.getValue(PROPERTYNS);
		if (ns) {
			l = isValidBlock(access, pos.west());
			r = isValidBlock(access, pos.east());
		} else {
			l = isValidBlock(access, pos.north());
			r = isValidBlock(access, pos.south());
		}
		return state.withProperty(PROPERTYNS, ns).withProperty(LEFT, l).withProperty(RIGHT, r);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { PROPERTYNS, LEFT, RIGHT });
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer) {
		IBlockState ibs = this.getDefaultState();
		EnumFacing playerFacing = placer.getHorizontalFacing();
		boolean ns = (playerFacing == EnumFacing.NORTH || playerFacing == EnumFacing.SOUTH);
		return ibs.withProperty(PROPERTYNS, ns);
	}
}