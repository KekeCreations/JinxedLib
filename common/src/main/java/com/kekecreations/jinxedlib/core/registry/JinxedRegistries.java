package com.kekecreations.jinxedlib.core.registry;

import com.kekecreations.jinxedlib.JinxedLib;
import com.kekecreations.jinxedlib.common.data.Composting;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class JinxedRegistries {

    public static final ResourceKey<Registry<Composting>> COMPOSTING = ResourceKey.createRegistryKey(JinxedLib.modID("composting"));

    public static void register() {

    }
}
