package com.kekecreations.jinxedlib.core.registry;

import com.kekecreations.jinxedlib.client.renderer.atlas.JinxedPalettedPermutations;
import com.kekecreations.jinxedlib.core.util.JinxedRegistryHelper;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.texture.atlas.SpriteSource;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;

public class JinxedSpriteSources {

    public static ExtraCodecs.LateBoundIdMapper<Identifier, MapCodec<? extends SpriteSource>> PALETTED_PERMUTATIONS = JinxedRegistryHelper.registerSpriteSource(Identifier.fromNamespaceAndPath("jinxedlib", "paletted_permutations"), JinxedPalettedPermutations.CODEC);


    public static void register() {}
}
