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

public class GUISetSign extends GUICommon {
	private GuiButton btnOK, btnCancel;
	private GuiTextField txtTexture;
	private String disp;
	private ItemStack is;

	public GUISetSign(ItemStack arg0) {
		disp = "gui.inputtexture";
		is = arg0;
	}

	@Override
	public void initGui() {
		btnOK = new GuiButton(0, width / 2 - 80, height / 2 + 80, 75, 20, I18n.format("gui.ok", new Object[0]));
		btnCancel = new GuiButton(1, width / 2, height / 2 + 80, 75, 20, I18n.format("gui.cancel", new Object[0]));
		txtTexture = new GuiTextField(1, fontRendererObj, width / 2 - 75, height / 2 - 95, 150, 20);
		txtTexture.setFocused(true);
		buttonList.add(btnOK);
		buttonList.add(btnCancel);
		if (is.hasTagCompound())
			txtTexture.setText(is.getTagCompound().getString("TEXTURE"));
	}

	@Override
	public void showScreen(int mouseX, int mouseY, float ticks) {
		drawDefaultBackground();
		txtTexture.drawTextBox();
		drawCenteredString(fontRendererObj, I18n.format("gui.settexture", new Object[0]), width / 2, height / 2 - 70,
				0xFFFFFF);
		drawCenteredString(fontRendererObj, I18n.format(disp, new Object[0]), width / 2, height / 2 - 30, 0xFFFFFF);
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0:
			if (txtTexture.getText().isEmpty()) {
				disp = "gui.noinput";
				return;
			} else {
				disp = "gui.inputtexture";
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setString("TEXTURE", txtTexture.getText());
				is.setTagCompound(nbt);
				String dispName = is.getDisplayName().split(" ")[0];
				is.setStackDisplayName(dispName + " - Texture:" + txtTexture.getText());
				mc.displayGuiScreen(null);
			}
			break;
		case 1:
			mc.displayGuiScreen(null);
			break;
		}
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		txtTexture.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if (txtTexture.isFocused())
			txtTexture.textboxKeyTyped(typedChar, keyCode);
		else
			super.keyTyped(typedChar, keyCode);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}