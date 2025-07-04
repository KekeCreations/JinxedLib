package com.kekecreations.jinxedlib.core.platform;

import com.kekecreations.jinxedlib.core.platform.services.IRegistryHelper;
import net.minecraft.core.Registry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NeoForgeRegistryHelper implements IRegistryHelper {
    IEventBus modEventBus = ModLoadingContext.get().getActiveContainer().getEventBus();

    @Override
    public <T> Supplier<T> register(Registry<T> registry, String modID, String name, Supplier<T> entry) {
        DeferredRegister<T> deferredRegister = DeferredRegister.create(registry.key(), modID);
        deferredRegister.register(modEventBus);

        return deferredRegister.register(name, entry);
    }
}



