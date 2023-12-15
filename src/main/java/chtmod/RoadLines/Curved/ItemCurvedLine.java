package chtmod.RoadLines.Curved;

import java.util.List;

import chtmod.Helper.MathTools;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
/**
 * This part of code was copied from Minecraft-Transit-Railway mod
 * 
 * https://github.com/jonafanho/Minecraft-Transit-Railway
 */
public class ItemCurvedLine extends Item {

	public ItemCurvedLine() {
		this.hasSubtypes = true;
		this.maxStackSize = 1;
		this.setUnlocalizedName("ToolCurvedLine");
		this.setRegistryName("ToolCurvedLine");
		this.setCreativeTab(chtmod.CustomCreativeTabs.lineTab);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		itemStackIn.setTagCompound(null);
		if (worldIn.isRemote)
			playerIn.addChatComponentMessage(new TextComponentString(I18n.format("gui.lineprepared", new Object[0])));
		return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState stateThis = worldIn.getBlockState(pos);
		if (!stateThis.getBlock().isReplaceable(worldIn, pos))
			pos = pos.offset(facing);
		stateThis = worldIn.getBlockState(pos);
		if (!stateThis.getBlock().isReplaceable(worldIn, pos))
			return EnumActionResult.FAIL;
		int rotation = MathHelper.floor_double((playerIn.rotationYaw + 180.0F) * 8.0F / 360.0F + 0.5D) & 3;
		int rotation2 = MathHelper.floor_double((playerIn.rotationYaw + 180.0F) * 8.0F / 360.0F + 0.5D) & 7;
		placeNormalRails(stack, playerIn, worldIn, pos, rotation, rotation2);
		return EnumActionResult.SUCCESS;
	}

	private void placeNormalRails(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, int i1,
			int rotation2) {
		// place block
		int x1 = pos.getX(), y1 = pos.getY(), z1 = pos.getZ();
		worldIn.setBlockState(pos, StartupCommon.lm.getDefaultState().withProperty(BlockLineMarker.ROTATION, i1));
		if (stack.getTagCompound() == null) {
			// if no previous block
			stack.setTagCompound(new NBTTagCompound());
			NBTTagCompound t = stack.getTagCompound();
			// set item data to current coords
			t.setInteger("trackX", x1);
			t.setInteger("trackY", y1);
			t.setInteger("trackZ", z1);
			if (worldIn.isRemote)
				playerIn.addChatComponentMessage(new TextComponentString(
						I18n.format("gui.connected", new Object[0]) + "(" + t.getInteger("trackX") + ","
								+ t.getInteger("trackY") + "," + t.getInteger("trackZ") + ")"));
		} else if (stack.getTagCompound().getInteger("trackY") == y1) {
			// if there is a previous block
			NBTTagCompound t = stack.getTagCompound();
			int x2 = t.getInteger("trackX");
			int y2 = t.getInteger("trackY");
			int z2 = t.getInteger("trackZ");
			BlockPos pos2 = new BlockPos(x2, y2, z2);
			IBlockState state = worldIn.getBlockState(pos2);
			int i2 = state.getValue(BlockLineMarker.ROTATION);
			int angle1 = (int) Math.round((-45D * i1 + 270D) % 180);
			int angle2 = (int) Math.round((-45D * i2 + 270D) % 180);
			int constants1[] = { 0, -1, 100, 1 }, constants2[] = { 100, 1, 0, -1 };
			int m1 = constants1[i1], m2 = constants1[i2];
			int d = m1 - m2;
			int dx = (-m1 * z1 + x1) * -m2 - (-m2 * z2 + x2) * -m1;
			int dz = -m2 * z2 + x2 - (-m1 * z1 + x1);
			// intercepts
			int xi = (int) Math.round((double) dx / (double) d), zi = (int) Math.round((double) dz / (double) d);
			double length1 = Math.sqrt(Math.pow(x1 - xi, 2) + Math.pow(z1 - zi, 2));
			double length2 = Math.sqrt(Math.pow(x2 - xi, 2) + Math.pow(z2 - zi, 2));
			// endpoint
			int x3, z3;
			if (length1 > length2) {
				double xd = length2 * Math.abs(Math.cos(angle1 * Math.PI / 180D));
				double zd = length2 * Math.abs(Math.sin(angle1 * Math.PI / 180D));
				x3 = xi + (int) (x1 > xi ? xd : -xd);
				z3 = zi + (int) (z1 > zi ? zd : -zd);
				m1 = constants2[i1];
				m2 = constants2[i2];
				d = m1 - m2;
				dx = (-m1 * z3 + x3) * -m2 - (-m2 * z2 + x2) * -m1;
				dz = -m2 * z2 + x2 - (-m1 * z3 + x3);
				// intercepts
				xi = (int) Math.round((double) dx / (double) d);
				zi = (int) Math.round((double) dz / (double) d);
				int r = (int) Math.round(Math.sqrt(Math.pow(x2 - xi, 2) + Math.pow(z2 - zi, 2)));
				connectWithCurve(worldIn, playerIn, x2, z2, x3, z3, y2, i2, i1, r, zi, xi, stack.getMetadata());
			} else {
				double xd = length1 * Math.abs(Math.cos(angle2 * Math.PI / 180D));
				double zd = length1 * Math.abs(Math.sin(angle2 * Math.PI / 180D));
				x3 = xi + (int) (x2 > xi ? xd : -xd);
				z3 = zi + (int) (z2 > zi ? zd : -zd);
				m1 = constants2[i1];
				m2 = constants2[i2];
				d = m1 - m2;
				dx = (-m1 * z1 + x1) * -m2 - (-m2 * z3 + x3) * -m1;
				dz = -m2 * z3 + x3 - (-m1 * z1 + x1);
				xi = (int) Math.round((double) dx / (double) d);
				zi = (int) Math.round((double) dz / (double) d);
				int r = (int) Math.round(Math.sqrt(Math.pow(x1 - xi, 2) + Math.pow(z1 - zi, 2)));
				connectWithCurve(worldIn, playerIn, x1, z1, x3, z3, y1, i1, i2, r, zi, xi, stack.getMetadata());
			}
			stack.setTagCompound(null);
		}
	}

