package com.feed_the_beast.ftbquests.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author LatvianModder
 */
public class BlockScreenPart extends BlockScreen
{
	public BlockScreenPart(String mod, String id, boolean f)
	{
		super(mod, id, f);
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		return currentTask == null ? new TileScreenPart() : currentTask.createScreenPart(world);
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos)
	{
		return false;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		if (!BREAKING_SCREEN)
		{
			TileEntity tileEntity = world.getTileEntity(pos);

			if (tileEntity instanceof TileScreenPart)
			{
				TileScreenCore screen = ((TileScreenPart) tileEntity).getScreen();

				if (screen != null)
				{
					world.setBlockToAir(screen.getPos());
				}
			}
		}

		super.breakBlock(world, pos, state);
	}
}