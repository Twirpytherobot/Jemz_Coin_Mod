package net.hoodedlizardman.jemzmod.events;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.hoodedlizardman.jemzmod.JemzMod;
import net.hoodedlizardman.jemzmod.item.ModItems;

import net.hoodedlizardman.jemzmod.item.custom.JemzcoinItem;
import net.hoodedlizardman.jemzmod.item.custom.JemzcoinUnstableItem;
import net.hoodedlizardman.jemzmod.item.custom.ZmejcoinUnstableItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = JemzMod.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event){
        //level 1 farmer jemzcoin
        if(event.getType() == VillagerProfession.FARMER ){
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            trades.get(1).add((pTrader,pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,3),
                    new ItemStack(ModItems.JEMZCOIN.get(),1),
                    10, 8,0.2f)
                    );
        }
        if(event.getType() == VillagerProfession.FARMER ) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.JEMZCOIN.get(), 10),
                    new ItemStack(ModItems.ZMEJCOIN.get(), 1),
                    10, 8, 0.2f)
            );
        }
    }
    @SubscribeEvent
    public static void addCustomWanderingTrades(WandererTradesEvent event){
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

        genericTrades.add((pTrader,pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD,5),
                new ItemStack(ModItems.JEMZCOIN.get(),1),
                5,8,0.2f
        ));
    }
    @SubscribeEvent
    public static void onExplosion(ExplosionEvent.Detonate event) {
        // Iterate through all affected entities
        for (var entity : event.getAffectedEntities()) {
            if (entity instanceof ItemEntity itemEntity) {
                ItemStack stack = itemEntity.getItem();

                // Check if the item is a Jemzcoin
                if (stack.getItem() instanceof JemzcoinItem) {
                    int count = stack.getCount(); // Get the count of Jemzcoins

                    // Remove the Jemzcoin item entity
                    itemEntity.remove(Entity.RemovalReason.DISCARDED);

                    // Spawn Unstable Jemzcoins in place of the Jemzcoins
                    for (int i = 0; i < count; i++) {
                        ItemStack unstableStack = new ItemStack(ModItems.JEMZCOINUNSTABLE.get(), 1);
                        itemEntity.spawnAtLocation(unstableStack);
                    }

                    // Optionally, add effects or sounds here
                    // event.getWorld().playSound(null, event.getExplosion().getPosition(), ModSounds.UNSTABLE_JEMZCOIN_SOUND, SoundSource.PLAYERS, 1.0F, 1.0F);
                }
            }
        }
    }

    @SubscribeEvent()
    public void onItemToss(ItemTossEvent event) {
        ItemEntity itemEntity = event.getEntity();
        ItemStack itemStack = itemEntity.getItem();

        // Check if the item is the Unstable Zmejcoin
        if (itemStack.getItem() instanceof ZmejcoinUnstableItem) {
            // Check if the item is in water
            BlockPos itemPos = new BlockPos((int)itemEntity.getX(), (int)itemEntity.getY(), (int)itemEntity.getZ());
            if (itemEntity.level().getBlockState(itemPos).getBlock() == Blocks.WATER) {
                // Convert to ZmejcoinStable
                itemEntity.setItem(new ItemStack(ModItems.ZMEJCOINSTABLE.get(), 1));
                System.out.println("it kinda worked!");
            }
        }
    }

}
