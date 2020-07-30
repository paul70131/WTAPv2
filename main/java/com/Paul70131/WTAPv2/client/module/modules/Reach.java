package com.Paul70131.WTAPv2.client.module.modules;

import com.Paul70131.WTAPv2.client.module.Categorys;
import com.Paul70131.WTAPv2.client.module.GuiReference;
import com.Paul70131.WTAPv2.client.module.Module;
import com.Paul70131.WTAPv2.client.module.Value;
import com.Paul70131.WTAPv2.client.util.Timer;
import com.Paul70131.WTAPv2.client.util.rayTrace;

import akka.japi.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public class Reach extends Module {
	
	private int stage = 0;
	
	private Timer timer = new Timer();

	public Reach(Categorys category, String name) {
		super(category, name);
		// TODO Auto-generated constructor stub
	}
		
	public void update() {
		if (timer.checkTimer() && this.stage == 1) {
			this.stage = 0;
		}
		if (this.getValue("ComboMode").value == 0 || this.stage == 0) {
			if (mc.objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.ENTITY) {
				rayTrace.getRayTrace(this.getValue("Reach").value);
			}
			if (mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && this.getValue("ComboMode").value == 1) {
				timer.resetDelay(this.getValue("ComboTimer").value);
				this.stage = 1;
			}
		}
		if (this.getValue("ComboMode").value == 1 && this.stage == 1) {

			if (mc.objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.ENTITY) {
				rayTrace.getRayTrace(this.getValue("ComboReach").value);
			}
			if (mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
				timer.resetDelay(this.getValue("ComboTimer").value);
			}
		}
	}
	@Override
	public void init() {
		this.valueList.add(new Value("Reach", 3, 3, 4, GuiReference.GUISLIDER));
		this.valueList.add(new Value("ComboReach", 3, 3, 4, GuiReference.GUISLIDER));
		this.valueList.add(new Value("ComboTimer", 50, 250, 500, GuiReference.GUISLIDER));
		this.valueList.add(new Value("ComboMode", 0, GuiReference.GUIBUTTON));
	}
	 
	@Override
	public void onUpdate(Event event) {
		if(this.state && event instanceof MouseEvent) {
			if (((MouseEvent)event).button == 0) {
				this.update();
			}
		}
	}
}
