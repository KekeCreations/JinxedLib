package com.kekecreations.jinxedlib;


import com.kekecreations.jinxedlib.core.event.JsonReloadEvents;
import com.kekecreations.jinxedlib.core.registry.JinxedSpriteSources;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;

@Mod(JinxedLib.MOD_ID)
public class JinxedLibNeoForge {

    public JinxedLibNeoForge(IEventBus eventBus) {
        JinxedLib.init();

        if (FMLEnvironment.dist == Dist.CLIENT) {
            JinxedSpriteSources.register();
        }
        NeoForge.EVENT_BUS.register(new JsonReloadEvents());
    }
}