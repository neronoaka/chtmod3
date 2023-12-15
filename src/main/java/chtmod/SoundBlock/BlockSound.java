package chtmod.SoundBlock;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockSound extends Block {
	public BlockSound() {
		super(Material.ROCK);
		this.setCreativeTab(chtmod.CustomCreativeTabs.miscellaneousTab);
		this.setUnlocalizedName("SoundBlock");
		this.setRegistryName("SoundBlock");
		this.setHardness(1);
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.SOLID;
	}

	@Override
	public boolean isOpaqueCube(IBlockState iBlockState) {
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState iBlockState) {
		return true;
	}

	@Override
	public boolean isFullCube(IBlockState iBlockState) {
		return true;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public boolean canConnectRedstone(IBlockState state, IBlockAccess access, BlockPos pos, EnumFacing facing) {
		return true;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0, 0, 0, 1, 0.5F, 1);
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntitySoundEntity();
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	private int getPowerLevelInput(World world, BlockPos pos) {
		int maxPowerFound = 0, powerLevel = 0;
		for (EnumFacing whichFace : EnumFacing.HORIZONTALS) {
			BlockPos neighborPos = pos.offset(whichFace);
			powerLevel = world.getRedstonePower(neighborPos, whichFace);
			if (powerLevel == 0) {
				IBlockState wire = world.getBlockState(neighborPos);
				if (wire.getBlock() == Blocks.REDSTONE_WIRE)
					powerLevel = wire.getValue(Blocks.REDSTONE_WIRE.POWER);
			}
			maxPowerFound = Math.max(powerLevel, maxPowerFound);
		}
		return maxPowerFound;
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block block) {
		TileEntity te = worldIn.getTileEntity(pos);
		if (te instanceof TileEntitySoundEntity) {
			TileEntitySoundEntity tes = (TileEntitySoundEntity) te;
			if (getPowerLevelInput(worldIn, pos) > 0) {
				if (tes.isPowered == false) {
					tes.isPowered = true;
					String place = "chtmod:" + tes.place + tes.soundID;
					SoundEvent ev = new SoundEvent(new ResourceLocation(place));
					worldIn.playSound(null, pos, ev, SoundCategory.BLOCKS, 4, 1);
				}
			} else
				tes.isPowered = false;
		}
	}
}