package mods.elevator.items;

import net.minecraft.item.Item;

public class ItemPart extends Item
{
	public ItemPart(String name)
	{
		setUnlocalizedName(name).setTextureName("elevator:"+name);
	}
}
