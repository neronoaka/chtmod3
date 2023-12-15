package chtmod.RoadLines;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemPutDottedLine extends Item {
	private final int maxSubtypes = 4;

	public ItemPutDottedLine() {
		this.setMaxStackSize(1);
		this.hasSubtypes = true;
		this.setUnlocalizedName("ToolPutDottedLine");
		this.setRegistryName("ToolPutDottedLine");
		this.setCreativeTab(chtmod.CustomCreativeTabs.lineTab);
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		for (int i = 0; i < maxSubtypes; i++)
			subItems.add(new ItemStack(itemIn, 1, i));
	}

	@Override
	public int getMetadata(int metadata) {
		return metadata;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + stack.getMetadata();
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing f, float hitX, float hitY, float hitZ) {
		IBlockState marker = chtmod.RoadLines.Curved.StartupCommon.lm.getDefaultState();
		if (stack.hasTagCompound()) {
			NBTTagCompound nbttag = stack.getTagCompound();
			int x0 = nbttag.getInteger("PosX");
			int z0 = nbttag.getInteger("PosZ");
			int y = nbttag.getInteger("PosY");
			if (worldIn.getBlockState(new BlockPos(x0, y, z0))
					.getBlock() instanceof chtmod.RoadLines.Curved.BlockLineMarker) {
				EnumFacing facing = EnumFacing.getHorizontal(nbttag.getInteger("FACING"));
				int x1 = pos.getX();
				int z1 = pos.getZ();
				int length = 0;
				if (stack.getMetadata() < maxSubtypes / 2) {
					if (facing == EnumFacing.SOUTH || facing == EnumFacing.NORTH)
						length = Math.abs(z1 - z0);
					if (facing == EnumFacing.WEST || facing == EnumFacing.EAST)
						length = Math.abs(x1 - x0);
					putDottedStraightLine(worldIn, facing, new BlockPos(x0, y, z0), length,
							getStateByMeta(stack.getMetadata()), false);
				} else {
					int length1 = z1 - z0;
					int length2 = x1 - x0;
					length = Math.abs(length1);
					if (length == Math.abs(length2))
						putDottedStraightLine(worldIn, getSlopeFacing(length2, length1), new BlockPos(x0, y, z0),
								length, getStateByMeta(stack.getMetadata()), true);
					else {
						if (worldIn.isRemote)
							playerIn.addChatComponentMessage(
									new TextComponentString(I18n.format("gui.lennotequal", new Object[0])));
					}
				}
			} else {
				if (worldIn.isRemote)
					playerIn.addChatComponentMessage(
							new TextComponentString(I18n.format("gui.putlinefail", new Object[0])));
			}
			stack.setTagCompound(null);
		} else {
			NBTTagCompound nbttag = new NBTTagCompound();
			nbttag.setInteger("PosX", pos.getX());
			nbttag.setInteger("PosY", pos.up().getY());
			nbttag.setInteger("PosZ", pos.getZ());
			nbttag.setInteger("FACING", playerIn.getHorizontalFacing().getHorizontalIndex());
			stack.setTagCompound(nbttag);
			worldIn.setBlockState(pos.up(), marker);
			if (worldIn.isRemote)
				playerIn.addChatComponentMessage(new TextComponentString(I18n.format("gui.connected", new Object[0])
						+ "(" + pos.getX() + "," + pos.up().getY() + "," + pos.getZ() + ")"));
		}
		return EnumActionResult.SUCCESS;
	}

	private IBlockState getStateByMeta(int meta) {
		Block white = Block.getBlockFromName("chtmod:RoadLineWhite");
		Block yellow = Block.getBlockFromName("chtmod:RoadLineYellow");
		switch (meta) {
		case 0:
			return white.getDefaultState().withProperty(BlockRoadLine.PROPERTYANGLE, EnumAngle.A);
		case 1:
			return yellow.getDefaultState().withProperty(BlockRoadLine.PROPERTYANGLE, EnumAngle.A);
		case 2:
			return white.getDefaultState().withProperty(BlockRoadLine.PROPERTYANGLE, EnumAngle.B);
		case 3:
			return yellow.getDefaultState().withProperty(BlockRoadLine.PROPERTYANGLE, EnumAngle.B);
		}
		return Blocks.STONE.getDefaultState();
	}

	private EnumFacing getSlopeFacing(int x, int z) {
		if (x < 0 && z < 0)
			return EnumFacing.NORTH;
		else if (x < 0 && z > 0)
			return EnumFacing.WEST;
		else if (x > 0 && z < 0)
			return EnumFacing.EAST;
		else
			return EnumFacing.SOUTH;
	}

	private void putDottedStraightLine(World world, EnumFacing facing, BlockPos start, int length, IBlockState state,
			boolean slope) {
		int counter = 0;
		if (!(state.getBlock() instanceof BlockRoadLine))
			return;
		for (int i = 0, j = 0; i < length; i++) {
			if (counter == 6)
				counter = 0;
			if (slope)
				j = i;
			if (counter < 3)
				switch (facing) {
				case EAST:
					world.setBlockState(start.east(i).north(j),
							state.withProperty(BlockRoadLine.PROPERTYFACING, EnumFacing.EAST));
					break;
				case NORTH:
					world.setBlockState(start.north(i).west(j),
							state.withProperty(BlockRoadLine.PROPERTYFACING, EnumFacing.NORTH));
					break;
				case SOUTH:
					world.setBlockState(start.south(i).east(j),
							state.withProperty(BlockRoadLine.PROPERTYFACING, EnumFacing.SOUTH));
					break;
				case WEST:
					world.setBlockState(start.west(i).south(j),
							state.withProperty(BlockRoadLine.PROPERTYFACING, EnumFacing.WEST));
					break;
				default:
					break;
				}
			counter++;
		}
	}
}
