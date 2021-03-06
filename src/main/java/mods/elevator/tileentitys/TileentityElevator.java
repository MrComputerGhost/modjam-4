package mods.elevator.tileentitys;

import java.util.ArrayList;
import java.util.List;

import mods.elevator.init.Blocks;
import mods.elevator.init.Items;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.Direction;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.common.FMLCommonHandler;

public class TileEntityElevator extends TileEntity
{
	
	public List<String> list = new ArrayList<String>();
	
	public void readFromNBT(NBTTagCompound nbttag)
	{
		if(nbttag.hasKey("Names"))
		{
			String tag = nbttag.getString("Names");
			for(String name : tag.split(";"))
			{
				if(!name.isEmpty())
				{
					list.add(name);
				}
			}
		}
		super.readFromNBT(nbttag);
	}
	
	public void writeToNBT(NBTTagCompound nbttag)
	{
		String tag = "";
		for(String s : list)
		{
			tag += s+";";
		}
		nbttag.setString("Names", tag);
		super.writeToNBT(nbttag);
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
					if(player.posY > yCoord && player.posY < yCoord+1.5)
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
		boolean item = false;
		if(player.getHeldItem() != null && player.getHeldItem().getItem().getUnlocalizedName().equalsIgnoreCase(Items.protecter.getUnlocalizedName()))
		{
			item = true;
		}
		return (list.isEmpty() || list.contains(player.getDisplayName()) && player.getHeldItem() != null) && !item;
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
		boolean locked = false;
		for(int xx = 0; xx < Math.abs(x)*16; xx++)
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
							worldObj.playSoundEffect(xCoord, yCoord+1, zCoord, "elevator:elevator", 100, 100);
							worldObj.playSoundEffect(elevator.xCoord, elevator.yCoord+1, elevator.zCoord, "elevator:elevator", 100, 100);
							player.setPositionAndUpdate(x > 0 ? xCoord+xx+0.5 : xCoord-xx+0.5, yCoord+1, zCoord+0.5);
							player.swingProgressInt = 0;
							if(locked)
							{
								locked = false;
								player.addChatMessage(new ChatComponentText("\247cYou have pass a locked elevator"));
							}
							break;
						}else
						{
							locked = true;
						}
					}
				}
			}
		}
		for(int zz = 0; zz < Math.abs(z)*16; zz++)
		{
			if(zz != 0)
			{
				Block b = worldObj.getBlock(xCoord, yCoord, z > 0 ? zCoord+zz : zCoord-zz);
				if(b != null && b.getUnlocalizedName().equalsIgnoreCase(Blocks.elevator.getUnlocalizedName()))
				{
					TileEntityElevator elevator = (TileEntityElevator)worldObj.getTileEntity(xCoord, yCoord, z > 0 ? zCoord+zz : zCoord-zz);
					if(elevator != null)
					{
						if(elevator.canTeleport(player))
						{
							worldObj.playSoundEffect(xCoord, yCoord+1, zCoord, "elevator:elevator", 100, 100);
							worldObj.playSoundEffect(elevator.xCoord, elevator.yCoord+1, elevator.zCoord, "elevator:elevator", 100, 100);
							player.setPositionAndUpdate(xCoord+0.5, yCoord+1, z > 0 ? zCoord+zz+0.5 : zCoord-zz+0.5);
							player.swingProgressInt = 0;
							if(locked)
							{
								locked = false;
								player.addChatMessage(new ChatComponentText("\247cYou have pass a locked elevator"));
							}
							break;
						}else
						{
							locked = true;
						}
					}
				}
			}
		}
	}
	
	public void teleportonTop(EntityPlayer player)
	{
		boolean locked = false;
		for(int yy = 0; yy < 24; yy++)
		{
			if(yy != 0)
			{
				Block b = worldObj.getBlock(xCoord, !player.isSneaking() ? yCoord+yy : yCoord-yy, zCoord);
				if(b != null && b.getUnlocalizedName().equalsIgnoreCase(Blocks.elevator.getUnlocalizedName()))
				{
					TileEntityElevator elevator = (TileEntityElevator)worldObj.getTileEntity(xCoord, !player.isSneaking() ? yCoord+yy : yCoord-yy, zCoord);
					if(elevator != null)
					{
						if(elevator.canTeleport(player))
						{
							worldObj.playSoundEffect(xCoord, yCoord+1, zCoord, "elevator:elevator", 100, 100);
							worldObj.playSoundEffect(elevator.xCoord, elevator.yCoord+1, elevator.zCoord, "elevator:elevator", 100, 100);
							player.setPositionAndUpdate(xCoord+0.5, !player.isSneaking() ? yCoord+yy+1 : yCoord-yy+1, zCoord+0.5);
							player.motionY = 0;
							if(locked)
							{
								locked = false;
								player.addChatMessage(new ChatComponentText("\247cYou have pass a locked elevator"));
							}
							break;
						}else
						{
							locked = true;
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
			}else if(player != null && player.motionY > 0.1)
			{
				teleportonTop(player);
			}
		}
	}

}
