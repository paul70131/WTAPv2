package com.Paul70131.WTAPv2.client.module.modules;

import org.lwjgl.input.Keyboard;

import com.Paul70131.WTAPv2.client.module.Categorys;
import com.Paul70131.WTAPv2.client.module.GuiReference;
import com.Paul70131.WTAPv2.client.module.Module;
import com.Paul70131.WTAPv2.client.module.ModuleManager;
import com.Paul70131.WTAPv2.client.module.Value;
import com.Paul70131.WTAPv2.client.util.Timer;

import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public class WTAP extends Module {
	
	private Timer timer = new Timer();
	
	private int stage = 0;
	private boolean active = false;

	public WTAP(Categorys category, String name) {
		super(category, name);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void init() {
		this.valueList.add(new Value("distance", 3, 2, 4, GuiReference.GUISLIDER));
		this.valueList.add(new Value("duration", 120, 50, 250, GuiReference.GUISLIDER));
		this.valueList.add(new Value("interval", 500, 0, 1000, GuiReference.GUISLIDER));
		this.valueList.add(new Value("stap", 0, GuiReference.GUIBUTTON));
	}
	
	
	@Override
	public void onUpdate(Event event) {
		
		if (this.state && event instanceof AttackEntityEvent) {
			if (this.stage == 0 && this.timer.checkTimer()) {
				this.stage = 1;
				timer.resetRandDelay(this.getValue("duration").value);
			}
		}
		if (this.state && event instanceof LivingUpdateEvent) {
			if (mc.objectMouseOver.typeOfHit == MovingObjectType.ENTITY) {
				if (this.stage == 1 && mc.thePlayer.getDistanceToEntity(mc.objectMouseOver.entityHit) < this.getValue("distance").value) {
					mc.gameSettings.keyBindForward.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(), false);
					
					if (this.getValue("stap").value == 1) {
						mc.gameSettings.keyBindBack.setKeyBindState(mc.gameSettings.keyBindBack.getKeyCode(), true);
					} 
				} 
			}
			if (this.stage == 1 && timer.checkTimer()) {
				this.stage = 2;
			}

			if (this.stage == 2) {
				if (Keyboard.isKeyDown(mc.gameSettings.keyBindForward.getKeyCode())) {
					mc.gameSettings.keyBindForward.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(), true);
				}
				if (this.getValue("stap").value == 1) {
					mc.gameSettings.keyBindBack.setKeyBindState(mc.gameSettings.keyBindBack.getKeyCode(), false);
				}
				this.stage = 0;
				this.timer.resetDelay(this.getValue("interval").value);
			}
		}
	}
}
