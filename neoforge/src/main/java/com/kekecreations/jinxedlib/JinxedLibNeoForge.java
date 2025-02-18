package com.kekecreations.jinxedlib;


import com.kekecreations.jinxedlib.common.data.Compostables;
import com.kekecreations.jinxedlib.common.data.FurnaceFuels;
import com.kekecreations.jinxedlib.core.registry.JinxedDatapackRegistries;
import com.kekecreations.jinxedlib.core.registry.JinxedLootModifiers;
import com.kekecreations.jinxedlib.core.registry.JinxedSpriteSources;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

@Mod(JinxedLib.MOD_ID)
public class JinxedLibNeoForge {

    public JinxedLibNeoForge(IEventBus eventBus) {
        JinxedLib.init();

        if (FMLEnvironment.dist == Dist.CLIENT) {
            JinxedSpriteSources.register();
        }
        JinxedLootModifiers.register(eventBus);

        eventBus.addListener(this::datapackRegistry);
    }

    public void datapackRegistry(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(JinxedDatapackRegistries.COMPOSTABLES, Compostables.CODEC);
        event.dataPackRegistry(JinxedDatapackRegistries.FURNACE_FUELS, FurnaceFuels.CODEC);
    }
}