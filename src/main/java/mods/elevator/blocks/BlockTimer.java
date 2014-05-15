package mods.elevator.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTimer extends Block
{

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
	}

	public int isProvidingStrongPower(IBlockAccess iBlockAccess, int x, int y, int z, int side)
	{
		return 0;
	}

	public void updateTick(World world, int x, int y, int z, Random random)
	{
		System.out.println("Test");
	}

	public void onBlockAdded(World world, int x, int y, int z)
	{
		update(world, x, y, z);
	}

	public boolean canProvidePower()
	{
		return true;
	}


}
