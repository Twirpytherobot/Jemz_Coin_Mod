package net.hoodedlizardman.jemzmod.item.custom;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;

public class ZmejcoinSword extends SwordItem {

    public ZmejcoinSword() {
        super(Tiers.DIAMOND, 10, -2.0f, new Properties().stacksTo(1).durability(2000).fireResistant());
    }


}