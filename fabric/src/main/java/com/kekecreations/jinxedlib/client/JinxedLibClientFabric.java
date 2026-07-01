package com.kekecreations.jinxedlib.client;

import com.kekecreations.jinxedlib.core.registry.JinxedSpriteSources;
import net.fabricmc.api.ClientModInitializer;

public class JinxedLibClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        JinxedSpriteSources.register();
    }
}
