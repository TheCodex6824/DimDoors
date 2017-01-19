package com.zixiken.dimdoors.tileentities;

import com.zixiken.dimdoors.blocks.BlockDimDoor;
import com.zixiken.dimdoors.shared.Location;
import com.zixiken.dimdoors.shared.RiftRegistry;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class TileEntityDimDoor extends DDTileEntityBase {

    public boolean openOrClosed;
    public EnumFacing orientation = EnumFacing.SOUTH;
    public boolean hasExit;
    public byte lockStatus;
    public boolean isDungeonChainLink;
    public boolean hasGennedPair = false;

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        try {
            this.openOrClosed = nbt.getBoolean("openOrClosed");
            this.orientation = EnumFacing.getFront(nbt.getInteger("orientation"));
            this.hasExit = nbt.getBoolean("hasExit");
            this.isDungeonChainLink = nbt.getBoolean("isDungeonChainLink");
            this.hasGennedPair = nbt.getBoolean("hasGennedPair");
        } catch (Exception e) {
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
          super.writeToNBT(nbt);

        nbt.setBoolean("openOrClosed", this.openOrClosed);
        nbt.setBoolean("hasExit", this.hasExit);
        nbt.setInteger("orientation", this.orientation.getIndex());
        nbt.setBoolean("isDungeonChainLink", isDungeonChainLink);
        nbt.setBoolean("hasGennedPair", hasGennedPair);
        return nbt;
    }

    @Override
    public float[] getRenderColor(Random rand) {
        float[] rgbaColor = {1, 1, 1, 1};
        if (this.world.provider.getDimension() == -1) {
            rgbaColor[0] = rand.nextFloat() * 0.5F + 0.4F;
            rgbaColor[1] = rand.nextFloat() * 0.05F;
            rgbaColor[2] = rand.nextFloat() * 0.05F;
        } else {
            rgbaColor[0] = rand.nextFloat() * 0.5F + 0.1F;
            rgbaColor[1] = rand.nextFloat() * 0.4F + 0.4F;
            rgbaColor[2] = rand.nextFloat() * 0.6F + 0.5F;
        }

        return rgbaColor;
    }

    @Override
    public Location getTeleportTargetLocation() {
        EnumFacing facing = getWorld().getBlockState(getPos()).getValue(BlockDimDoor.FACING).getOpposite(); //@todo this will allways return South after world-load?

        return new Location(this.getWorld().provider.getDimension(), this.getPos().offset(facing));
    }

    @Override
    public boolean tryTeleport(Entity entity) {
        if (!isPaired()) {
            //@todo try to automatically pair this door somehow
        }
        return RiftRegistry.Instance.teleportEntityToRift(entity, getPairedRiftID());
    }
}
