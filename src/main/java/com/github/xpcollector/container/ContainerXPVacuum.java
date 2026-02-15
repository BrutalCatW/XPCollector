package com.github.xpcollector.container;

import net.minecraft.inventory.IInventory;

import com.github.xpcollector.tileentity.TileEntityXPVacuum;

import openmods.container.ContainerInventoryProvider;

public class ContainerXPVacuum extends ContainerInventoryProvider<TileEntityXPVacuum> {

    public ContainerXPVacuum(IInventory playerInventory, TileEntityXPVacuum hopper) {
        super(playerInventory, hopper);
        addInventoryGrid(44, 20, 5);
        addPlayerInventorySlots(69);
    }

}
