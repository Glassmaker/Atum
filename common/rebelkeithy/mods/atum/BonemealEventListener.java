package rebelkeithy.mods.atum;

import java.util.Random;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;
import rebelkeithy.mods.atum.blocks.BlockAtumSapling;
import rebelkeithy.mods.atum.blocks.BlockFlax;

public class BonemealEventListener
{
	@ForgeSubscribe
	public boolean onBonemeal(BonemealEvent event)
	{
		if(event.world.isRemote)
			return true;
		
		int id = event.world.getBlockId(event.X, event.Y, event.Z);
		if(id == Atum.atumPalmSapling.blockID)
		{
			((BlockAtumSapling)(Atum.atumPalmSapling)).growTree(event.world, event.X, event.Y, event.Z, new Random());
		}
		if(id == Atum.atumFlax.blockID)
		{
			((BlockFlax)(Atum.atumFlax)).fertilize(event.world, event.X, event.Y, event.Z);
		}
		return true;
	}
}
