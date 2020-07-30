package com.Paul70131.WTAPv2.client.gui;

import java.io.IOException;

import com.Paul70131.WTAPv2.WTAPv2;

import com.Paul70131.WTAPv2.client.module.Categorys;
import com.Paul70131.WTAPv2.client.module.GuiReference;
import com.Paul70131.WTAPv2.client.module.ModuleManager;
import com.Paul70131.WTAPv2.client.module.Value;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class ClickGui extends GuiScreen {
	Value currValue;
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		
		fontRendererObj.drawString("WTAP " + WTAPv2.VERSION, 0, 0, 16777215);
		
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void actionPerformed(GuiButton button) throws IOException {
		if (button.id < 1000 && button.id % 10 == 0) {
			for (int i = 0; i < ModuleManager.modules.size(); i ++) {
				if (button.displayString == ModuleManager.modules.get(i).button.displayString) {
					ModuleManager.getModuleByName(button.displayString).toggle();
				}
			}
		} else if (button.id < 1000 && button instanceof ClickGuiValueButton) {
			for (int i = 0; i < ModuleManager.getModuleByName(((ClickGuiValueButton)button).linkedModule).valueList.size(); i ++) {
				if (ModuleManager.getModuleByName(((ClickGuiValueButton)button).linkedModule).valueList.get(i).name == button.displayString) {
					currValue = ModuleManager.getModuleByName(((ClickGuiValueButton)button).linkedModule).valueList.get(i);
				}
			}
			if (currValue.value == 0) {
				currValue.value = 1;
			} else {
				currValue.value = 0;
			}
		} else if (button.id < 1000 && button instanceof ClickGuiValueSlider) {
			for (int i = 0; i < ModuleManager.getModuleByName(((ClickGuiValueSlider)button).linkedModule).valueList.size(); i ++) {
				if (ModuleManager.getModuleByName(((ClickGuiValueSlider)button).linkedModule).valueList.get(i).name == button.displayString) {
					ModuleManager.getModuleByName(((ClickGuiValueSlider)button).linkedModule).valueList.get(i).value = ((ClickGuiValueSlider)button).getValue();
				}
			}
		}
	}
	
	public void onRightClick(GuiButton button) {
	
		if (!ModuleManager.getModuleByName(button.displayString).guiexpanded) {
			ModuleManager.getModuleByName(button.displayString).guiexpanded = true;
			for (int i = 0; i < ModuleManager.getModuleByName(button.displayString).valueList.size(); i++ ) {
				if (ModuleManager.getModuleByName(button.displayString).valueList.get(i).reference == GuiReference.GUIBUTTON) {
					ModuleManager.getModuleByName(button.displayString).valueList.get(i).button.xPosition = button.xPosition + 90;
					ModuleManager.getModuleByName(button.displayString).valueList.get(i).button.yPosition = button.yPosition + (20 * i);
					this.buttonList.add(ModuleManager.getModuleByName(button.displayString).valueList.get(i).button);
				} else if (ModuleManager.getModuleByName(button.displayString).valueList.get(i).reference == GuiReference.GUISLIDER) {
					ModuleManager.getModuleByName(button.displayString).valueList.get(i).slider.xPosition = button.xPosition + 90;
					ModuleManager.getModuleByName(button.displayString).valueList.get(i).slider.yPosition = button.yPosition + (20* i);
					this.buttonList.add(ModuleManager.getModuleByName(button.displayString).valueList.get(i).slider);
				}
			}
		} else {
			ModuleManager.getModuleByName(button.displayString).guiexpanded = false;
			for (int i = 0; i < ModuleManager.getModuleByName(button.displayString).valueList.size(); i++ ) {
				if (ModuleManager.getModuleByName(button.displayString).valueList.get(i).reference == GuiReference.GUIBUTTON) {
					this.buttonList.remove(ModuleManager.getModuleByName(button.displayString).valueList.get(i).button);
				} else if (ModuleManager.getModuleByName(button.displayString).valueList.get(i).reference == GuiReference.GUISLIDER) {
					this.buttonList.remove(ModuleManager.getModuleByName(button.displayString).valueList.get(i).slider);
				}
			}
		}
		
	}
	
	@Override
	public void initGui() {
		buttonList.add(new ClickGuiCategory(1000, 0, 50, 100, 20, "MOVEMENT"));
		buttonList.add(new ClickGuiCategory(1001, 200, 50, 100, 20, "COMBAT"));
		buttonList.add(new ClickGuiCategory(1002, 400, 50, 100, 20, "RENDER"));
		
		int movementI = 1;
		int combatI = 1;
		int renderI = 1;
		
		for (int i = 0; i < ModuleManager.modules.size(); i++) {
			
			if (ModuleManager.modules.get(i).category == Categorys.MOVEMENT) {
				ModuleManager.modules.get(i).button.yPosition = buttonList.get(0).yPosition + (20 * movementI);
				ModuleManager.modules.get(i).button.xPosition = buttonList.get(0).xPosition + 5;
				buttonList.add(ModuleManager.modules.get(i).button);
				movementI ++;
			} else if (ModuleManager.modules.get(i).category == Categorys.COMBAT) {
				ModuleManager.modules.get(i).button.yPosition = buttonList.get(1).yPosition + (20 * combatI);
				ModuleManager.modules.get(i).button.xPosition = buttonList.get(1).xPosition + 5;
				buttonList.add(ModuleManager.modules.get(i).button);
				combatI ++;
			} else if (ModuleManager.modules.get(i).category == Categorys.RENDER) {
				ModuleManager.modules.get(i).button.yPosition = buttonList.get(2).yPosition + (20 * renderI);
				ModuleManager.modules.get(i).button.xPosition = buttonList.get(2).xPosition + 5;
				buttonList.add(ModuleManager.modules.get(i).button);
				renderI ++;
			}
		}
	}
	@Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
    	 if (mouseButton == 0)
         {
             for (int i = 0; i < this.buttonList.size(); ++i)
             {
                 GuiButton guibutton = (GuiButton)this.buttonList.get(i);

                 if (guibutton.mousePressed(this.mc, mouseX, mouseY))
                 {
                     net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent.Pre event = new net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent.Pre(this, guibutton, this.buttonList);
                     if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event))
                         break;
                     guibutton = event.button;
            //         this.selectedButton = guibutton;
                     guibutton.playPressSound(this.mc.getSoundHandler());
                     this.actionPerformed(guibutton);
                     if (this.equals(this.mc.currentScreen))
                         net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent.Post(this, event.button, this.buttonList));
                 }
             }
        } else if (mouseButton == 1) {
        	for ( int i = 0; i < this.buttonList.size(); i++) {
        		if (this.buttonList.get(i).isMouseOver() && ((this.buttonList.get(i).id % 10 == 0) && this.buttonList.get(i).id < 1000)) {
        			this.onRightClick(this.buttonList.get(i));
        			}
        		}
        	}
        }
  
	@Override
	public void onGuiClosed() {
		for (int i = 0; i < ModuleManager.modules.size(); i++) {
			for (int u = 0; u < ModuleManager.modules.get(i).valueList.size(); u ++) {
				if (ModuleManager.modules.get(i).valueList.get(u).reference == GuiReference.GUISLIDER) {
					ModuleManager.modules.get(i).valueList.get(u).value = ModuleManager.modules.get(i).valueList.get(u).slider.getValue();
				}
			}
		}
	}
}
