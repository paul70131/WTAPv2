package com.Paul70131.WTAPv2.client.gui;

import com.Paul70131.WTAPv2.WTAPv2;
import com.Paul70131.WTAPv2.client.module.ModuleManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;



public class ClickGuiButton extends GuiButton {

	public ClickGuiButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		// TODO Auto-generated constructor stub
	}
	
	protected static final ResourceLocation clickGuiButtonTextures = new ResourceLocation(WTAPv2.MODID, "gui/guibutton.png");
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            FontRenderer fontrenderer = mc.fontRendererObj;
            mc.getTextureManager().bindTexture(clickGuiButtonTextures);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int i = this.getHoverState(this.hovered);
            
            int o = 1;
            if (ModuleManager.getModuleByName(this.displayString).state) {
            	o = 2;
            }
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + o * 20, this.width / 2, this.height);
            this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + o * 20, this.width / 2, this.height);
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

            this.drawString(fontrenderer, this.displayString, this.xPosition + 25, this.yPosition + (this.height - 8) / 2, j);
        }
    }
}