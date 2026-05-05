package com.kekecreations.jinxedlib;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(JinxedLib.MOD_ID)
public class JinxedLibNeoForge {

    public JinxedLibNeoForge(IEventBus eventBus) {
        JinxedLib.init();

    }
}