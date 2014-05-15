package mods.elevator.tileentitys;

import mods.elevator.blocks.BlockTimer;
import mods.elevator.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.FMLCommonHandler;

public class TileEntityTimer extends TileEntity
{
	
	public int tick = 0;
	public int maxTick = 1;
	
	public void readFromNBT(NBTTagCompound nbttag)
	{
		if(nbttag.hasKey("Tick"))
		{
			tick = nbttag.getInteger("Tick");
		}
		
		if(nbttag.hasKey("MaxTick"))
		{
			maxTick = nbttag.getInteger("MaxTick");
		}
		super.readFromNBT(nbttag);
	}
	
	public void writeToNBT(NBTTagCompound nbttag)
	{
		nbttag.setInteger("Tick", tick);
		nbttag.setInteger("MaxTick", maxTick);
		super.writeToNBT(nbttag);
	}

	public void updateEntity()
	{
		if(FMLCommonHandler.instance().getEffectiveSide().isServer())
		{
			tick++;
			Block b = worldObj.getBlock(xCoord, yCoord, zCoord);
			if(b != null && b.getUnlocalizedName().equalsIgnoreCase(Blocks.timer.getUnlocalizedName()) && tick > maxTick)
			{
				worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.timer_off);
				BlockTimer timer = (BlockTimer)b;
				timer.update(worldObj, xCoord, yCoord, zCoord);
				tick = 0;
			}else if(b != null && b.getUnlocalizedName().equalsIgnoreCase(Blocks.timer_off.getUnlocalizedName()) && tick > maxTick)
			{
				worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.timer);
				BlockTimer timer = (BlockTimer)b;
				timer.update(worldObj, xCoord, yCoord, zCoord);
				tick = 0;
			}
		}
	}
	
}
