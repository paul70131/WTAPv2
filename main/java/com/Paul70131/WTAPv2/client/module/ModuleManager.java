package com.Paul70131.WTAPv2.client.module;

import java.util.ArrayList;

import com.Paul70131.WTAPv2.WTAPv2;
import com.Paul70131.WTAPv2.client.module.modules.Hitbox;
import com.Paul70131.WTAPv2.client.module.modules.Keepsprint;
import com.Paul70131.WTAPv2.client.module.modules.ModuleList;
import com.Paul70131.WTAPv2.client.module.modules.Reach;
import com.Paul70131.WTAPv2.client.module.modules.Triggerbot;
import com.Paul70131.WTAPv2.client.module.modules.Velocity;
import com.Paul70131.WTAPv2.client.module.modules.WTAP;

import net.minecraftforge.fml.common.eventhandler.Event;

public class ModuleManager {
	
	public static ArrayList<Module> modules = new ArrayList<Module>();
	
	public static void callOnUpdate(Event event) {
		for (int i = 0; i < modules.size(); i++) {
			modules.get(i).onUpdate(event);
		}
	}	
	public static void callOnRenderGui(Event event) {
		for (int i = 0; i < modules.size(); i++) {
			modules.get(i).onRenderGui(event);
			}
	}
	public static void callOnRender(Event event) {
		for (int i = 0; i < modules.size(); i++) {
			modules.get(i).onRender(event);
		}
	}
	
	public static void callOnTick(Event event) {
			for (int i = 0; i < modules.size(); i++) {
				modules.get(i).onTick(event);
		}
	}
	
	public static void registerModules() {
			modules.add(new Reach(Categorys.COMBAT, "Reach"));
			modules.add(new Keepsprint(Categorys.MOVEMENT, "Keepsprint"));
			modules.add(new WTAP(Categorys.COMBAT, "WTAP"));
			modules.add(new Triggerbot(Categorys.COMBAT, "Triggerbot"));
			modules.add(new Velocity(Categorys.COMBAT, "Velocity"));
			modules.add(new Hitbox(Categorys.COMBAT, "Hitbox"));
			modules.add(new ModuleList(Categorys.RENDER, "ArrayList"));
			for (int i = 0; i < modules.size(); i++) {
				modules.get(i).init();
				modules.get(i).postInit(i);
		}
	}
	
	public static Module getModuleByName(String name) {
		
		for (int i = 0; i < ModuleManager.modules.size(); i ++) {
			if (ModuleManager.modules.get(i).name.toLowerCase().equals(name.toLowerCase())) {
				return ModuleManager.modules.get(i);
			}
		}
		return null;
		
	}
}
