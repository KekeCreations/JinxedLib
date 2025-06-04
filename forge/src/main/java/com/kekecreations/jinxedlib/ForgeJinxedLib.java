package com.kekecreations.jinxedlib;

import com.kekecreations.jinxedlib.common.data.FurnaceFuels;
import com.kekecreations.jinxedlib.core.registry.JinxedDatapackRegistries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DataPackRegistryEvent;

@Mod(JinxedLib.MOD_ID)
public class ForgeJinxedLib {
    
    public ForgeJinxedLib() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        JinxedLib.init();

        modEventBus.addListener(this::datapackRegistry);
    }

    public void datapackRegistry(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(JinxedDatapackRegistries.FURNACE_FUELS, FurnaceFuels.CODEC);
    }
}