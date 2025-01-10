package com.kekecreations.jinxedlib.core.registry;

import com.kekecreations.jinxedlib.JinxedLib;
import com.kekecreations.jinxedlib.common.data.Compostables;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class JinxedDatapackRegistries {

    public static final ResourceKey<Registry<Compostables>> COMPOSTABLES = ResourceKey.createRegistryKey(JinxedLib.modID("compostables"));

    //public static final ResourceKey<Registry<PaintbrushPalette>> PAINTBRUSH_PALETTE = ResourceKey.createRegistryKey(ArtsAndCrafts.id("paintbrush_palette"));

    public static void register() {

    }
}
