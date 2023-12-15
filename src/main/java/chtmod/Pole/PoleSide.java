package chtmod.Pole;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
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

public class PoleSide extends BlockPoleBase {

	private int width;
	public static final PropertyDirection PROPERTYFACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool FRONT = PropertyBool.create("front");
	public static final PropertyBool UPDOWN = PropertyBool.create("updown");

	public PoleSide(String arg0, int width) {
		super(arg0);
		this.width = width;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState stateIn, IBlockAccess source, BlockPos pos) {
		IBlockState state = stateIn.getActualState(source, pos);
		boolean front = state.getValue(FRONT);
		float xz1 = (8 - (width / 2)) / 16F;
		float xz2 = (8 + (width / 2)) / 16F;
		float xz3 = (16 - width) / 16F;
		float y = (8 + (width / 2)) / 16F;
		AxisAlignedBB normalAABB = FULL_BLOCK_AABB;
		AxisAlignedBB northAABB = new AxisAlignedBB(xz1, 0, 0, xz2, y, width / 16F);
		AxisAlignedBB southAABB = new AxisAlignedBB(xz1, 0, xz3, xz2, y, 1);
		AxisAlignedBB westAABB = new AxisAlignedBB(0, 0, xz1, width / 16F, y, xz2);
		AxisAlignedBB eastAABB = new AxisAlignedBB(xz3, 0, xz1, 1, y, xz2);
		AxisAlignedBB nsAABB = new AxisAlignedBB(xz1, 0, width / 16F, xz2, y, xz3);
		AxisAlignedBB weAABB = new AxisAlignedBB(width / 16F, 0, xz1, xz3, y, xz2);
		switch (state.getValue(PROPERTYFACING)) {
		case EAST:
			normalAABB = eastAABB;
			if (front)
				normalAABB = normalAABB.union(weAABB).union(westAABB);
			break;
		case NORTH:
			normalAABB = northAABB;
			if (front)
				normalAABB = normalAABB.union(nsAABB).union(southAABB);
			break;
		case SOUTH:
			normalAABB = southAABB;
			if (front)
				normalAABB = normalAABB.union(nsAABB).union(northAABB);
			break;
		case WEST:
			normalAABB = westAABB;
			if (front)
				normalAABB = normalAABB.union(weAABB).union(eastAABB);
			break;
		default:
			break;
		}
		if (state.getValue(UPDOWN)) {
			normalAABB = normalAABB.setMaxY(1);
		}
		return normalAABB;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing facing = EnumFacing.getHorizontal(meta >> 2);
		boolean updown = ((meta & 0x02) > 0);
		boolean front = ((meta & 0x01) > 0);
		return this.getDefaultState().withProperty(PROPERTYFACING, facing).withProperty(FRONT, front).withProperty(UPDOWN,
				updown);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int facing = state.getValue(PROPERTYFACING).getHorizontalIndex() << 2;
		int updown = (state.getValue(UPDOWN) ? 1 : 0) << 1;
		int front = (state.getValue(FRONT) ? 1 : 0);
		return facing | updown | front;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess access, BlockPos pos) {
		boolean front, updown;
		EnumFacing facing = state.getValue(PROPERTYFACING);
		switch (facing) {
		case EAST:
			front = isValidBlock(access, pos.west());
			break;
		case NORTH:
			front = isValidBlock(access, pos.south());
			break;
		case SOUTH:
			front = isValidBlock(access, pos.north());
			break;
		case WEST:
			front = isValidBlock(access, pos.east());
			break;
		default:
			front = false;
			break;
		}
		updown = isValidBlock(access, pos.up());
		return state.withProperty(FRONT, front).withProperty(UPDOWN, updown);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { PROPERTYFACING, FRONT, UPDOWN });
	}

	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer) {
		IBlockState ibs = this.getDefaultState();
		EnumFacing facing = placer.getHorizontalFacing();
		return ibs.withProperty(PROPERTYFACING, facing);
	}
}