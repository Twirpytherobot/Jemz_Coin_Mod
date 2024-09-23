package net.hoodedlizardman.jemzmod.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ZmejcoinUnstableEntity extends ItemEntity {
    public ZmejcoinUnstableEntity(Level pLevel, double pPosX, double pPosY, double pPosZ, ItemStack pItemStack) {
        super(pLevel, pPosX, pPosY, pPosZ, pItemStack);
    }

    // Optionally, you could override additional constructors if needed, such as for deserialization
    public ZmejcoinUnstableEntity(EntityType<? extends ItemEntity> entityType, Level level) {
        super(entityType, level);
    }


    @Override
    public void tick() {
        super.tick();


    }
}
