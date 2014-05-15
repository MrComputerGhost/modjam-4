package mods.elevator.init;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes
{
	
	public static void init()
	{
		GameRegistry.addRecipe(new ItemStack(Items.ironChip), "RRR", "IRI", "RRR", 'R', net.minecraft.init.Items.redstone, 'I', net.minecraft.init.Items.iron_ingot);
		GameRegistry.addRecipe(new ItemStack(Items.goldChip), "RRR", "GIG", "RRR", 'R', net.minecraft.init.Items.redstone, 'G', net.minecraft.init.Items.gold_ingot, 'I', Items.ironChip);
		GameRegistry.addRecipe(new ItemStack(Items.enderChip), "RRR", "GEG", "RRR", 'R', net.minecraft.init.Items.redstone, 'E', net.minecraft.init.Items.ender_pearl, 'G', Items.goldChip);
		GameRegistry.addRecipe(new ItemStack(Items.protecter), "GIG", "IEI", "GIG", 'G', Items.goldChip, 'I', Items.ironChip, 'E', Items.enderChip);
		GameRegistry.addRecipe(new ItemStack(Blocks.elevator), "WIW", "IEI", "WIW", 'W', net.minecraft.init.Blocks.wool, 'I', Items.ironChip, 'E', Items.enderChip);
	}
	
}
