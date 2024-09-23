package net.hoodedlizardman.jemzmod.entity;

import net.hoodedlizardman.jemzmod.JemzMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, JemzMod.MOD_ID);

    public static final RegistryObject<EntityType<ZmejcoinUnstableEntity>> ZMEJCOINUNSTABLEENTITY = ENTITY_TYPES.register("zmejcoinunstableentity",
            () -> EntityType.Builder.<ZmejcoinUnstableEntity>of(ZmejcoinUnstableEntity::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F) // Size of the entity
                    .build("zmejcoinunstableentity")
    );

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
