package com.kekecreations.jinxedlib;


import com.kekecreations.jinxedlib.core.registry.JinxedSpriteSources;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(JinxedLib.MOD_ID)
public class JinxedLibNeoForge {

    public JinxedLibNeoForge(IEventBus eventBus) {
        JinxedLib.init();

        if (FMLEnvironment.dist == Dist.CLIENT) {
            JinxedSpriteSources.register();
        }

    }
}