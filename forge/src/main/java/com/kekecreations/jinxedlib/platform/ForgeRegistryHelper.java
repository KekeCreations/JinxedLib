package com.kekecreations.jinxedlib.platform;

import com.kekecreations.jinxedlib.core.platform.services.IRegistryHelper;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.RegistryManager;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ForgeRegistryHelper implements IRegistryHelper {

    private static final RegistryMap registryMap = new RegistryMap();

    @Override
    public <T> Supplier<T> register(Registry<? super T> registry, String modID, String name, Supplier<T> entry) {
        return registryMap.register(registry, modID, name, entry);
    }


    private static class RegistryMap {

        private final Map<ResourceLocation, DeferredRegister<?>> registries = new HashMap<>();

        @SuppressWarnings({"unchecked", "rawtypes"})
        private <T> RegistryObject<T> register(Registry<? super T> registry, String modID, String name, Supplier<T> entry) {
            DeferredRegister<T> reg = (DeferredRegister<T>)registries.computeIfAbsent(registry.key().location(), (key) -> {
                ForgeRegistry forgeReg = RegistryManager.ACTIVE.getRegistry(key);

                if(forgeReg == null)
                    return null;

                DeferredRegister<T> defReg = DeferredRegister.create(forgeReg, modID);
                defReg.register(FMLJavaModLoadingContext.get().getModEventBus());

                return defReg;
            });

            return reg != null ? reg.register(name, entry) : null;
        }

    }
}
