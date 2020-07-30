package com.Paul70131.WTAPv2.client.module.modules;

import com.Paul70131.WTAPv2.client.module.Categorys;
import com.Paul70131.WTAPv2.client.module.GuiReference;
import com.Paul70131.WTAPv2.client.module.Module;
import com.Paul70131.WTAPv2.client.module.ModuleManager;
import com.Paul70131.WTAPv2.client.module.Value;
import com.Paul70131.WTAPv2.client.util.rayTrace;

import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public class Hitbox extends Module {

	public Hitbox(Categorys category, String name) {
		super(category, name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void init() {
		this.valueList.add(new Value("multiplier", 1.5, 1, 2, GuiReference.GUISLIDER));
	}
	
	@Override
	public void onUpdate(Event event) {
		if (this.state && event instanceof MouseEvent) {
			if (!ModuleManager.getModuleByName("Reach").state) {
				rayTrace.getRayTrace(3);
			}
		}
	}
}
