package mods.elevator.blocks;

import java.util.Random;

import cpw.mods.fml.common.FMLCommonHandler;

import mods.elevator.init.Blocks;
import mods.elevator.tileentitys.TileEntityTimer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTimerOff extends BlockTimer
{
	
	public boolean redstone = false;

	public BlockTimerOff(Material material)
	{
		super(material);
		setBlockName("block.timeroff");
	}
	
	public int isProvidingWeakPower(IBlockAccess iBlockAccess, int x, int y, int z, int side)
	{
		return 0;
	}
}
