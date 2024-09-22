package net.hoodedlizardman.jemzmod.item.custom;

import net.hoodedlizardman.jemzmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class JemzcoinUnstableItem extends Item {
    public JemzcoinUnstableItem(Properties properties){
        super(properties);
    }
    // Check for conversion on tick
    @Override
    public void onDestroyed(ItemEntity itemEntity) {
        // Check if the item is in lava or fire
        if (itemEntity.isInLava() || itemEntity.isOnFire()) {
            Level level = itemEntity.getOwner().level();
            // Create a new stable item stack
            ItemStack stableItemStack = new ItemStack(ModItems.JEMZCOINSTABLE.get(), 1);
            // Spawn the stable item in the world
            ItemEntity stableItemEntity = new ItemEntity(level, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), stableItemStack);
            level.addFreshEntity(stableItemEntity);
            // Remove the unstable item entity
            itemEntity.discard();
        }
        super.onDestroyed(itemEntity); // Call the super method if needed
    }
}


