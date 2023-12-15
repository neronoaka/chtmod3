package chtmod.Miscellaneous;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTicketChecker extends Block {
	public static final PropertyBool OPEN = PropertyBool.create("open");
	public static final PropertyDirection PROPERTYFACING = PropertyDirection.create("facing",
			EnumFacing.Plane.HORIZONTAL);

	public BlockTicketChecker() {
		super(Material.ROCK);
		this.setCreativeTab(chtmod.CustomCreativeTabs.miscellaneousTab);
		this.setRegistryName("TicketChecker");
		this.setUnlocalizedName("TicketChecker");
		this.setHardness(1);
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean isOpaqueCube(IBlockState iBlockState) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState iBlockState) {
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState iBlockState) {
		return false;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(PROPERTYFACING, EnumFacing.getHorizontal(meta & 0x3))
				.withProperty(OPEN, false);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(PROPERTYFACING).getHorizontalIndex();
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { PROPERTYFACING, OPEN });
	}

	@Override
	public boolean isSideSolid(IBlockState state, IBlockAccess access, BlockPos pos, EnumFacing facing) {
		return true;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if (state.getValue(OPEN))
			return new AxisAlignedBB(0, 0, 0, 1, 0.01F, 1);
		else
			return new AxisAlignedBB(0, 0, 0, 1, 1.5F, 1);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand handIn, ItemStack stackin, EnumFacing facing, float arg0, float arg1, float arg2) {
		try {
			if (stackin.getItem() instanceof ItemTicket) {
				IBlockState s = worldIn.getBlockState(pos);
				EnumFacing f = s.getValue(PROPERTYFACING);
				if (s.getBlock() instanceof BlockTicketChecker && f == playerIn.getHorizontalFacing()) {
					worldIn.setBlockState(pos, s.withProperty(OPEN, true));
					worldIn.scheduleUpdate(pos, worldIn.getBlockState(pos).getBlock(), 50);
				}
			} else {
				if (worldIn.isRemote)
					playerIn.addChatMessage(new TextComponentString(I18n.format("gui.invalidticket", new Object[0])));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return true;
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random p_updateTick_4_) {
		worldIn.setBlockState(pos, state.withProperty(OPEN, false));
	}

	@Override
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing blockFaceClickedOn, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer) {
		EnumFacing enumfacing = (placer == null) ? EnumFacing.NORTH : EnumFacing.fromAngle(placer.rotationYaw);
		return this.getDefaultState().withProperty(PROPERTYFACING, enumfacing).withProperty(OPEN, false);
	}
}
