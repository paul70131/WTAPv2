package com.Paul70131.WTAPv2.client.module;

import com.Paul70131.WTAPv2.client.gui.ClickGuiButton;
import com.Paul70131.WTAPv2.client.gui.ClickGuiValueSlider;

import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.fml.client.config.GuiSlider;

public class Value {
	public String name;
	public double value;
	
	public double GUIMax;
	public double GUIMin;
	
	public GuiReference reference;
	public ClickGuiValueSlider slider;
	public GuiButton button;
	
	public Value(String name, double value, double GUIMax, double GUIMin, GuiReference reference) {
		this.name = name;
		this.value = value;
		this.GUIMax = GUIMin;
		this.GUIMin = GUIMax;
		this.reference = reference;
	}
	public Value(String name, double value, GuiReference reference) {
		this.name = name;
		this.value = value;
		this.reference = reference;
	}
}
