package com.Paul70131.WTAPv2.client.module.modules;

import javax.vecmath.Vector2d;

import com.Paul70131.WTAPv2.client.module.Categorys;
import com.Paul70131.WTAPv2.client.module.GuiReference;
import com.Paul70131.WTAPv2.client.module.Module;
import com.Paul70131.WTAPv2.client.module.Value;

import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public class Velocity extends Module {
	
	private double realVal = 0;

	public Velocity(Categorys category, String name) {
		super(category, name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void init() {
		this.valueList.add(new Value("Reducer", 95, 80, 100, GuiReference.GUISLIDER));
	}
	
	@Override
	public void onUpdate(Event event) {
		if (this.state && event instanceof LivingUpdateEvent) {

			if (mc.thePlayer.hurtTime > 0 && this.stage == 0) {
				this.stage = 1;
				Minecraft.getMinecraft().thePlayer.addChatMessage( new ChatComponentText("INITIALIZINGVELOCITYYY"));
			}
		}
		if (this.state && event instanceof LivingUpdateEvent) {
			
			if (this.stage == 1 && !mc.thePlayer.onGround) {
				Minecraft.getMinecraft().thePlayer.addChatMessage( new ChatComponentText("STARTVELOCITYYY"));
				this.stage = 2;
			} else if (this.stage == 2 && !mc.thePlayer.onGround) {
				
				Minecraft.getMinecraft().thePlayer.addChatMessage( new ChatComponentText("VELOCITYYY"));

				mc.thePlayer.motionX = mc.thePlayer.motionX + ((Math.sin(mc.thePlayer.rotationYaw * 0.017453292) * (this.getValue("reducer").value / 100) / 10));
		        mc.thePlayer.motionZ = mc.thePlayer.motionZ + ((Math.cos(mc.thePlayer.rotationYaw * 0.017453292) * (this.getValue("reducer").value / 100) / 10));
				
				
			} else if (this.stage == 2 && mc.thePlayer.onGround) {
				this.stage = 0;
			}
		}
	}
}
