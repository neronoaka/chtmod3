package chtmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class GUICommon extends GuiScreen {
	private boolean released;

	public GUICommon() {
		released = false;
	}

	public void showScreen(int mouseX, int mouseY, float ticks) {
		// System.out.println("GUI Opened");
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float ticks) {
		try {
			showScreen(mouseX, mouseY, ticks);
			super.drawScreen(mouseX, mouseY, ticks);
			if (!released) {
				Minecraft.getMinecraft().mouseHelper.ungrabMouseCursor();
				released = true;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
	}
}
