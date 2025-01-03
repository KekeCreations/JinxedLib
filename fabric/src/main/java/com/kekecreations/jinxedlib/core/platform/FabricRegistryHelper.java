package com.kekecreations.jinxedlib.core.platform;

import com.kekecreations.jinxedlib.JinxedLib;
import com.kekecreations.jinxedlib.core.platform.services.IRegistryHelper;
import net.minecraft.core.Registry;

import java.util.function.Supplier;

public class FabricRegistryHelper implements IRegistryHelper {

    @Override
    public <T> Supplier<T> register(Registry<? super T> registry, String modID, String name, Supplier<T> entry) {
        T value = entry.get();
        Registry.register(registry, JinxedLib.customId(modID, name), value);
        return () -> value;
    }
}
