package mods.elevator.init;

import mods.elevator.items.ItemPart;
import mods.elevator.items.ItemProtecter;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class Items
{
	
	public static Item redstoneChip = new ItemPart("item.redstoneChip");
	public static Item ironChip = new ItemPart("item.ironChip");
	public static Item goldChip = new ItemPart("item.goldChip");
	public static Item enderChip = new ItemPart("item.enderChip");
	public static Item protecter = new ItemProtecter();
	
	public static void init()
	{
		GameRegistry.registerItem(redstoneChip, "item.redstoneChip");
		GameRegistry.registerItem(ironChip, "item.ironChip");
		GameRegistry.registerItem(goldChip, "item.goldChip");
		GameRegistry.registerItem(enderChip, "item.enderChip");
		GameRegistry.registerItem(protecter, "item.protecter");
	}
	
}
