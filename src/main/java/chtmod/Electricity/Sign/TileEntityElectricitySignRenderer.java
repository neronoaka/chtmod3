package chtmod.Electricity.Sign;

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

public class TileEntityElectricitySignRenderer extends TileEntitySpecialRenderer<TileEntityElectricitySignEntity> {

	@Override
	public boolean isGlobalRenderer(TileEntityElectricitySignEntity te) {
		return true;
	}

	@Override
	public void renderTileEntityAt(TileEntityElectricitySignEntity te, double x, double y, double z, float partialTicks,
			int destroyStage) {
		for (int i = 0; i <= 3; i++) {
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
			if (i == 0) {
				bindTexture(new ResourceLocation("chtmod:textures/electricitylines/" + te.texture + ".png"));
			} else {
				int num = getPartNumber(te.number)[i - 1];
				bindTexture(new ResourceLocation("chtmod:textures/number/" + num + ".png"));
			}
			vertexBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
			for (double[] vertex : vertexTable(i))
				vertexBuffer.pos(vertex[0], vertex[1], vertex[2]).tex(vertex[3], vertex[4]).endVertex();
			tessellator.draw();
			GlStateManager.enableLighting();
			GlStateManager.popAttrib();
			GlStateManager.popMatrix();
		}
	}

	private int[] getPartNumber(int i) {
		int _unit = 0, _ten = 0, _hundred = 0;
		if (i < 10)
			_unit = i;
		if (i > 9 && i < 100) {
			_unit = i % 10;
			_ten = i / 10;
		}
		if (i > 99) {
			_hundred = i / 100;
			int ten = i % 100;
			_unit = ten % 10;
			_ten = ten / 10;
		}
		return new int[] { _hundred, _ten, _unit };
	}

	private double[][] vertexTable(int part) {
		double x1 = 1 / 4d, x2 = 3 / 4d, y1 = 1 / 4d, y2 = 1 / 2d;
		double z = -3.98 / 16d;
		if (part > 0) {
			y1 = 0;
			y2 = 1 / 4d;
		}
		switch (part) {
		case 1:
			x1 = 1 / 4d;
			x2 = 5 / 12d;
			break;
		case 2:
			x1 = 5 / 12d;
			x2 = 7 / 12d;
			break;
		case 3:
			x1 = 7 / 12d;
			x2 = 3 / 4d;
			break;
		}
		return new double[][] { { x2, y1, z, 1, 1 }, { x2, y2, z, 1, 0 }, { x1, y2, z, 0, 0 }, { x1, y1, z, 0, 1 } };
	}
}