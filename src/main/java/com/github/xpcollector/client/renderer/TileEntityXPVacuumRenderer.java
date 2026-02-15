package com.github.xpcollector.client.renderer;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.github.xpcollector.client.model.ModelXPVacuum;
import com.github.xpcollector.tileentity.TileEntityXPVacuum;

public class TileEntityXPVacuumRenderer extends TileEntitySpecialRenderer {

    private ModelXPVacuum model = new ModelXPVacuum();

    private static final ResourceLocation texture = new ResourceLocation("xpcollector", "textures/models/xpvacuum.png");

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        bindTexture(texture);
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1f, (float) z + 0.5F);
        GL11.glRotatef(180, 1, 0, 0);
        TileEntityXPVacuum hopper = (TileEntityXPVacuum) tileentity;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        model.render(hopper, f);
        GL11.glPopMatrix();
    }

}
