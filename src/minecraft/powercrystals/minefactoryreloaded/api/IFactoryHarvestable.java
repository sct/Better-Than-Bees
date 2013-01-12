package powercrystals.minefactoryreloaded.api;

import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author PowerCrystals
 *
 * Defines a harvestable block for the Harvester.
 */
public interface IFactoryHarvestable
{
	/**
	 * @return The block ID this harvestable instance is managing.
	 */
	public int getSourceId();
	/**
	 * @return The type of harvest the Harvester should perform on this block.
	 */
	public HarvestType getHarvestType();

	/**
	 * @param world The world this block is in.
	 * @param harvesterSettings The harvester's current settings. Do not modify these.
	 * @param x The X coordinate of the block being harvested.
	 * @param y The Y coordinate of the block being harvested.
	 * @param z The Z coordinate of the block being harvested.
	 * @return True if this block can be harvested.
	 */
	public boolean canBeHarvested(World world, Map<String, Boolean> harvesterSettings, int x, int y, int z);
	
	/**
	 * @param world The world this block is in.
	 * @param rand A Random instance to use when generating drops.
	 * @param harvesterSettings The harvester's current settings. Do not modify these.
	 * @param x The X coordinate of the block being harvested.
	 * @param y The Y coordinate of the block being harvested.
	 * @param z The Z coordinate of the block being harvested.
	 * @return The drops generated by breaking this block. For a default implementation, calling Block.getBlockDropped() is usually sufficient.
	 */
	public List<ItemStack> getDrops(World world, Random rand, Map<String, Boolean> harvesterSettings, int x, int y, int z);
	
	/**
	 * Called before the block is going to be harvested. Usually empty.
	 * @param world The world this block is in.
	 * @param x The X coordinate of the block being harvested.
	 * @param y The Y coordinate of the block being harvested.
	 * @param z The Z coordinate of the block being harvested.
	 */
	public void preHarvest(World world, int x, int y, int z);
	
	/**
	 * Called after the block is going to be harvested. Used to re-till soil, for example.
	 * @param world The world this block is in.
	 * @param x The X coordinate of the block being harvested.
	 * @param y The Y coordinate of the block being harvested.
	 * @param z The Z coordinate of the block being harvested.
	 */
	public void postHarvest(World world, int x, int y, int z);
}
