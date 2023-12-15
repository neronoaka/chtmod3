package chtmod.Sign;

import java.io.IOException;

import chtmod.GUICommon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GUISetMetroSign extends GUICommon {
	private GuiButton btnOK, btnCancel, btnLR;
	private GuiTextField txtLine, txtStart, txtEnd;
	private ItemStack is;
	private boolean lefttoright;

	public GUISetMetroSign(ItemStack arg0) {
		is = arg0;
		lefttoright = true;
	}

	@Override
	public void initGui() {
		btnOK = new GuiButton(0, width / 2 - 80, height / 2 + 80, 75, 20, I18n.format("gui.ok", new Object[0]));
		btnCancel = new GuiButton(1, width / 2, height / 2 + 80, 75, 20, I18n.format("gui.cancel", new Object[0]));
		btnLR = new GuiButton(2, width / 2 - 35, height / 2 + 50, 70, 20, "Left->Right");
		txtLine = new GuiTextField(1, fontRendererObj, width / 2 - 75, height / 2 - 90, 150, 20);
		txtStart = new GuiTextField(1, fontRendererObj, width / 2 - 60, height / 2 - 50, 50, 20);
		txtEnd = new GuiTextField(1, fontRendererObj, width / 2 + 5, height / 2 - 50, 50, 20);
		txtLine.setFocused(true);
		buttonList.add(btnOK);
		buttonList.add(btnCancel);
		buttonList.add(btnLR);
	}

	@Override
	public void showScreen(int mouseX, int mouseY, float ticks) {
		drawDefaultBackground();
		txtLine.drawTextBox();
		txtStart.drawTextBox();
		txtEnd.drawTextBox();
		drawCenteredString(fontRendererObj, I18n.format("gui.setline", new Object[0]), width / 2, height / 2 - 105,
				0xFFFFFF);
		drawCenteredString(fontRendererObj, "->", width / 2 - 5, height / 2 - 45, 0xFFFFFF);
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0:
			if (txtLine.getText().isEmpty()) {
				return;
			} else {
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setInteger("START", chtmod.Helper.MathTools.str2Int(txtStart.getText()));
				nbt.setInteger("END", chtmod.Helper.MathTools.str2Int(txtEnd.getText()));
				nbt.setBoolean("LtoR", lefttoright);
				nbt.setString("LINE", txtLine.getText());
				is.setTagCompound(nbt);
				mc.displayGuiScreen(null);
			}
			break;
		case 1:
			mc.displayGuiScreen(null);
			break;
		case 2:
			if (lefttoright) {
				btnLR.displayString = "Right->Left";
				lefttoright = false;
			} else {
				btnLR.displayString = "Left->Right";
				lefttoright = true;
			}
			break;
		}
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		txtLine.mouseClicked(mouseX, mouseY, mouseButton);
		txtStart.mouseClicked(mouseX, mouseY, mouseButton);
		txtEnd.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (txtLine.isFocused())
			txtLine.textboxKeyTyped(typedChar, keyCode);
		else if (txtStart.isFocused())
			txtStart.textboxKeyTyped(typedChar, keyCode);
		else if (txtEnd.isFocused())
			txtEnd.textboxKeyTyped(typedChar, keyCode);
		else
			super.keyTyped(typedChar, keyCode);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}