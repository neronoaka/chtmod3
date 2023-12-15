package chtmod.Pole;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

public class PoleCentre extends BlockPoleBase {
	public static final int maxSubs = 4;
	public static final PropertyBool PROPERTYFACING = PropertyBool.create("facing");
	public static final PropertyInteger PROPERTYSTYLE = PropertyInteger.create("style", 0, maxSubs);
	public int[] width;

	public PoleCentre(String arg0, int[] arg1) {
		super(arg0);
		width = arg1;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		int sid = state.getValue(PROPERTYSTYLE) * 2;
		float x1 = (8 - (width[sid] / 2)) / 16F;
		float x2 = (8 + (width[sid] / 2)) / 16F;
		float y1 = (8 - (width[sid + 1] / 2)) / 16F;
		float y2 = (8 + (width[sid + 1] / 2)) / 16F;
		if (state.getValue(PROPERTYFACING))
			return new AxisAlignedBB(x1, y1, 0, x2, y2, 1);
		else
			return new AxisAlignedBB(0, y1, x1, 1, y2, x2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
		for (int i = 0; i <= maxSubs; i++)
			list.add(new ItemStack(itemIn, 1, i));
	}

	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(PROPERTYSTYLE);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		boolean facing = (meta & 0x01) > 0;
		int style = meta >> 1;
		return this.getDefaultState().withProperty(PROPERTYSTYLE, style).withProperty(PROPERTYFACING, facing);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int facing = (state.getValue(PROPERTYFACING) ? 1 : 0);
		int style = state.getValue(PROPERTYSTYLE) << 1;
		return facing | style;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { PROPERTYFACING, PROPERTYSTYLE });
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer) {
		EnumFacing f = placer.getHorizontalFacing();
		boolean facing = (f == EnumFacing.NORTH || f == EnumFacing.SOUTH);
		return this.getDefaultState().withProperty(PROPERTYFACING, facing).withProperty(PROPERTYSTYLE, meta);
	}
}