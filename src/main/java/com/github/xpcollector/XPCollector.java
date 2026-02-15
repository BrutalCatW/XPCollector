package com.github.xpcollector;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.xpcollector.block.BlockXPVacuum;
import com.github.xpcollector.entity.EntityItemProjectile;
import com.github.xpcollector.tileentity.TileEntityXPVacuum;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import openmods.OpenMods;
import openmods.config.BlockInstances;
import openmods.config.game.ModStartupHelper;
import openmods.config.game.RegisterBlock;
import openmods.infobook.BookDocumentation;

@Mod(
        modid = XPCollector.MODID,
        name = XPCollector.NAME,
        version = Tags.VERSION,
        dependencies = "required-after:OpenMods@[0.10,)")
public class XPCollector {

    public static final String MODID = "xpcollector";
    public static final String NAME = "XPCollector";

    public static final Logger LOGGER = LogManager.getLogger(NAME);

    @Mod.Instance(MODID)
    public static XPCollector instance;

    @SidedProxy(
            clientSide = "com.github.xpcollector.client.ClientProxy",
            serverSide = "com.github.xpcollector.CommonProxy")
    public static CommonProxy proxy;

    private final ModStartupHelper startupHelper = new ModStartupHelper(MODID);

    // Blocks holder
    @BookDocumentation
    public static class Blocks implements BlockInstances {

        @RegisterBlock(name = "xpvacuum", tileEntity = TileEntityXPVacuum.class)
        public static BlockXPVacuum xpVacuum;
    }

    // Fluids
    public static class Fluids {

        public static final Fluid xpJuice = new Fluid("xpjuice").setLuminosity(10).setDensity(800).setViscosity(1500)
                .setUnlocalizedName(MODID + ".xpjuice");
    }

    // Creative tab
    public static final CreativeTabs creativeTab = new CreativeTabs(MODID) {

        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(Blocks.xpVacuum);
        }
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("XPCollector PreInit");

        // Register XP Juice fluid
        FluidRegistry.registerFluid(Fluids.xpJuice);

        // Register blocks using OpenMods system
        startupHelper.registerBlocksHolder(Blocks.class);

        // OpenMods preInit
        startupHelper.preInit(event.getSuggestedConfigurationFile());

        // Register GUI handler through OpenMods
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, OpenMods.proxy.wrapHandler(null));

        // Register EntityItemProjectile
        EntityRegistry.registerModEntity(EntityItemProjectile.class, "EntityItemProjectile", 1, instance, 64, 1, true);

        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        LOGGER.info("XPCollector Init");

        // Register recipes
        registerRecipes();

        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        LOGGER.info("XPCollector PostInit");

        proxy.postInit();
    }

    @SuppressWarnings("unchecked")
    private void registerRecipes() {
        List<IRecipe> recipeList = CraftingManager.getInstance().getRecipeList();

        // XP Vacuum recipe: Hopper + Obsidian + Ender Eye
        recipeList.add(
                new ShapelessOreRecipe(
                        Blocks.xpVacuum,
                        net.minecraft.init.Blocks.hopper,
                        net.minecraft.init.Blocks.obsidian,
                        Items.ender_eye));
    }
}
