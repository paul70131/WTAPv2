package com.Paul70131.WTAPv2.client.module;

import java.util.ArrayList;

import com.Paul70131.WTAPv2.client.gui.ClickGuiButton;
import com.Paul70131.WTAPv2.client.gui.ClickGuiValueButton;
import com.Paul70131.WTAPv2.client.gui.ClickGuiValueSlider;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.Event;

public class Module {
	public boolean state = false;
	public String name;
	
	public Categorys category;
	
	public ArrayList<Value> valueList = new ArrayList<Value>();
	
	protected Minecraft mc = Minecraft.getMinecraft();
	
	public ClickGuiButton button;
	public boolean guiexpanded = false;
	public int stage;
	
	public Module(Categorys category, String name) {
		this.category = category;
		this.state = false;
		this.name = name;
	}
	
	public Value getValue(String name) {
		for (int i = 0; i < this.valueList.size(); i++) {
			if (this.valueList.get(i).name.equalsIgnoreCase(name)) {
				return this.valueList.get(i);
			}
		}
		return null;
	}
	public void init() {
	}
	public void postInit(int ID) {
		this.valueList.add(new Value("keybind", 23920184, GuiReference.NONE));	
		this.button = new ClickGuiButton(ID * 10, 0, 0, 90, 20, this.name);
		for (int i = 0; i < this.valueList.size(); i++) {
			if (this.valueList.get(i).reference == GuiReference.GUIBUTTON) {
				this.valueList.get(i).button = new ClickGuiValueButton(this.button.id + i +1, 0, 0, 80, 20, this.valueList.get(i).name, this.name);
			} else if (this.valueList.get(i).reference == GuiReference.GUISLIDER) {
				this.valueList.get(i).slider = new ClickGuiValueSlider(this.button.id + i + 1, 0, 0, 80, 20, this.valueList.get(i).name, "", this.valueList.get(i).GUIMin, this.valueList.get(i).GUIMax, this.valueList.get(i).value, true, true, this.name);
			}
		}
		
	}
	public void toggle() {
		if (!this.state) {
			this.state = true;
			this.onEnable();
		} else {
			this.state = false;
			this.onDisable();
		}
	}
	
	public void onEnable() {
		
	}
	
	public void onUpdate(Event event) {
		
	}
	
	public void onDisable() {
	
	}
	
	public void onTick(Event event) {
		
	}

	public void onRender(Event event) {
		
	}

	public void onRenderGui(Event event) {
		
	}
}
