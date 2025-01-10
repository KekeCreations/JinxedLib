package com.kekecreations.jinxedlib;

import com.kekecreations.jinxedlib.core.data.CompostingManagerFabric;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;

public class JinxedLibFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        JinxedLib.init();

        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(ResourceLocation.parse("composting"), CompostingManagerFabric::new);

    }
}
