package com.zixiken.dimdoors.client;

import com.zixiken.dimdoors.shared.DDProxyCommon;
import com.zixiken.dimdoors.shared.ModelManager;
import com.zixiken.dimdoors.shared.entities.MobMonolith;
import com.zixiken.dimdoors.shared.entities.RenderMobObelisk;
import com.zixiken.dimdoors.shared.tileentities.TileEntityDimDoor;
import com.zixiken.dimdoors.shared.tileentities.TileEntityRift;
import com.zixiken.dimdoors.shared.tileentities.TileEntityTransTrapdoor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@SuppressWarnings({"MethodCallSideOnly", "NewExpressionSideOnly"})
public class DDProxyClient extends DDProxyCommon {

    @Override
    public void onPreInitialization(FMLPreInitializationEvent event) {
        super.onPreInitialization(event);

        ModelManager.registerModelVariants();
        ModelManager.addCustomStateMappers();

        registerRenderers();
    }

    @Override
    public void onInitialization(FMLInitializationEvent event) {
        super.onInitialization(event);
        ModelManager.registerModels();
    }

    public void registerRenderers() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDimDoor.class, new RenderDimDoor());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTransTrapdoor.class, new RenderTransTrapdoor());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRift.class, new RenderRift());
        RenderingRegistry.registerEntityRenderingHandler(MobMonolith.class, new IRenderFactory<MobMonolith>()
        {
            @Override
            public Render<? super MobMonolith> createRenderFor(RenderManager manager) {
                return new RenderMobObelisk(manager, 0.5f);
            }
        });
    }

    @Override
    public boolean isClient() {
        return true;
    }

    @Override
    public EntityPlayer getLocalPlayer() {
        return Minecraft.getMinecraft().player;
    }

    @Override
    public World getDefWorld() {
        return getWorldServer(0); //gets the client world dim 0 handler
    }

    @Override
    public WorldServer getWorldServer(int dimId) {
        return Minecraft.getMinecraft().getIntegratedServer().getWorld(dimId);
    }
}
