package net.hoodedlizardman.jemzmod.block.custom;

import net.hoodedlizardman.jemzmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class JemzCoinVendingMachineBlock extends HorizontalDirectionalBlock{

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public JemzCoinVendingMachineBlock(Properties pProperties) {
        super(pProperties);
        registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));

    }
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext){
        return defaultBlockState().setValue(FACING,pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public RenderShape getRenderShape(BlockState pState){
        return RenderShape.MODEL;
    }



    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation){
        return pState.setValue(FACING,pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror){
        return pState.rotate(pMirror.getRotation((pState.getValue(FACING))));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block,BlockState> pBuilder){
        super.createBlockStateDefinition((pBuilder));
        pBuilder.add(FACING);
    }
    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit){

        ItemStack heldItem = pPlayer.getItemInHand(pHand);

        if(!pLevel.isClientSide && heldItem.getItem() == ModItems.JEMZCOIN.get()){
            heldItem.shrink(1);
            pLevel.playSound(pPlayer,pPos, SoundEvents.NOTE_BLOCK_CHIME.get(), SoundSource.BLOCKS,1f, 1f);
            Item randomItem = getRandomItem(pLevel.getRandom());
            ItemStack randomItemStack = new ItemStack(randomItem);

            if(!pPlayer.addItem(randomItemStack)){
                pPlayer.drop(randomItemStack,false);
            }

            return InteractionResult.SUCCESS;

        }
        return InteractionResult.PASS;
    }

    private Item getRandomItem(RandomSource random) {
        Item[] items = new Item[]{Items.DIAMOND, Items.APPLE, Items.IRON_INGOT};
        return items[random.nextInt(items.length)];
    }
}
