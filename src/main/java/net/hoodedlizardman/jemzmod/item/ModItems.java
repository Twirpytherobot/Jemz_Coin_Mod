package net.hoodedlizardman.jemzmod.item;

import net.hoodedlizardman.jemzmod.JemzMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, JemzMod.MOD_ID);

    public static final RegistryObject<Item> JEMZCOIN = ITEMS.register("jemzcoin",() -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> JEMZCOINUNWRAPPED = ITEMS.register("jemzcoinunwrapped",() -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
