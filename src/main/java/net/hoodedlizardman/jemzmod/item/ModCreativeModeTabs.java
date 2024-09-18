package net.hoodedlizardman.jemzmod.item;

import net.hoodedlizardman.jemzmod.JemzMod;
import net.hoodedlizardman.jemzmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, JemzMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> JEMZ_TAB = CREATIVE_MODE_TABS.register("jemz_tab",() ->  CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.JEMZCOIN.get()))
            .title(Component.translatable("creativetab.jemz_tab"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModItems.JEMZCOIN.get());
                output.accept(ModItems.JEMZCOINUNWRAPPED.get());
                output.accept(ModBlocks.JEMZCOIN_BLOCK.get());
            })
            .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
