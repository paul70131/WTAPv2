package com.Paul70131.WTAPv2.client;

import java.util.UUID;

import org.lwjgl.input.Keyboard;

import com.Paul70131.WTAPv2.WTAPv2;
import com.Paul70131.WTAPv2.client.gui.ClickGui;
import com.Paul70131.WTAPv2.client.module.ModuleManager;
import com.mojang.authlib.GameProfile;

import io.netty.channel.ChannelHandler;

import com.Paul70131.WTAPv2.client.module.modules.Keepsprint;

import net.minecraft.client.Minecraft;
import net.minecraft.network.EnumConnectionState;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;

public class EventManager {
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	private boolean keyFired = false;
	private boolean mmbDown = false;

	@SubscribeEvent
	public void onKeyPress(InputEvent.KeyInputEvent event) {
		if (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
			mc.displayGuiScreen(new ClickGui());
		}
		for (int i = 0; i < ModuleManager.modules.size(); i++) {
			if (ModuleManager.modules.get(i).getValue("keybind").value == Keyboard.getEventCharacter()) {
				ModuleManager.modules.get(i).toggle();
			}
		}
	}
	@SubscribeEvent
	public void onTick(TickEvent.ClientTickEvent event) {
		ModuleManager.callOnTick(event);
	}
	@SubscribeEvent
	public void onMouseClick(MouseEvent event) {
		ModuleManager.callOnUpdate(event);
	}
	@SubscribeEvent
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
		if (event.entity == mc.thePlayer) {
			ModuleManager.callOnUpdate(event);
		}
	}
	@SubscribeEvent
	public void onAttackEntity(AttackEntityEvent event) {
		ModuleManager.callOnUpdate(event);
	}
	
	@SubscribeEvent
	public void onRender(TickEvent.RenderTickEvent event) {
		if (mc.thePlayer != null && mc.theWorld != null) {
			ModuleManager.callOnRender(event);
		}
	
	}
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onRenderGui(RenderGameOverlayEvent event) {
		ModuleManager.callOnRender(event);

	}
}
