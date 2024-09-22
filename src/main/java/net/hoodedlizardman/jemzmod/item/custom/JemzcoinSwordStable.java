package net.hoodedlizardman.jemzmod.item.custom;

import net.hoodedlizardman.jemzmod.item.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Random;

public class JemzcoinSwordStable extends SwordItem {

    public JemzcoinSwordStable() {
        super(Tiers.DIAMOND, 10, -2.0f, new Properties().stacksTo(1).durability(2000).fireResistant());
    }


}