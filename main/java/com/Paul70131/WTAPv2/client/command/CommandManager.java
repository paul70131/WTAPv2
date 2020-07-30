package com.Paul70131.WTAPv2.client.command;

import com.Paul70131.WTAPv2.client.command.commands.Bind;
import com.Paul70131.WTAPv2.client.command.commands.Toggle;

import net.minecraftforge.client.ClientCommandHandler;

public class CommandManager {
	
	public static void init() {
		ClientCommandHandler.instance.registerCommand(new Toggle());
		ClientCommandHandler.instance.registerCommand(new Bind());
	}
}
