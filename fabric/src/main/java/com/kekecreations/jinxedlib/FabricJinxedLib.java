package com.kekecreations.jinxedlib;

import net.fabricmc.api.ModInitializer;

public class FabricJinxedLib implements ModInitializer {
    
    @Override
    public void onInitialize() {
        JinxedLib.init();
    }
}
