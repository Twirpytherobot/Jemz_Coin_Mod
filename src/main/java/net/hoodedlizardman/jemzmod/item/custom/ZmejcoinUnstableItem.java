package net.hoodedlizardman.jemzmod.item.custom;

import net.hoodedlizardman.jemzmod.entity.ZmejcoinUnstableEntity;
import net.hoodedlizardman.jemzmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.util.Vector;

@Mod.EventBusSubscriber
public class ZmejcoinUnstableItem extends Item {
    private static final int TRANSFORM_DELAY = 60;

    public ZmejcoinUnstableItem(Properties properties) {
        super(properties);
    }

    // Check for conversion on tick

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        // Check if the item entity is in water
        Level level = entity.level();
        if (entity.isInWater()) {
            level.addParticle(ParticleTypes.SPLASH, entity.getX(), entity.getY() + 0.2, entity.getZ(), 0, 0.1, 0);
            // Use a custom timer (age of entity) to delay the transformation
            if (entity.getAge() > TRANSFORM_DELAY) {
                // After the delay, transform the unstable Zmejcoin into the stable version
                entity.setItem(new ItemStack(ModItems.ZMEJCOINSTABLE.get()));
                return true;  // Custom handling done, stop further processing
            }

        }

        return super.onEntityItemUpdate(stack, entity);  // Return false for default behavior
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {
        // Ensure Minecraft knows this item has a custom entity
        return true;
    }
}

