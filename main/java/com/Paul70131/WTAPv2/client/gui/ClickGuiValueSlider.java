package com.Paul70131.WTAPv2.client.gui;

import com.Paul70131.WTAPv2.WTAPv2;
import com.Paul70131.WTAPv2.client.module.GuiReference;
import com.Paul70131.WTAPv2.client.module.ModuleManager;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiSlider;
import net.minecraftforge.fml.client.config.GuiUtils;

public class ClickGuiValueSlider extends GuiSlider{
	
	public String linkedModule;

	public ClickGuiValueSlider(int id, int xPos, int yPos, int width, int height, String prefix, String suf,
			double minVal, double maxVal, double currentVal, boolean showDec, boolean drawStr, String linkedModule) {
		super(id, xPos, yPos, width, height, prefix, suf, minVal, maxVal, currentVal, showDec, drawStr);
		this.linkedModule = linkedModule;
		// TODO Auto-generated constructor stub
	}
	
	protected static final ResourceLocation clickGuiCategoryTextures = new ResourceLocation(WTAPv2.MODID, "gui/guivalueslider.png");
	
    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        if (this.visible)
        {
        	/*
        	for (int ix = 0; ix < ModuleManager.modules.size(); ix ++) {
        		for (int u = 0; u < ModuleManager.modules.get(ix).valueList.size(); u ++) {
        			if (ModuleManager.modules.get(ix).valueList.get(u).reference == GuiReference.GUISLIDER) {
        				ModuleManager.modules.get(ix).valueList.get(u).slider.setValue(ModuleManager.modules.get(ix).valueList.get(u).value);
        			}
        		}
        	}
        	 */
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int k = this.getHoverState(this.hovered);
            
            GuiUtils.drawContinuousTexturedBox(clickGuiCategoryTextures, this.xPosition, this.yPosition, 0, 46 + k * 20, this.width, this.height, 200, 20, 2, 3, 2, 2, this.zLevel);
            if (this.hovered) {
            this.updateSlider();
            }
            int color = 14737632;

            if (packedFGColour != 0)
            {
                color = packedFGColour;
            }
            else if (!this.enabled)
            {
                color = 10526880;
            }
            else if (this.hovered)
            {
                color = 16777120;
            }


            this.drawString(mc.fontRendererObj, this.dispString + ": " + ((double)(Math.round(((this.maxValue - this.minValue) * this.sliderValue)* 100))/100 + this.minValue) , this.xPosition + 2, this.yPosition + (this.height - 8) / 2, color);
        }
    }
}
