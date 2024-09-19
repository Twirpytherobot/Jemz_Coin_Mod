package net.hoodedlizardman.jemzmod.item.custom;

import net.hoodedlizardman.jemzmod.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class JemzcoinItem extends Item {
    public JemzcoinItem(Properties properties){
        super(properties);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand){
        ItemStack itemStack = player.getItemInHand(hand);

        if(!level.isClientSide){
            itemStack.shrink(1);
            ItemStack unwrappedItemStack = new ItemStack(ModItems.JEMZCOINUNWRAPPED.get());
            ItemStack wrapperItemStack = new ItemStack(ModItems.JEMZCOINWRAPPER.get());
            if(!player.getInventory().add(unwrappedItemStack)){
                player.drop(unwrappedItemStack,false);
            }
            if(!player.getInventory().add(unwrappedItemStack)){
                player.drop(wrapperItemStack,false);
            }
            level.playSound(null,player.getX(),player.getY(),player.getZ(),
                    SoundEvents.AZALEA_HIT,
                    SoundSource.PLAYERS,1.0f,1.0f);
        }
        return InteractionResultHolder.sidedSuccess(itemStack,level.isClientSide());
    }
}

