package mods.elevator.tileentitys;

import mods.elevator.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.common.FMLCommonHandler;

public class TileEntityElevator extends TileEntity
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
				if(player.posX > xCoord-0.35 && player.posX < xCoord+1.35)
				{
					if(player.posY > yCoord && player.posY < yCoord+1.35)
					{
						if(player.posZ > zCoord-0.35 && player.posZ < zCoord+1.35)
						{
							return player;
						}
					}
				}
			}

		}

		return null;
	}
	
	public boolean canTeleport(EntityPlayer player)
	{
		return true;
	}

	public double getDirectionDouble(EntityPlayer player)
	{
		return MathHelper.floor_double((double)(player.rotationYaw * 4.0D / 360.0D) + 0.5D) & 3;
	}
	
	public int getDirectiononBlocksX(EntityPlayer player)
	{
		String dirString = getDirection(player);
		if(dirString.equalsIgnoreCase("east"))
		{
			return 1;
		}else if(dirString.equalsIgnoreCase("west"))
		{
			return -1;
		}
		return 0;
	}
	
	public int getDirectiononBlocksZ(EntityPlayer player)
	{
		String dirString = getDirection(player);
		if(dirString.equalsIgnoreCase("south"))
		{
			return 1;
		}else if(dirString.equalsIgnoreCase("north"))
		{
			return -1;
		}
		return 0;
	}

	public String getDirection(EntityPlayer player)
	{
		if(player != null)
		{
			double dir = getDirectionDouble(player);
			return Direction.directions[(int)dir];
		}else
		{
			return "";
		}
	}
	
	public void teleportonSide(EntityPlayer player)
	{
		int x = getDirectiononBlocksX(player);
		int z = getDirectiononBlocksZ(player);
		for(int xx = 0; xx < Math.abs(x)*8; xx++)
		{
			if(xx != 0)
			{
				Block b = worldObj.getBlock(x > 0 ? xCoord+xx : xCoord-xx, yCoord, zCoord);
				if(b != null && b.getUnlocalizedName().equalsIgnoreCase(Blocks.elevator.getUnlocalizedName()))
				{
					TileEntityElevator elevator = (TileEntityElevator)worldObj.getTileEntity(x > 0 ? xCoord+xx : xCoord-xx, yCoord, zCoord);
					if(elevator != null)
					{
						if(elevator.canTeleport(player))
						{
							player.setPositionAndUpdate(x > 0 ? xCoord+xx : xCoord-xx, yCoord+1, zCoord);
						}
					}
				}
			}
		}
		for(int zz = 0; zz < Math.abs(z)*8; zz++)
		{
			if(zz != 0)
			{
				Block b = worldObj.getBlock(xCoord, yCoord, z > 0 ? zCoord+zz : zCoord-zz);
				if(b != null && b.getUnlocalizedName().equalsIgnoreCase(Blocks.elevator.getUnlocalizedName()))
				{
					TileEntityElevator elevator = (TileEntityElevator)worldObj.getTileEntity(x > 0 ? xCoord+xx : xCoord-xx, yCoord, zCoord);
					if(elevator != null)
					{
						if(elevator.canTeleport(player))
						{
							player.setPositionAndUpdate(x > 0 ? xCoord+xx : xCoord-xx, yCoord+1, zCoord);
						}
					}
				}
			}
		}
	}

	public void updateEntity()
	{
		if(FMLCommonHandler.instance().getEffectiveSide().isServer())
		{
			EntityPlayer player = getPlayer();
			if(player != null && player.swingProgressInt == 1)
			{
				teleportonSide(player);
			}
		}
	}

	public void writeToNBT(NBTTagCompound nbttag)
	{
		super.writeToNBT(nbttag);
	}

}
