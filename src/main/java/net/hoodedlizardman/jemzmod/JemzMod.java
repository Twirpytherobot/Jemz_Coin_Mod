package net.hoodedlizardman.jemzmod;

import com.mojang.logging.LogUtils;
import net.hoodedlizardman.jemzmod.block.ModBlocks;
import net.hoodedlizardman.jemzmod.entity.ModEntities;
import net.hoodedlizardman.jemzmod.events.UnstableJemzCoinEffect;
import net.hoodedlizardman.jemzmod.events.UnstableZmejCoinEffect;
import net.hoodedlizardman.jemzmod.item.ModCreativeModeTabs;
import net.hoodedlizardman.jemzmod.item.ModItems;
import net.hoodedlizardman.jemzmod.loot.ModLootModifiers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(JemzMod.MOD_ID)
public class JemzMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "jemzmod";
    public static final Logger LOGGER = LogUtils.getLogger();


    public JemzMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntities.register(modEventBus);
        ModLootModifiers.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new UnstableJemzCoinEffect());
        MinecraftForge.EVENT_BUS.register(new UnstableZmejCoinEffect());



    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }


    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

        }
    }
}
