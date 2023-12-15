package chtmod.GlassDoor;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGlassDoor extends Block {
	public static final PropertyBool OPEN = PropertyBool.create("open");
	public static final PropertyBool LEFT = PropertyBool.create("left");
	public static final PropertyBool PROPERTYNS = PropertyBool.create("ns");
	public static final PropertyInteger PROPERTYLAYER = PropertyInteger.create("layer", 1, 2);

	public BlockGlassDoor() {
		super(Material.GLASS);
		this.setSoundType(SoundType.GLASS);
		this.setCreativeTab(null);
		this.setUnlocalizedName("GlassDoor");
		this.setRegistryName("GlassDoor");
		this.setHardness(0.5F);
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public boolean isOpaqueCube(IBlockState iBlockState) {
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState iBlockState) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState iBlockState) {
		return false;
	}

	@Override
	public boolean isSideSolid(IBlockState state, IBlockAccess access, BlockPos pos, EnumFacing facing) {
		return true;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		boolean left = state.getValue(LEFT);
		boolean ns = state.getValue(PROPERTYNS);
		if (state.getValue(OPEN)) {
			if (ns) {
				if (left)
					return new AxisAlignedBB(0, 0, 0, 2 / 16F, 1, 1);
				else
					return new AxisAlignedBB(14 / 16F, 0, 0, 1, 1, 1);
			} else {
				if (left)
					return new AxisAlignedBB(0, 0, 0, 1, 1, 2 / 16F);
				else
					return new AxisAlignedBB(0, 0, 14 / 16F, 1, 1, 1);
			}
		} else {
			if (ns)
				return new AxisAlignedBB(0, 0, 7 / 16F, 1, 1, 9 / 16F);
			else
				return new AxisAlignedBB(7 / 16F, 0, 0, 9 / 16F, 1, 1);
		}
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(StartupCommon.igd, 1, 0);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		boolean isleft = ((meta & 0x8) >> 3) > 0;
		boolean isopen = ((meta & 0x4) >> 2) > 0;
		int layer = ((meta & 0x2) >> 1) + 1;
		boolean facing = (meta & 0x1) > 0;
		return this.getDefaultState().withProperty(PROPERTYNS, facing).withProperty(OPEN, isopen)
				.withProperty(LEFT, isleft).withProperty(PROPERTYLAYER, layer);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int left = state.getValue(LEFT) ? 1 : 0;
		int isopen = state.getValue(OPEN) ? 1 : 0;
		int layer = state.getValue(PROPERTYLAYER) - 1;
		int facing = state.getValue(PROPERTYNS) ? 1 : 0;
		return left << 3 | isopen << 2 | layer << 1 | facing;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { PROPERTYNS, OPEN, LEFT, PROPERTYLAYER });
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand handIn, ItemStack stackin, EnumFacing facing, float arg0, float arg1, float arg2) {
		BlockPos p = pos.down(2);
		for (int i = 1; i <= 3; i++)
			open(p.up(i), worldIn);
		return true;
	}

	@Override
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		delete(pos, worldIn);
	}

	@Override
	public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosion) {
		delete(pos, worldIn);
	}

	private void open(BlockPos pos, World world) {
		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() instanceof BlockGlassDoor) {
			boolean isopen = world.getBlockState(pos).getValue(OPEN) ? false : true;
			world.setBlockState(pos, state.withProperty(OPEN, isopen));
		}
	}

	private void delete(BlockPos pos, World world) {
		if (world.getBlockState(pos.up()).getBlock() instanceof BlockGlassDoor)
			world.setBlockToAir(pos.up());
		if (world.getBlockState(pos.down()).getBlock() instanceof BlockGlassDoor)
			world.setBlockToAir(pos.down());
	}
}