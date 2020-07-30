package com.Paul70131.WTAPv2.client.command.commands;

import com.Paul70131.WTAPv2.client.module.Module;
import com.Paul70131.WTAPv2.client.module.ModuleManager;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class Toggle extends CommandBase {
	
	@Override
	public int getRequiredPermissionLevel()
    {
        return 0;
    }


	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "toggle";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "/toggle (module)";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		// TODO Auto-generated method stub
		if (sender instanceof EntityPlayer && args.length == 1) {
			Module module = ModuleManager.getModuleByName(args[0]);
			if (module != null) {
				Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("[WTAPv2]: Toggled " + module.name));
				module.toggle();
			} else {
				Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("[WTAPv2]: Couldn't find module " + args[0]));
			} 
		} else {
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("[WTAPv2]: Invalid usage"));
		}
	}
}
