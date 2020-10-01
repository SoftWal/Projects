package com.aww_man.Teleportation;

import com.aww_man.Teleportation.Tabs.MODTABS;
import com.aww_man.Teleportation.Tabs.ModArmorTab;
import com.aww_man.Teleportation.Tabs.ModBlockTab;
import com.aww_man.Teleportation.Tabs.ModMaterialTab;
import com.aww_man.Teleportation.Tabs.ModToolsTab;
import com.aww_man.Teleportation.events.PayerInventoryFailSafe;
import com.aww_man.Teleportation.events.VoidSTaffEvent;
import com.aww_man.Teleportation.init.EntityInit;
import com.aww_man.Teleportation.init.ModSmelting;
import com.aww_man.Teleportation.proxy.CommonProxy;
import com.aww_man.Teleportation.util.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import world.ModWorldGen;

@Mod(modid=Reference.MOD_ID,name=Reference.NAME,version=Reference.VERSION)
public class Main {
	public static final CreativeTabs modtabs= new MODTABS("modtabs");
	public static final CreativeTabs modarmortabs= new ModArmorTab("modarmortabs");
	public static final CreativeTabs modtoolstabs= new ModToolsTab("modtoolstabs");
	public static final CreativeTabs modmaterialtabs= new ModMaterialTab("modmaterialtabs");
	public static final CreativeTabs modblocktabs= new ModBlockTab("modblocktabs");
	@Instance
	public static Main instance;
	@SidedProxy(clientSide=Reference.CLIENT_PROXY_CLASS,serverSide=Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
		PayerInventoryFailSafe safe = new PayerInventoryFailSafe();
		MinecraftForge.EVENT_BUS.register(safe);
		VoidSTaffEvent events = new VoidSTaffEvent();
		MinecraftForge.EVENT_BUS.register(events);
		GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);
		EntityInit.registerEntities();
	}
	@EventHandler
	public static void Init(FMLInitializationEvent event) {
		ModSmelting.init();
		
		
	}
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) {
		
	}
}
