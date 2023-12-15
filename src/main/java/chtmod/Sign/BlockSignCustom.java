package chtmod.Sign;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSignCustom extends BlockSign implements ITileEntityProvider {

	public BlockSignCustom(String arg0) {
		super(arg0);
		this.setCreativeTab(null);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing f = state.getValue(PROPERTYFACING);
		switch (f) {
		case EAST:
			return new AxisAlignedBB(15 / 16F, 0, 0, 1, 1, 1);
		case NORTH:
			return new AxisAlignedBB(0, 0, 0, 1, 1, 1 / 16F);
		case SOUTH:
			return new AxisAlignedBB(0, 0, 15 / 16F, 1, 1, 1);
		case WEST:
			return new AxisAlignedBB(0, 0, 0, 1 / 16F, 1, 1);
		default:
			return FULL_BLOCK_AABB;
		}
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		int size = 0;
		NBTTagCompound nbt = new NBTTagCompound();
		try {
			TileEntitySignEntity tese = (TileEntitySignEntity) worldIn.getTileEntity(pos);
			size = tese.size;
			nbt.setString("TEXTURE", tese.texture);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		ItemStack is = new ItemStack(StartupClientOnly.isp, 1, size);
		is.setTagCompound(nbt);
		return is;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntitySignEntity();
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block block) {
		EnumFacing facing = worldIn.getBlockState(pos).getValue(PROPERTYFACING);
		BlockPos pos1 = pos.offset(facing);
		if (worldIn.isAirBlock(pos.offset(facing)))
			worldIn.setBlockToAir(pos);
	}
}