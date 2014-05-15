package mods.elevator.tileentitys;

import mods.elevator.blocks.BlockTimer;
import mods.elevator.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.FMLCommonHandler;

public class TileEntityTimer extends TileEntity
{

	public void updateEntity()
	{
		if(FMLCommonHandler.instance().getEffectiveSide().isServer())
		{
			System.out.println("Test");
			Block b = worldObj.getBlock(xCoord, yCoord, zCoord);
			if(b != null && b.getUnlocalizedName().equalsIgnoreCase(Blocks.timer.getUnlocalizedName()))
			{
				world
			}else if(b != null && b.getUnlocalizedName().equalsIgnoreCase(Blocks.timer_off.getUnlocalizedName()))
			{
				BlockTimer bb = (BlockTimer)b;
				bb.redstone = !bb.redstone;
				bb.update(worldObj, xCoord, yCoord, zCoord);
			}
		}
	}
	
}
