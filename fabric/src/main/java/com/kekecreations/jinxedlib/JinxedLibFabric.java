package com.kekecreations.jinxedlib;

import com.kekecreations.jinxedlib.common.data.Compostables;
import com.kekecreations.jinxedlib.common.data.Waxables;
import com.kekecreations.jinxedlib.core.registry.JinxedDatapackRegistries;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;

public class JinxedLibFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        JinxedLib.init();

        DynamicRegistries.register(JinxedDatapackRegistries.COMPOSTABLES, Compostables.CODEC);
        DynamicRegistries.register(JinxedDatapackRegistries.WAXABLES, Waxables.CODEC);
    }
}
