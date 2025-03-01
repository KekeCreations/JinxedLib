package com.kekecreations.jinxedlib.core.registry;

import com.kekecreations.jinxedlib.client.renderer.atlas.JinxedPalettedPermutations;
import com.kekecreations.jinxedlib.core.util.JinxedRegistryHelper;
import net.minecraft.client.renderer.texture.atlas.SpriteSourceType;

public class JinxedSpriteSources {

    public static SpriteSourceType PALETTED_PERMUTATIONS = JinxedRegistryHelper.registerSpriteSource("jinxedlib_paletted_permutations", JinxedPalettedPermutations.CODEC);


    public static void register() {}
}
