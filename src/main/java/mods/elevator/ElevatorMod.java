package mods.elevator;

import mods.elevator.init.Blocks;
import mods.elevator.init.Items;
import mods.elevator.init.Recipes;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ElevatorMod.MODID, version = ElevatorMod.VERSION)
public class ElevatorMod
{
	public static final String MODID = "elevator";
	public static final String VERSION = "1.7.2-1.0";

	@EventHandler
	public void preinit(FMLPreInitializationEvent event)
	{
		Blocks.init();
		Items.init();
		Recipes.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
	}
	
	@EventHandler
	public void postinit(FMLPostInitializationEvent event)
	{
	}
}
