package mods.elevator.init;

import mods.elevator.items.ItemPart;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class Items
{
	
	public static Item ironChip = new ItemPart();
	public static Item goldChip = new ItemPart();
	public static Item enderChip = new ItemPart();
	
	public static void init()
	{
		GameRegistry.registerItem(ironChip, "item.ironChip");
		GameRegistry.registerItem(goldChip, "item.ironChip");
		GameRegistry.registerItem(enderChip, "item.ironChip");
	}
	
}
