package com.kekecreations.jinxedlib;

import com.kekecreations.jinxedlib.common.data.FurnaceFuels;
import com.kekecreations.jinxedlib.core.registry.JinxedDatapackRegistries;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;

public class FabricJinxedLib implements ModInitializer {
    
    @Override
    public void onInitialize() {
        JinxedLib.init();

        DynamicRegistries.register(JinxedDatapackRegistries.FURNACE_FUELS, FurnaceFuels.CODEC);
    }
}
