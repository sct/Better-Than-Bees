package deathrat.mods.btbees.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;

import com.google.common.io.ByteArrayDataInput;

import cpw.mods.fml.common.network.Player;

public class TileEntityRicePlant extends TileEntity
{
	public TileEntityRicePlant()
	{
	}

	@Override
	public void updateEntity()
	{
		if(this.getWorldObj().rand.nextInt(300) == 0)
		{
			BlockRicePlant block = (BlockRicePlant)this.getBlockType();
			block.updatePlant(this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord, this.getWorldObj().rand);
		}
		if(this.getWorldObj().getBlockMaterial(this.xCoord, this.yCoord-1, this.zCoord) != Material.water)
		{
			this.getWorldObj().removeBlockTileEntity(this.xCoord, this.yCoord, this.zCoord);
		}
	}



	public void handlePacketData(INetworkManager manager, Packet250CustomPayload packet, Player player, ByteArrayDataInput data, int meta)
	{
		try
		{
			this.getWorldObj().setBlockMetadataWithNotify(xCoord, yCoord, zCoord, meta);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound)
	{
		super.writeToNBT(par1nbtTagCompound);
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound)
	{
		super.readFromNBT(par1nbtTagCompound);
	}

}
