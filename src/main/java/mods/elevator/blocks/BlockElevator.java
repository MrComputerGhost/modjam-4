package mods.elevator.blocks;

import mods.elevator.tileentitys.TileEntityElevator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockElevator extends BlockContainer
{

	public BlockElevator(Material material)
	{
		super(material);
		setBlockName("block.elevator").setBlockTextureName("elevator:elevator");
	}

	public TileEntity createNewTileEntity(World world, int arg1)
	{
		return new TileEntityElevator();
	}
	
	public void breakBlock(World world, int i, int j, int k, int par5, int par6) {
		TileEntityElevator te = (TileEntityElevator) world.getBlockTileEntity(i, j, k);
		if (te != null) {
			world.markTileEntityForDespawn(te);
		}
		super.breakBlock(world, i, j, k, par5, par6);
	}


}
