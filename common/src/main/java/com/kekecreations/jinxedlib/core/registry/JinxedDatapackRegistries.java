package com.kekecreations.jinxedlib.core.registry;

import com.kekecreations.jinxedlib.JinxedLib;
import com.kekecreations.jinxedlib.common.data.Compostables;
import com.kekecreations.jinxedlib.common.data.FurnaceFuels;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class JinxedDatapackRegistries {

    public static final ResourceKey<Registry<Compostables>> COMPOSTABLES = ResourceKey.createRegistryKey(JinxedLib.modID("compostables"));

    public static final ResourceKey<Registry<FurnaceFuels>> FURNACE_FUELS = ResourceKey.createRegistryKey(JinxedLib.modID("furnace_fuels"));

    public static void register() {

    }
}
