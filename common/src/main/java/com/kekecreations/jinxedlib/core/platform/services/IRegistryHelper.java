package com.kekecreations.jinxedlib.core.platform.services;

import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Supplier;

public interface IRegistryHelper {
    <T> Supplier<T> register(Registry<? super T> registry, String modID, String name, Supplier<T> entry);

    <T extends CreativeModeTab> Supplier<T> registerCreativeModeTab(String modID, String id, Supplier<T> tabSupplier);
}
