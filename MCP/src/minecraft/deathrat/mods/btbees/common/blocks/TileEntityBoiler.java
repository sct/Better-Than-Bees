package deathrat.mods.btbees.common.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBoiler extends TileEntity implements IInventory
{
	private ItemStack[] inv;
	private int fireLevel;
	private int waterLevel;
	private int energyLevel;
	private int waterLevelMax = 100;
	private int fireLevelMax = 100;
	private int energyLevelMax = 100;

	public TileEntityBoiler()
	{
		inv = new ItemStack[30];
		fireLevel = 0;
		waterLevel = 0;
		energyLevel = 0;
	}

	@Override
	public int getSizeInventory()
	{
		return inv.length;
	}


	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return inv[slot];
	}


	@Override
	public ItemStack decrStackSize(int slot, int amt)
	{
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
                if (stack.stackSize <= amt) {
                        setInventorySlotContents(slot, null);
                } else {
                        stack = stack.splitStack(amt);
                        if (stack.stackSize == 0) {
                                setInventorySlotContents(slot, null);
                        }
                }
        }
        return stack;
	}


	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		ItemStack stack = getStackInSlot(slot);
		if(stack != null)
		{
			setInventorySlotContents(slot, null);
		}
		return stack;
	}


	@Override
	public void setInventorySlotContents(int slot, ItemStack stack)
	{
		inv[slot] = stack;
		if(stack != null && stack.stackSize > getInventoryStackLimit())
		{
			stack.stackSize = getInventoryStackLimit();
		}
	}


	@Override
	public String getInvName()
	{
		return "deathrat.tileentityboiler";
	}


	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}


	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}


	@Override
	public void openChest()
	{

	}


	@Override
	public void closeChest()
	{

	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);

		NBTTagList itemList = new NBTTagList();
		for(int i = 0; i < inv.length; i++)
		{
			ItemStack stack = inv[i];
			if(stack != null)
			{
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}

		tagCompound.setTag("Inventory", itemList);
		tagCompound.setInteger("energyLevel", energyLevel);
		tagCompound.setInteger("fireLevel", fireLevel);
		tagCompound.setInteger("waterLevel", waterLevel);
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);

        NBTTagList tagList = tagCompound.getTagList("Inventory");
        for (int i = 0; i < tagList.tagCount(); i++) {
                NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
                byte slot = tag.getByte("Slot");
                if (slot >= 0 && slot < inv.length) {
                        inv[slot] = ItemStack.loadItemStackFromNBT(tag);
                }
        }

		energyLevel = tagCompound.getInteger("energyLevel");
		fireLevel = tagCompound.getInteger("fireLevel");
		waterLevel = tagCompound.getInteger("waterLevel");
	}
}
