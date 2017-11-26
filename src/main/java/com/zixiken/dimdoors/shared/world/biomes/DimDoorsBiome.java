package com.zixiken.dimdoors.shared.world.biomes;

import net.minecraft.world.biome.Biome;

/**
 * Created by Jared Johnson on 1/24/2017.
 */
public class DimDoorsBiome extends Biome
{
    public DimDoorsBiome(String name) {
        super(new BiomeProperties(name));
        this.decorator.treesPerChunk = 0;
        this.decorator.flowersPerChunk = 0;
        this.decorator.grassPerChunk = 0;

        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
    }

    @Override
    public boolean canRain() {
        return false;
    }
}