	private void connectWithCurve(World worldIn, EntityPlayer playerIn, int x1, int z1, int x2, int z2, int y, int i1,
			int i2, int r, int h, int k, int texture) {
		if (r < 3 || r > 255) {
			if (worldIn.isRemote)
				playerIn.addChatComponentMessage(new TextComponentString(I18n.format("gui.lowradius", new Object[0])));
			return;
		}
		int angle1 = (int) Math.round((-45D * i1 + 270D) % 180);
		int angle2 = (int) Math.round((-45D * i2 + 270D) % 180);
		if (x1 < k)
			angle1 += 180;
		if (angle1 == 0 && z1 < h)
			angle1 = 180;
		if (x2 < k)
			angle2 += 180;
		if (angle2 == 0 && z2 < h)
			angle2 = 180;
		boolean reverse = MathTools.findCloserAngle(angle2, angle1 + 1, angle1 - 1) == angle1 - 1;
		IBlockState curvedRail = StartupCommon.blc.getDefaultState();
		double i = angle1;
		while (i != angle2) {
			i = Math.round((i + (reverse ? -0.01 : 0.01)) * 100) / 100D;
			if (i < 0)
				i += 360;
			if (i >= 360)
				i = i - 360;
			double a = Math.toRadians(i);
			int x = (int) Math.round(k + r * Math.sin(a));
			int z = (int) Math.round(h + r * Math.cos(a));
		}

		BlockPos pos1 = new BlockPos(x1, y, z1), pos2 = new BlockPos(x2, y, z2);
		int a = r % 16, b = Math.floorDiv(r, 16);
		BlockPos pos3 = pos1, pos4 = pos2;
		int angleDiff = (int) Math.round(MathTools.angleDifference(angle2, angle1));
		worldIn.setBlockState(pos1, curvedRail.withProperty(BlockLineCurved.ROTATION, i1));
		worldIn.setBlockState(pos2, curvedRail.withProperty(BlockLineCurved.ROTATION, i2));
		try {
			TileEntityLineEntity te1 = (TileEntityLineEntity) worldIn.getTileEntity(pos1);
			TileEntityLineEntity te2 = (TileEntityLineEntity) worldIn.getTileEntity(pos2);
			te1.radius = te2.radius = r;
			te1.xc = te2.xc = k;
			te1.zc = te2.zc = h;
			te1.startAngle = angle1;
			te1.texture = te2.texture = (texture < 5) ? texture : texture - 5;
			te2.startAngle = angle2;
			te1.angleChange = angleDiff;
			te2.angleChange = -te1.angleChange;
			te1.dotted = te2.dotted = (texture > 4);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private double[] rotateAngle(int pivotX, int pivotZ, double x, double z, int angle) {
		double r = Math.sqrt(Math.pow(x - pivotX, 2) + Math.pow(z - pivotZ, 2));
		double a = Math.acos((z - pivotZ) / r);
		if (x < pivotX)
			a = 2 * Math.PI - a;
		a = a + angle * Math.PI / 180D;
		double z2 = pivotZ + r * Math.cos(a);
		double x2 = pivotX + r * Math.sin(a);
		double result[] = { x2, z2 };
		return result;
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (stack.getTagCompound() != null) {
			NBTTagCompound t = stack.getTagCompound();
			int x = t.getInteger("trackX");
			int y = t.getInteger("trackY");
			int z = t.getInteger("trackZ");
			BlockPos pos = new BlockPos(x, y, z);
			if (!(worldIn.getBlockState(pos).getBlock() instanceof BlockLineMarker))
				stack.setTagCompound(null);
		}
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		for (int i = 0; i < 10; i++)
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
}
