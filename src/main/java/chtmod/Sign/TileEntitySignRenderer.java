package chtmod.Sign;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class TileEntitySignRenderer extends TileEntitySpecialRenderer<TileEntitySignEntity> {

	@Override
	public boolean isGlobalRenderer(TileEntitySignEntity te) {
		return true;
	}

	@Override
	public void renderTileEntityAt(TileEntitySignEntity te, double x, double y, double z, float partialTicks,
			int destroyStage) {
		GlStateManager.pushMatrix();
		GlStateManager.pushAttrib();
		GlStateManager.translate(x, y, z);
		GlStateManager.disableLighting();
		switch (EnumFacing.getHorizontal(te.facing)) {
		case EAST:
			GlStateManager.rotate(270, 0, 1, 0);
			GlStateManager.translate(0, 0, -1);
			break;
		case SOUTH:
			GlStateManager.rotate(180, 0, 1, 0);
			GlStateManager.translate(-1, 0, -1);
			break;
		case WEST:
			GlStateManager.rotate(90, 0, 1, 0);
			GlStateManager.translate(-1, 0, 0);
			break;
		default:
			break;
		}
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vertexBuffer = tessellator.getBuffer();
		bindTexture(new ResourceLocation("chtmod:textures/" + te.texture + ".png"));
		vertexBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		for (double[] vertex : vertexTable(te.size))
			vertexBuffer.pos(vertex[0], vertex[1], vertex[2]).tex(vertex[3], vertex[4]).endVertex();
		tessellator.draw();
		GlStateManager.enableLighting();
		GlStateManager.popAttrib();
		GlStateManager.popMatrix();
	}

	private double[][] vertexTable(int size) {
		double x1 = 0, x2 = 1, y1 = 0, y2 = 1;
		double z = 0.01 / 16F;
		switch (size) {
		case 1:// 3x1
			x1 = -1;
			x2 = 2;
			y1 = 0;
			y2 = 1;
			break;
		case 2:// 1x3
			x1 = 0;
			x2 = 1;
			y1 = -1;
			y2 = 2;
			break;
		case 3:// 3x3
			x1 = -1;
			x2 = 2;
			y1 = -1;
			y2 = 2;
			break;
		case 4:// 3x2
			x1 = -1;
			x2 = 2;
			y1 = 0;
			y2 = 2;
			break;
		case 5:// 6x4
			x1 = -3;
			x2 = 3;
			y1 = -2;
			y2 = 2;
			break;
		case 6:// 2.81x5.62
			x1 = -0.905;
			x2 = 1.905;
			y1 = 0;
			y2 = 5.62;
			break;
		}
		return new double[][] { { x2, y1, z, 1, 1 }, { x2, y2, z, 1, 0 }, { x1, y2, z, 0, 0 }, { x1, y1, z, 0, 1 } };
	}
}