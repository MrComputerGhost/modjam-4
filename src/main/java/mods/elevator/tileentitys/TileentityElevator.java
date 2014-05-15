package mods.elevator.tileentitys;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileentityElevator extends TileEntity
{
	
	public void readFromNBT(NBTTagCompound nbttag)
	{
		super.readFromNBT(nbttag);
	}
	
	public EntityPlayer getPlayer()
	{
		for(Object object : worldObj.playerEntities)
		{
			if(object instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer)object;
				if(player.posX > xCoord-0.35 && player.posX < xCoord+0.35)
				{
					System.out.println("X");
					if(player.posY > yCoord-0.35 && player.posY < yCoord+0.35)
					{
						System.out.println("Y");
						if(player.posZ > zCoord-0.35 && player.posZ < zCoord+0.35)
						{
							System.out.println("Z");
						}
					}
				}
			}
			
		}
		return null;
	}
	
	public String getDirection(EntityPlayer player)
	{
		return null;
	}
	
	public void updateEntity()
	{
		
	}
	
	public void writeToNBT(NBTTagCompound nbttag)
	{
		super.writeToNBT(nbttag);
	}
	
}
