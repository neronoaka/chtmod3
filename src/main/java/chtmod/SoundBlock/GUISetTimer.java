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

public class GUISetTimer extends GUICommon {
	private GuiButton btnOK, btnCancel, btnClr;
	private GuiButton buttonOnAdd1, buttonOnSubtract1, buttonOnAdd10, buttonOnSubtract10, buttonOnAdd100,
			buttonOnSubtract100, buttonOffAdd1, buttonOffSubtract1, buttonOffAdd10, buttonOffSubtract10,
			buttonOffAdd100, buttonOffSubtract100;
	private int on, off;
	private ItemStack is;

	public GUISetTimer(ItemStack arg0) {
		is = arg0;
	}

	@Override
	public void initGui() {
		btnOK = new GuiButton(0, width / 2 - 80, height / 2 + 80, 75, 20, I18n.format("gui.ok", new Object[0]));
		btnCancel = new GuiButton(1, width / 2, height / 2 + 80, 75, 20, I18n.format("gui.cancel", new Object[0]));
		btnClr = new GuiButton(14, width / 2 - 20, height / 2 + 55, 40, 20,
				I18n.format("gui.timeclean", new Object[0]));
		buttonOnAdd1 = new GuiButton(2, width / 2 + 55, height / 2 - 30, 40, 20, "+1");
		buttonOnAdd10 = new GuiButton(3, width / 2 + 95, height / 2 - 30, 30, 20, "+10");
		buttonOnAdd100 = new GuiButton(4, width / 2 + 125, height / 2 - 30, 30, 20, "+100");
		buttonOnSubtract1 = new GuiButton(5, width / 2 - 85, height / 2 - 30, 40, 20, "-1");
		buttonOnSubtract10 = new GuiButton(6, width / 2 - 115, height / 2 - 30, 30, 20, "-10");
		buttonOnSubtract100 = new GuiButton(7, width / 2 - 145, height / 2 - 30, 30, 20, "-100");
		buttonOffAdd1 = new GuiButton(8, width / 2 + 55, height / 2, 40, 20, "+1");
		buttonOffAdd10 = new GuiButton(9, width / 2 + 95, height / 2, 30, 20, "+10");
		buttonOffAdd100 = new GuiButton(10, width / 2 + 125, height / 2, 30, 20, "+100");
		buttonOffSubtract1 = new GuiButton(11, width / 2 - 85, height / 2, 40, 20, "-1");
		buttonOffSubtract10 = new GuiButton(12, width / 2 - 115, height / 2, 30, 20, "-10");
		buttonOffSubtract100 = new GuiButton(13, width / 2 - 145, height / 2, 30, 20, "-100");
		buttonList.add(btnOK);
		buttonList.add(btnCancel);
		buttonList.add(btnClr);
		buttonList.add(buttonOnAdd1);
		buttonList.add(buttonOnAdd10);
		buttonList.add(buttonOnAdd100);
		buttonList.add(buttonOnSubtract1);
		buttonList.add(buttonOnSubtract10);
		buttonList.add(buttonOnSubtract100);
		buttonList.add(buttonOffAdd1);
		buttonList.add(buttonOffAdd10);
		buttonList.add(buttonOffAdd100);
		buttonList.add(buttonOffSubtract1);
		buttonList.add(buttonOffSubtract10);
		buttonList.add(buttonOffSubtract100);
		if (is.hasTagCompound()) {
			NBTTagCompound nbt = is.getTagCompound();
			off = nbt.getInteger("OFF");
			on = nbt.getInteger("ON");
		}
	}

	@Override
	public void showScreen(int mouseX, int mouseY, float ticks) {
		drawDefaultBackground();
		drawCenteredString(fontRendererObj, I18n.format("gui.settimer", new Object[0]), width / 2, height / 2 - 75,
				0xFFFFFF);
		drawCenteredString(fontRendererObj, I18n.format("gui.ontime", new Object[0]), width / 2, height / 2 - 30,
				0xFFFFFF);
		drawCenteredString(fontRendererObj, String.valueOf(on), width / 2, height / 2 - 20, 0xFFFFFF);
		drawCenteredString(fontRendererObj, I18n.format("gui.offtime", new Object[0]), width / 2, height / 2, 0xFFFFFF);
		drawCenteredString(fontRendererObj, String.valueOf(off), width / 2, height / 2 + 10, 0xFFFFFF);
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0:
			if (on > 0 && off > 0) {
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setInteger("ON", on);
				nbt.setInteger("OFF", off);
				is.setTagCompound(nbt);
				mc.displayGuiScreen(null);
			}
			break;
		case 1:
			mc.displayGuiScreen(null);
			break;
		case 2:
			on++;
			break;
		case 3:
			on += 10;
			break;
		case 4:
			on += 100;
			break;
		case 5:
			on--;
			break;
		case 6:
			on -= 10;
			break;
		case 7:
			on -= 100;
			break;
		case 8:
			off++;
			break;
		case 9:
			off += 10;
			break;
		case 10:
			off += 100;
			break;
		case 11:
			off--;
			break;
		case 12:
			off -= 10;
			break;
		case 13:
			off -= 100;
			break;
		case 14:
			on = 0;
			off = 0;
			is.setTagCompound(null);
			break;
		}
		if (on < 0)
			on = 0;
		if (off < 0)
			off = 0;
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}