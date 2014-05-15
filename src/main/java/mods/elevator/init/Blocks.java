package mods.elevator.init;

import mods.elevator.blocks.BlockElevator;
import mods.elevator.blocks.BlockTimer;
import mods.elevator.tileentitys.TileEntityElevator;
import mods.elevator.tileentitys.TileEntityTimer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;

public class Blocks
{
	
	public static Block elevator = new BlockElevator(Material.iron);
	public static Block timer = new BlockTimer(Material.wood);
	public static Block timer_off = new BlockTimer(Material.wood);
	
	public static void init()
	{
		GameRegistry.registerBlock(elevator, "block.elevator");
		GameRegistry.registerBlock(timer, "block.timer");
		GameRegistry.registerBlock(timer_off, "block.timer_off");
		GameRegistry.registerTileEntity(TileEntityElevator.class, "tileentity.elevator");
		GameRegistry.registerTileEntity(TileEntityTimer.class, "tileentity.timer");
	}
	
}
