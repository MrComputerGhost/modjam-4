package mods.elevator.init;

import cpw.mods.fml.common.FMLCommonHandler;
import mods.elevator.tileentitys.TileEntityElevator;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemProtecter extends Item
{

	public ItemProtecter()
	{
		setUnlocalizedName("item.protecter").setTextureName("elevator:"+"item.protecter");
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
	{
		if(FMLCommonHandler.instance().getEffectiveSide().isServer())
		{
			Block b = world.getBlock(x, y, z);
			if(b != null && b.getUnlocalizedName().equalsIgnoreCase(Blocks.elevator.getUnlocalizedName()))
			{
				TileEntityElevator elevator = (TileEntityElevator)world.getTileEntity(x, y, z);
				if(elevator != null)
				{
					if(player.isSneaking() && elevator.list.isEmpty())
					{
						elevator.list.add(player.getDisplayName());
						player.addChatMessage(new ChatComponentText("\247cThis Elevator is now Locked"));
						player.addChatMessage(new ChatComponentText("You are now on the whitelist"));
					}else if(!player.isSneaking() && !elevator.list.isEmpty())
					{
						player.addChatMessage(new ChatComponentText("Users: "));
						for(String s : elevator.list)
						{
							player.addChatMessage(new ChatComponentText("      "+s));	
						}
					}else if(!player.isSneaking() && elevator.list.isEmpty())
					{
						player.addChatMessage(new ChatComponentText("This Elevator is Unlocked"));
					}else if(player.isSneaking() && !elevator.list.isEmpty())
					{
						elevator.list.add(player.getDisplayName());
						player.addChatMessage(new ChatComponentText("You are now on the whitelist"));
					}
				}
			}
		}
		return true;
	}


}
