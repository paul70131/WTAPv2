package com.Paul70131.WTAPv2.client.module.modules;

import com.Paul70131.WTAPv2.client.module.Categorys;
import com.Paul70131.WTAPv2.client.module.Module;
import com.Paul70131.WTAPv2.client.module.ModuleManager;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public class ModuleList extends Module {

	public ModuleList(Categorys category, String name) {
		super(category, name);
		// TODO Auto-generated constructor stub
	}
		
	@Override
	public void init() {
		
	}
	
	@Override 
	public void onRenderGui(Event event) {
		if (this.state && event instanceof RenderGameOverlayEvent.Post) {
			int moduleCount = 0;
			event = (RenderGameOverlayEvent) event;

			ScaledResolution scaled = new ScaledResolution(mc);
			
			for (int i = 0; i < ModuleManager.modules.size(); i ++) {
				String moduleName = ModuleManager.modules.get(i).name;
				int nameWidth = mc.fontRendererObj.getStringWidth(moduleName);
				
				if (ModuleManager.modules.get(i).state) {
					mc.fontRendererObj.drawString(moduleName, scaled.getScaledWidth() - nameWidth - 2, (moduleCount * 10) + 2, 255);
					moduleCount ++;
				}
			}
			
		}
		
	}

}
