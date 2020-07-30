package com.Paul70131.WTAPv2.client.command.commands;

import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import org.lwjgl.input.Keyboard;

import com.Paul70131.WTAPv2.client.module.Module;
import com.Paul70131.WTAPv2.client.module.ModuleManager;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class Bind extends CommandBase {

	@Override
	public int getRequiredPermissionLevel()
    {
        return 0;
    }
	
	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "bind";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "/bind (module) (key)";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		// TODO Auto-generated method stub
		if (sender instanceof EntityPlayer && args.length == 2) {
			Module module = ModuleManager.getModuleByName(args[0]);
			if (module != null) {
				ModuleManager.getModuleByName(args[0]).getValue("keybind").value = args[1].charAt(0);
				Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("[WTAPv2]: Bound " + module.name + " to " + args[1]));
			} else {
				Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("[WTAPv2]: Couldn't find module " + args[0]));
			} 
		} else {
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("[WTAPv2]: Invalid usage"));
		}
	}
}
