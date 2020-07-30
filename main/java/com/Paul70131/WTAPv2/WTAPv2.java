package com.Paul70131.WTAPv2;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

import com.Paul70131.WTAPv2.client.EventManager;
import com.Paul70131.WTAPv2.client.command.CommandManager;
import com.Paul70131.WTAPv2.client.module.ModuleManager;

import net.minecraft.client.Minecraft;
import net.minecraft.network.NetworkManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = WTAPv2.MODID, version = WTAPv2.VERSION)

public class WTAPv2
{
    public static final String MODID = "WTAPv2";
    public static final String VERSION = "v2.0";
 
    
    public Minecraft mc = Minecraft.getMinecraft();
    
 
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	//start ModuleManager
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	MinecraftForge.EVENT_BUS.register(new EventManager());
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	ModuleManager.registerModules();
        CommandManager.init();
    	//init gui
    	// WTAPv2.authenticate();
    }
}