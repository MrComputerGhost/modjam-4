package mods.elevator.tileentitys;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.FMLCommonHandler;

public class TileentityElevator extends TileEntity
{

	public void readFromNBT(NBTTagCompound nbttag)
	{
		super.readFromNBT(nbttag);
	}

	public EntityPlayer getPlayer()
	{
		if(FMLCommonHandler.instance().getEffectiveSide().isServer())
		{
			for(Object object : worldObj.playerEntities)
			{
				if(object instanceof EntityPlayer)
				{
					EntityPlayer player = (EntityPlayer)object;
					if(player.getDistanceToEntity(player) > 2)
					{
						System.out.println("true");
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
		getPlayer();
	}

	public void writeToNBT(NBTTagCompound nbttag)
	{
		super.writeToNBT(nbttag);
	}

}
