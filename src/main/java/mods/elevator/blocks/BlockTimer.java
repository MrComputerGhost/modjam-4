package mods.elevator.blocks;

import java.util.Random;

import mods.elevator.init.Blocks;
import mods.elevator.tileentitys.TileEntityTimer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;

public class BlockTimer extends BlockContainer
{
	
	public BlockTimer(Material material)
	{
		super(material);
		setBlockName("block.timer");
	}
	
	public void update(World world, int x, int y, int z)
	{
		world.notifyBlockOfNeighborChange(x-1, y, z, this);
		world.notifyBlockOfNeighborChange(x+1, y, z, this);
		world.notifyBlockOfNeighborChange(x, y, z-1, this);
		world.notifyBlockOfNeighborChange(x, y, z+1, this);
		world.notifyBlockOfNeighborChange(x, y+1, z, this);
		world.notifyBlockOfNeighborChange(x, y-1, z, this);
	}

	public void breakBlock(World world, int x, int y, int z, Block block, int arg5)
	{
		update(world, x, y, z);
		world.setTileEntity(x, y, z, null);
	}

	public int isProvidingWeakPower(IBlockAccess iBlockAccess, int x, int y, int z, int side)
	{
		return 15;
	}

	public void updateTick(World world, int x, int y, int z, Random random)
	{
	}

	public int tickRate(World world)
	{
		return 1;
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		boolean up = false;
		if(!player.isSneaking())
		{
			up = true;
		}
		
		if(FMLCommonHandler.instance().getEffectiveSide().isServer())
		{
			Block b = world.getBlock(x, y, z);
			if(b != null && (b.getUnlocalizedName().equalsIgnoreCase(Blocks.timer.getUnlocalizedName()) || b.getUnlocalizedName().equalsIgnoreCase(Blocks.timer_off.getUnlocalizedName())))
			{
				TileEntityTimer timer = (TileEntityTimer)world.getTileEntity(x, y, z);
				if(timer != null)
				{
					if(up)
					{
						timer.maxTick++;
					}else if(timer.maxTick > 1)
					{
						timer.maxTick--;
					}
					player.addChatMessage(new ChatComponentText("Ticks: "+timer.maxTick));
				}
			}
		}
		
		return true;
	}

	public void onBlockAdded(World world, int x, int y, int z)
	{
		update(world, x, y, z);
	}

	public boolean canProvidePower()
	{
		return true;
	}

	public TileEntity createNewTileEntity(World world, int arg1)
	{
		return new TileEntityTimer();
	}


}
