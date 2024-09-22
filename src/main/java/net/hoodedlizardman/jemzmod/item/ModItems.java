package net.hoodedlizardman.jemzmod.item;

import net.hoodedlizardman.jemzmod.JemzMod;
import net.hoodedlizardman.jemzmod.block.ModBlocks;
import net.hoodedlizardman.jemzmod.block.custom.JemzCoinVendingMachineBlock;
import net.hoodedlizardman.jemzmod.item.custom.JemzcoinItem;
import net.hoodedlizardman.jemzmod.item.custom.JemzcoinUnstableItem;
import net.minecraft.util.datafix.fixes.ItemStackEnchantmentNamesFix;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, JemzMod.MOD_ID);

    public static final FoodProperties JEMZCOIN_UNWRAPPED_FOOD = new FoodProperties.Builder()
            .nutrition(4)
            .saturationMod(0.3f)
            .alwaysEat()
            .build();


    public static final RegistryObject<Item> JEMZCOIN = ITEMS.register("jemzcoin",() -> new JemzcoinItem(new Item.Properties()));

    public static final RegistryObject<Item> ZMEJCOIN = ITEMS.register("zmejcoin",() -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> JEMZCOINWRAPPER = ITEMS.register("jemzcoinwrapper",() -> new ItemNameBlockItem(ModBlocks.JEMZCOINPLANT_BLOCK.get(),new Item.Properties()));

    public static final RegistryObject<Item> JEMZCOINUNWRAPPED = ITEMS.register("jemzcoinunwrapped",() -> new Item(new Item.Properties().food(JEMZCOIN_UNWRAPPED_FOOD)));

    public static final RegistryObject<Item> JEMZCOINUNSTABLE = ITEMS.register("jemzcoinunstable",() -> new JemzcoinUnstableItem(new Item.Properties()));

    public static final RegistryObject<Item> JEMZCOINSTABLE = ITEMS.register("jemzcoinstable",() -> new Item(new Item.Properties().fireResistant()));
    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
