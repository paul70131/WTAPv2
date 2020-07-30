package com.Paul70131.WTAPv2.client.module.modules;

import com.Paul70131.WTAPv2.client.module.Categorys;
import com.Paul70131.WTAPv2.client.module.GuiReference;
import com.Paul70131.WTAPv2.client.module.Module;
import com.Paul70131.WTAPv2.client.module.ModuleManager;
import com.Paul70131.WTAPv2.client.module.Value;
import com.Paul70131.WTAPv2.client.util.Timer;
import com.Paul70131.WTAPv2.client.util.rayTrace;

import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public class Triggerbot extends Module {
	
	Timer timer = new Timer();
	Timer overTimer = new Timer();
	
	private int stage = 0;

	public Triggerbot(Categorys category, String name) {
		super(category, name);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void init() {
		this.valueList.add(new Value("Delay", 80, 1, 200, GuiReference.GUISLIDER));
		this.valueList.add(new Value("FirstHitDelay", 0, 0, 100, GuiReference.GUISLIDER));
	}
	
	@Override 
	public void onUpdate(Event event) {
		if (this.state && event instanceof LivingUpdateEvent) {
			if (this.overTimer.checkTimer() && this.getValue("FirstHitDelay").value == 0) {
				this.stage = 0;
			}
			
			if (this.getValue("firsthitdelay").value > 1 && this.stage == 0) {
				this.stage = 1;
				this.timer.resetDelay((long) this.getValue("firsthitdelay").value);
			}
		
			if (this.stage == 1 || this.getValue("firsthitdelay").value == 0) {
				if (mc.objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.ENTITY) {
					if (ModuleManager.getModuleByName("Reach").state) {
						((Reach)ModuleManager.getModuleByName("Reach")).update();
					}
					if (this.getValue("FirstHitDelay").value == 0) {
					this.overTimer.resetDelay(500);
					}
				}
				if (mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
					mc.thePlayer.swingItem();
					mc.playerController.attackEntity(mc.thePlayer, mc.objectMouseOver.entityHit);
					timer.resetRandDelay((long) this.getValue("Delay").value);
					
				}
			}
		}
	}
}
