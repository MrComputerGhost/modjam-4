package mods.elevator.blocks;

import java.util.Random;

import cpw.mods.fml.common.FMLCommonHandler;

import mods.elevator.init.Blocks;
import mods.elevator.tileentitys.TileEntityTimer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTimer extends BlockContainer
{
	
	public boolean redstone = false;

	public BlockTimer(Material material)
	{
		super(material);
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
	}

	public int isProvidingWeakPower(IBlockAccess iBlockAccess, int x, int y, int z, int side)
	{
		return redstone;
	}

	public void updateTick(World world, int x, int y, int z, Random random)
	{
	}

	public int tickRate(World world)
	{
		return 1;
	}
	
	//Testing
	public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		update(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_);
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
