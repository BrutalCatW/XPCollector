package com.github.xpcollector.client;

import com.github.xpcollector.CommonProxy;
import com.github.xpcollector.client.renderer.TileEntityXPVacuumRenderer;
import com.github.xpcollector.tileentity.TileEntityXPVacuum;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit() {
        super.preInit();

        // Register TileEntity renderer
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityXPVacuum.class, new TileEntityXPVacuumRenderer());
    }
}
