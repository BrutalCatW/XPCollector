package com.github.xpcollector.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.github.xpcollector.XPCollector;
import com.github.xpcollector.tileentity.TileEntityXPVacuum;

import openmods.block.OpenBlock;

public class BlockXPVacuum extends OpenBlock {

    public BlockXPVacuum() {
        super(Material.rock);
        setCreativeTab(XPCollector.creativeTab);
        setBlockName("xpcollector.xpvacuum");
        setBlockTextureName("xpcollector:xpvacuum");
        setRenderMode(RenderMode.TESR_ONLY);
        setBlockBounds(0.25f, 0.25f, 0.25f, 0.75f, 0.75f, 0.75f);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return AxisAlignedBB.getBoundingBox(x + 0.01, y + 0.01, z + 0.01, x + 0.99, y + 0.99, z + 0.99);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        TileEntityXPVacuum te = getTileEntity(world, x, y, z, TileEntityXPVacuum.class);
        if (te != null) {
            te.onEntityCollidedWithBlock(entity);
        }
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, ForgeDirection side) {
        return true;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    protected Object getModInstance() {
        return XPCollector.instance;
    }
}
