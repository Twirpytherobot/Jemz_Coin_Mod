package net.hoodedlizardman.jemzmod.item.custom;

import net.hoodedlizardman.jemzmod.item.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Random;

public class JemzcoinSwordUnstable extends SwordItem {

    public JemzcoinSwordUnstable() {
        super(Tiers.DIAMOND, 10, -2.0f, new Item.Properties().stacksTo(1).durability(2000));
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean result = super.hurtEnemy(stack, target, attacker);

        // Check if the attacker is a player and not dead
        if (attacker instanceof Player player && !player.isDeadOrDying()) {
            Random random = new Random();

            // 10% chance to strike the player with lightning
            if (random.nextFloat() < 0.10f) {
                Level world = player.level();

                if (!world.isClientSide() && world instanceof ServerLevel serverWorld) {
                    // Create and spawn a lightning bolt
                    LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(serverWorld);
                    if (lightning != null) {
                        lightning.moveTo(player.getX(), player.getY(), player.getZ()); // Set the position of the lightning
                        serverWorld.addFreshEntity(lightning); // Add lightning to the world
                        player.gameEvent(GameEvent.LIGHTNING_STRIKE);
                    }
                }
            }
        }
        return result;
    }
    @Override
    public void onDestroyed(ItemEntity itemEntity) {
        // Check if the item is in lava or fire
        if (itemEntity.isInLava() || itemEntity.isOnFire()) {
            Level level = itemEntity.getOwner().level();
            // Create a new stable item stack
            ItemStack stableItemStack = new ItemStack(ModItems.JEMZCOINSTABLESWORD.get(), 1);
            // Spawn the stable item in the world
            ItemEntity stableItemEntity = new ItemEntity(level, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), stableItemStack);
            level.addFreshEntity(stableItemEntity);
            // Remove the unstable item entity
            itemEntity.discard();
        }
        super.onDestroyed(itemEntity); // Call the super method if needed
    }
}