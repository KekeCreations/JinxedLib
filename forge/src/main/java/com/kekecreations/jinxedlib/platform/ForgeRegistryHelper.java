package com.kekecreations.jinxedlib.platform;

import com.kekecreations.jinxedlib.core.platform.services.IRegistryHelper;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.RegistryManager;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ForgeRegistryHelper implements IRegistryHelper {

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
                ForgeRegistry forgeReg = RegistryManager.ACTIVE.getRegistry(key.getSecond());
                if (forgeReg == null) return null;
                DeferredRegister<T> defReg = DeferredRegister.create(forgeReg, key.getFirst());
                defReg.register(FMLJavaModLoadingContext.get().getModEventBus());
                return defReg;
            });
            return reg != null ? reg.register(name, entry) : null;
        }

    }
}
