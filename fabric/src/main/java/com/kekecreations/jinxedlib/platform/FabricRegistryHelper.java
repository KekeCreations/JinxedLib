package com.kekecreations.jinxedlib.platform;

import com.kekecreations.jinxedlib.JinxedLib;
import com.kekecreations.jinxedlib.core.platform.services.IRegistryHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Supplier;

public class FabricRegistryHelper implements IRegistryHelper {

    @Override
    public <T> Supplier<T> register(Registry<? super T> registry, String modID, String name, Supplier<T> entry) {
        T value = entry.get();
        Registry.register(registry, JinxedLib.customId(modID, name), value);
        return () -> value;
    }

    @Override
    public <T extends CreativeModeTab> Supplier<T> registerCreativeModeTab(String modID, String id, Supplier<T> tabSupplier) {
        var creativeModeTab = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(modID, id), tabSupplier.get());
        return () -> creativeModeTab;
    }
}
