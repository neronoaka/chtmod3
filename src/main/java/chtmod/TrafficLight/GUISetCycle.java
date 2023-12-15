package chtmod.TrafficLight;

import java.io.IOException;

import chtmod.GUICommon;
import chtmod.Helper.MathTools;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GUISetCycle extends GUICommon {
	private GuiButton btnOK, btnCancel;
	private GuiTextField txtTime, txtColor, txtMax;
	private ItemStack is;
	private String dispTips;

	public GUISetCycle(ItemStack arg0) {
		is = arg0;
		dispTips = "gui.example";
	}

	@Override
	public void initGui() {
		btnOK = new GuiButton(0, width / 2 - 80, height / 2 + 80, 75, 20, I18n.format("gui.ok", new Object[0]));
		btnCancel = new GuiButton(1, width / 2, height / 2 + 80, 75, 20, I18n.format("gui.cancel", new Object[0]));
		txtTime = new GuiTextField(0, fontRendererObj, width / 2 - 75, height / 2 - 55, 150, 20);
		txtColor = new GuiTextField(1, fontRendererObj, width / 2 - 75, height / 2 - 15, 150, 20);
		txtMax = new GuiTextField(1, fontRendererObj, width / 2 - 75, height / 2 - 95, 150, 20);
		buttonList.add(btnOK);
		buttonList.add(btnCancel);
		txtTime.setMaxStringLength(64);
		txtMax.setFocused(true);
		if (is.hasTagCompound()) {
			NBTTagCompound tag = is.getTagCompound();
			txtTime.setText(MathTools.arr2Str(tag.getIntArray("TIME")));
			txtColor.setText(MathTools.arr2Str(tag.getIntArray("COLOR")));
			txtMax.setText(String.valueOf(tag.getInteger("MAX")));
		}
	}

	@Override
	public void showScreen(int mouseX, int mouseY, float ticks) {
		drawDefaultBackground();
		drawCenteredString(fontRendererObj, I18n.format("gui.setcycle", new Object[0]), width / 2, height / 2 - 110,
				0xFFFFFF);
		txtMax.drawTextBox();
		drawCenteredString(fontRendererObj, I18n.format("gui.settime", new Object[0]), width / 2, height / 2 - 70,
				0xFFFFFF);
		txtTime.drawTextBox();
		drawCenteredString(fontRendererObj, I18n.format("gui.setcolor", new Object[0]), width / 2, height / 2 - 30,
				0xFFFFFF);
		txtColor.drawTextBox();
		drawCenteredString(fontRendererObj, I18n.format(dispTips, new Object[0]), width / 2, height / 2 + 10, 0xFFFFFF);
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0:
			if (txtTime.getText().isEmpty() && txtColor.getText().isEmpty())
				return;
			int maxValue = MathTools.str2Int(txtMax.getText());
			if (maxValue < 4)
				maxValue = 4;
			String times[] = txtTime.getText().split(" ");
			String colors[] = txtColor.getText().split(" ");
			int[] inttime = new int[times.length];
			int[] intcolor = new int[colors.length];
			for (int i = 0; i < times.length; i++) {
				int n = MathTools.str2Int(times[i]);
				if (n > -1 && n < maxValue)
					inttime[i] = n;
			}
			for (int j = 0; j < colors.length; j++) {
				int n = MathTools.str2Int(colors[j]);
				if (n > -1 && n < 4)
					intcolor[j] = n;
			}
			if (inttime.length == intcolor.length) {
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setIntArray("TIME", inttime);
				nbt.setIntArray("COLOR", intcolor);
				nbt.setInteger("MAX", maxValue);
				is.setTagCompound(nbt);
				String dispName = is.getDisplayName().split(" ")[0];
				is.setStackDisplayName(dispName + " - Colors:" + MathTools.arr2Str(intcolor) + ",Times:"
						+ MathTools.arr2Str(inttime) + ",Cycle:" + maxValue);
				mc.displayGuiScreen(null);
			} else
				dispTips = "gui.error";
			break;
		case 1:
			mc.displayGuiScreen(null);
			break;
		}
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		txtTime.mouseClicked(mouseX, mouseY, mouseButton);
		txtColor.mouseClicked(mouseX, mouseY, mouseButton);
		txtMax.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (txtTime.isFocused())
			txtTime.textboxKeyTyped(typedChar, keyCode);
		else if (txtColor.isFocused())
			txtColor.textboxKeyTyped(typedChar, keyCode);
		else if (txtMax.isFocused())
			txtMax.textboxKeyTyped(typedChar, keyCode);
		else
			super.keyTyped(typedChar, keyCode);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}