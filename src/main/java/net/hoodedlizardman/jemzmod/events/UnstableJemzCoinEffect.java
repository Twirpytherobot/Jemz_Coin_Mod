package net.hoodedlizardman.jemzmod.events;

import net.hoodedlizardman.jemzmod.item.ModItems;
import net.hoodedlizardman.jemzmod.item.custom.JemzcoinItem;
import net.hoodedlizardman.jemzmod.item.custom.JemzcoinUnstableItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;

public class UnstableJemzCoinEffect {
    private final Map<Player, Integer> tickCounters = new HashMap<>(); // Tracks tick count for each player

    // This event will run every tick (20 times per second)
    @SubscribeEvent
    public void onWorldTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Level level = player.level();

        // Initialize the tick counter for new players
        tickCounters.putIfAbsent(player, 0);
        int ticks = tickCounters.get(player) + 1; // Increment tick counter
        tickCounters.put(player, ticks);

        // Check if enough ticks have passed (e.g., 200 ticks for 10 seconds)
        if (ticks >= 200) { // 10 seconds
            tickCounters.put(player, 0); // Reset tick counter
            convertSingleJemzCoin(player);
        }
    }

    // Helper method to check if the player has an Unstable Jemz Coin
    private boolean hasUnstableJemzCoin(Player player) {
        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() instanceof JemzcoinUnstableItem) {
                return true; // Found an Unstable Jemz Coin
            }
        }
        return false;
    }

    // Method to convert one JemzCoin to Zmejcoin in a player's inventory
    private void convertSingleJemzCoin(Player player) {
        if (hasUnstableJemzCoin(player)) {
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                ItemStack stack = player.getInventory().getItem(i);
                if (stack.getItem() instanceof JemzcoinItem && stack.getCount() > 0) {
                    stack.shrink(1); // Remove one JemzCoin
                    player.getInventory().add(new ItemStack(ModItems.ZMEJCOIN.get(), 1)); // Add one Zmejcoin
                    System.out.println("Converted 1 Jemzcoin to Zmejcoin. New Jemzcoin Count: " + stack.getCount());
                    break; // Stop after converting one coin
                }
            }
        }
    }
}
