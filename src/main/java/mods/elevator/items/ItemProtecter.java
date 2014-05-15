package mods.elevator.items;

import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.elevator.init.Blocks;
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
					}else if(player.isSneaking() && !player.onGround)
					{
						elevator.list.clear();
						player.addChatMessage(new ChatComponentText("You cleared the whitelist"));
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
					}else if(player.isSneaking() && !elevator.list.contains(player.getDisplayName()))
					{
						elevator.list.add(player.getDisplayName());
						player.addChatMessage(new ChatComponentText("You are now on the whitelist"));
					}else if(player.isSneaking() && elevator.list.contains(player.getDisplayName()))
					{
						elevator.list.remove(player.getDisplayName());
						player.addChatMessage(new ChatComponentText("You are nolonger on the whitelist"));
					}
				}
			}
		}
		return true;
	}
	
	public boolean isinhand(EntityPlayer player)
	{
		return player.getHeldItem() != null && player.getHeldItem().getItem().getUnlocalizedName().equalsIgnoreCase(getUnlocalizedName());
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
		
		list.add((isinhand(player) ? "\247c" : "") + "!! You cannot use the elevator");
		list.add((isinhand(player) ? "\247c" : "") + "while the item is in you hand !!");
		list.add("Sneak and Rightclick - Add or remove");
		list.add("Sneak and Rightclick in air - Clear list");
		list.add("Rightclick - look at then list");
		super.addInformation(stack, player, list, par4);
	}


}
