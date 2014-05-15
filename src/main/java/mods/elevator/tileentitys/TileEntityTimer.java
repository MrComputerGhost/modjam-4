package mods.elevator.tileentitys;

import mods.elevator.blocks.BlockTimer;
import mods.elevator.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.FMLCommonHandler;

public class TileEntityTimer extends TileEntity
{
	
	public int tick = 0;
	
	public void updateEntity()
	{
		if(FMLCommonHandler.instance().getEffectiveSide().isServer())
		{
			tick++;
			int tick2 = 20;
			Block b = worldObj.getBlock(xCoord, yCoord, zCoord);
			if(b != null)
			{
				System.out.println(tick);
			}
			if(b != null && b.getUnlocalizedName().equalsIgnoreCase(Blocks.timer.getUnlocalizedName()) && tick > tick2)
			{
				worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.timer_off);
				BlockTimer timer = (BlockTimer)b;
				timer.update(worldObj, xCoord, yCoord, zCoord);
				tick = 0;
			}else if(b != null && b.getUnlocalizedName().equalsIgnoreCase(Blocks.timer_off.getUnlocalizedName()) && tick > tick2)
			{
				worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.timer);
				BlockTimer timer = (BlockTimer)b;
				timer.update(worldObj, xCoord, yCoord, zCoord);
				System.out.println("Test");
				tick = 0;
			}
		}
	}
	
}
