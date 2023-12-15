package chtmod.RoadLines.Curved;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

/**
 * This part of code was copied from Minecraft-Transit-Railway mod
 * 
 * https://github.com/jonafanho/Minecraft-Transit-Railway
 */
public class TileEntityLineRenderer extends TileEntitySpecialRenderer<TileEntityLineEntity> {

	@Override
	public boolean isGlobalRenderer(TileEntityLineEntity te) {
		return true;
	}

	@Override
	public void renderTileEntityAt(TileEntityLineEntity te, double x, double y, double z, float partialTicks,
			int destroyStage) {
		String recPath = "chtmod:textures/blocks/";
		switch (te.texture) {
		case 0:
			recPath += "white_thin.png";
			break;
		case 1:
			recPath += "yellow_thin.png";
			break;
		case 2:
			recPath += "yellow_double.png";
			break;
		case 3:
			recPath += "white_thick.png";
			break;
		case 4:
			recPath += "yellow_thick.png";
			break;
		}
		GlStateManager.pushMatrix();
		GlStateManager.pushAttrib();
		GlStateManager.translate(x + te.xc + 0.5 - te.getPos().getX(), y, z + te.zc + 0.5 - te.getPos().getZ());
		GlStateManager.disableLighting();
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer worldrenderer = tessellator.getBuffer();
		renderRail(tessellator, worldrenderer, te.radius, te.startAngle, te.angleChange, recPath, te.dotted);
		GlStateManager.enableLighting();
		GlStateManager.popAttrib();
		GlStateManager.popMatrix();
	}

	private void renderRail(Tessellator tessellator, VertexBuffer worldrenderer, int radius, int startAngle,
			int angleChange, String texture, boolean dotted) {
		worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
		bindTexture(new ResourceLocation(texture));
		double interval = Math.signum(angleChange) / radius;
		double intAbs = Math.abs(interval) / 2D;
		boolean lower = angleChange < 0;
		int times = 0;
		for (double i = 0; Math.abs(i) < Math.abs(angleChange); i += Math.toDegrees(interval)) {
			if (times == 6)
				times = 0;
			if (!(times > 1 && dotted)) {
				double a = Math.toRadians(i + startAngle);
				double x1 = (radius + 0.5) * Math.sin(a - intAbs), z1 = (radius + 0.5) * Math.cos(a - intAbs);
				double x2 = (radius - 0.5) * Math.sin(a - intAbs), z2 = (radius - 0.5) * Math.cos(a - intAbs);
				double x3 = (radius + 0.5) * Math.sin(a + intAbs), z3 = (radius + 0.5) * Math.cos(a + intAbs);
				double x4 = (radius - 0.5) * Math.sin(a + intAbs), z4 = (radius - 0.5) * Math.cos(a + intAbs);
				worldrenderer.pos(x1, lower ? 0.0615 : 0.062, z1).tex(1, 0).endVertex();
				worldrenderer.pos(x3, lower ? 0.0615 : 0.062, z3).tex(1, 1).endVertex();
				worldrenderer.pos(x4, lower ? 0.0615 : 0.062, z4).tex(0, 1).endVertex();
				worldrenderer.pos(x2, lower ? 0.0615 : 0.062, z2).tex(0, 0).endVertex();
			}
			times++;
		}
		tessellator.draw();
	}
}