package net.hoodedlizardman.jemzmod.datagen;

import net.hoodedlizardman.jemzmod.JemzMod;
import net.hoodedlizardman.jemzmod.block.ModBlocks;
import net.hoodedlizardman.jemzmod.block.custom.JemzcoinPlantBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, JemzMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.JEMZCOIN_BLOCK);

        makeStrawberryCrop((CropBlock) ModBlocks.JEMZCOINPLANT_BLOCK.get(), "jemzcoinplant_block_stage", "jemzcoinplant_block_stage");
    }


    public void makeStrawberryCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> strawberryStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] strawberryStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((JemzcoinPlantBlock) block).getAgeProperty()),
                new ResourceLocation(JemzMod.MOD_ID, "block/" + textureName + state.getValue(((JemzcoinPlantBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
