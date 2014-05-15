package mods.elevator.init;

import mods.elevator.blocks.BlockElevator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;

public class Blocks
{
	
	public static Block elevator = new BlockElevator(Material.iron);
	
	public static void init()
	{
		GameRegistry.registerBlock(elevator, "elevator.elevator");
	}
	
}
