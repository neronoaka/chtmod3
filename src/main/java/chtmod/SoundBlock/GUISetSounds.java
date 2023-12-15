package chtmod.SoundBlock;

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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GUISetSounds extends GUICommon {
	private GuiButton btnOK, btnCancel, btnAdd, btnSubtract;
	private GuiTextField txtType;
	private int sid;
	private ItemStack is;

	public GUISetSounds(ItemStack arg0) {
		is = arg0;
	}

	@Override
	public void initGui() {
		btnOK = new GuiButton(0, width / 2 - 80, height / 2 + 80, 75, 20, I18n.format("gui.ok", new Object[0]));
		btnCancel = new GuiButton(1, width / 2, height / 2 + 80, 75, 20, I18n.format("gui.cancel", new Object[0]));
		btnAdd = new GuiButton(2, width / 2 + 55, height / 2 - 15, 40, 20, "+");
		btnSubtract = new GuiButton(3, width / 2 - 85, height / 2 - 15, 40, 20, "-");
		txtType = new GuiTextField(0, fontRendererObj, width / 2 - 75, height / 2 - 55, 150, 20);
		buttonList.add(btnOK);
		buttonList.add(btnCancel);
		buttonList.add(btnAdd);
		buttonList.add(btnSubtract);
		txtType.setFocused(true);
		txtType.setMaxStringLength(64);
		if (is.hasTagCompound()) {
			NBTTagCompound nbt = is.getTagCompound();
			txtType.setText(nbt.getString("PLACE"));
			sid = nbt.getInteger("SOUNDID");
		}
	}

	@Override
	public void showScreen(int mouseX, int mouseY, float ticks) {
		drawDefaultBackground();
		drawCenteredString(fontRendererObj, I18n.format("gui.settype", new Object[0]), width / 2, height / 2 - 70,
				0xFFFFFF);
		txtType.drawTextBox();
		drawCenteredString(fontRendererObj, String.valueOf(sid), width / 2, height / 2 - 15, 0xFFFFFF);
		drawCenteredString(fontRendererObj, I18n.format("gui.setsoundid", new Object[0]), width / 2, height / 2 - 30,
				0xFFFFFF);
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0:
			if (txtType.getText().isEmpty())
				return;
			String place = txtType.getText();
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setString("PLACE", place);
			nbt.setInteger("SOUNDID", sid);
			is.setTagCompound(nbt);
			String dispName = is.getDisplayName().split(" ")[0];
			is.setStackDisplayName(dispName + " - Place:" + place + ",ID:" + sid);
			mc.displayGuiScreen(null);
			break;
		case 1:
			mc.displayGuiScreen(null);
			break;
		case 2:
			sid++;
			break;
		case 3:
			sid--;
			break;
		}
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		txtType.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (txtType.isFocused())
			txtType.textboxKeyTyped(typedChar, keyCode);
		else
			super.keyTyped(typedChar, keyCode);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}