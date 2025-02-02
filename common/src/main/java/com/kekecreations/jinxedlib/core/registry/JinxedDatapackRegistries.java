package com.kekecreations.jinxedlib.core.registry;

import com.kekecreations.jinxedlib.JinxedLib;
import com.kekecreations.jinxedlib.common.data.Compostables;
import com.kekecreations.jinxedlib.common.data.Waxables;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class JinxedDatapackRegistries {

    public static final ResourceKey<Registry<Compostables>> COMPOSTABLES = ResourceKey.createRegistryKey(JinxedLib.modID("compostables"));
    public static final ResourceKey<Registry<Waxables>> WAXABLES = ResourceKey.createRegistryKey(JinxedLib.modID("waxables"));

    public static void register() {

    }
}
