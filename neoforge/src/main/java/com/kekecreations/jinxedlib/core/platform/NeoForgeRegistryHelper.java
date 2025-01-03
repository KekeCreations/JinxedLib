package com.kekecreations.jinxedlib.core.platform;

import com.kekecreations.jinxedlib.core.platform.services.IRegistryHelper;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class NeoForgeRegistryHelper implements IRegistryHelper {
    private static final RegistryMap registryMap = new RegistryMap();

    @Override
    public <T> Supplier<T> register(Registry<? super T> registry, String modID, String name, Supplier<T> entry) {
        return registryMap.register(modID, registry, name, entry);
    }


    private static class RegistryMap {

        private final Map<Pair<String, ResourceLocation>, DeferredRegister<?>> registries = new HashMap<>();

        @SuppressWarnings({"unchecked", "rawtypes"})
        private <T> Supplier<T> register(String modID, Registry<? super T> registry, String name, Supplier<T> entry) {
            DeferredRegister<T> reg = (DeferredRegister<T>)registries.computeIfAbsent(Pair.of(modID, registry.key().location()), (key) -> {
                Registry forgeReg = BuiltInRegistries.REGISTRY.get(key.getSecond());
                if (forgeReg == null) return null;
                DeferredRegister<T> defReg = DeferredRegister.create(forgeReg, key.getFirst());
                defReg.register(ModLoadingContext.get().getActiveContainer().getEventBus());
                return defReg;
            });
            return reg != null ? reg.register(name, entry) : null;
        }
    }
}



