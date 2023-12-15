package chtmod.TrafficLight;

import java.util.List;

import chtmod.SoundBlock.TileEntitySoundEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSignalProvider extends Block {
	public static final PropertyBool PROPERTYPOWER = PropertyBool.create("powered");

	public BlockSignalProvider() {
		super(Material.ROCK);
		this.setCreativeTab(null);
		this.setRegistryName("BlockSignalProvider");
		this.setUnlocalizedName("BlockSignalProvider");
		this.setHardness(1);
	}

	@Override
	public ItemStack getItem(World p_getItem_1_, BlockPos p_getItem_2_, IBlockState p_getItem_3_) {
		return new ItemStack(StartupCommon.ct, 1, 0);
	}

	@Override
	public boolean canProvidePower(IBlockState state) {
		return true;
	}

	@Override
	public boolean canConnectRedstone(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return true;
	}

	@Override
	public int getWeakPower(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		if (state.getValue(PROPERTYPOWER))
			return 15;
		else
			return 0;
	}

	@Override
	public int getStrongPower(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		if (state.getValue(PROPERTYPOWER))
			return 15;
		else
			return 0;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.SOLID;
	}

	@Override
	public int getLightValue(IBlockState state) {
		if (state.getValue(PROPERTYPOWER))
			return 5;
		else
			return 0;
	}

	@Override
	public boolean isOpaqueCube(IBlockState iBlockState) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState iBlockState) {
		return true;
	}

	@Override
	public boolean isFullBlock(IBlockState iBlockState) {
		return true;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		Boolean b = (meta > 0);
		return this.getDefaultState().withProperty(PROPERTYPOWER, b);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(PROPERTYPOWER) ? 1 : 0;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { PROPERTYPOWER });
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntitySignalProviderEntity();
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
}