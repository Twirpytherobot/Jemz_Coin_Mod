package net.hoodedlizardman.jemzmod.events;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.hoodedlizardman.jemzmod.JemzMod;
import net.hoodedlizardman.jemzmod.item.ModItems;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
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
}
