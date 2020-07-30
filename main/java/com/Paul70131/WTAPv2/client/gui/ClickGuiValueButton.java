package com.Paul70131.WTAPv2.client.gui;

import com.Paul70131.WTAPv2.WTAPv2;
import com.Paul70131.WTAPv2.client.module.ModuleManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class ClickGuiValueButton extends GuiButton {

	public String linkedModule;

	public ClickGuiValueButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, String linkedModule) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		this.linkedModule = linkedModule;
		// TODO Auto-generated constructor stub
	}
	protected static final ResourceLocation clickGuiValueButtonTextures = new ResourceLocation(WTAPv2.MODID, "gui/guivaluebutton.png");
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            FontRenderer fontrenderer = mc.fontRendererObj;
            mc.getTextureManager().bindTexture(clickGuiValueButtonTextures);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            int o = 1;
            for (int i = 0; i < ModuleManager.getModuleByName(this.linkedModule).valueList.size(); i++) {
            	if (ModuleManager.getModuleByName(this.linkedModule).valueList.get(i).name.equalsIgnoreCase(this.displayString)) {
            		o = (int) ModuleManager.getModuleByName(this.linkedModule).valueList.get(i).value + 1;
            	}
            }
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + 20 * o, this.width / 2, this.height);
            this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, this.width / 2, 46 + 20 * o, this.width / 2, this.height);
            this.mouseDragged(mc, mouseX, mouseY);
            int j = 14737632;

            if (packedFGColour != 0)
            {
                j = packedFGColour;
            }
            else
            if (!this.enabled)
            {
                j = 10526880;
            }
            else if (this.hovered)
            {
                j = 16777120;
            }

            this.drawString(fontrenderer, this.displayString, this.xPosition, this.yPosition + (this.height - 8) / 2, j);
        }	
    }
}