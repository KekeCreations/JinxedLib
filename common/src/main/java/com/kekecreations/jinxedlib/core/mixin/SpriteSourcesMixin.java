package com.kekecreations.jinxedlib.core.mixin;


import com.kekecreations.jinxedlib.client.renderer.atlas.JinxedPalettedPermutations;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.texture.atlas.SpriteSource;
import net.minecraft.client.renderer.texture.atlas.SpriteSources;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(SpriteSources.class)
public class SpriteSourcesMixin {

    @Shadow
    private static final ExtraCodecs.LateBoundIdMapper<Identifier, MapCodec<? extends SpriteSource>> ID_MAPPER = new ExtraCodecs.LateBoundIdMapper();

    @Inject(method = "bootstrap", at = @At(value = "HEAD"))
    public static void jinxedlib$Bootstrap() {
        ID_MAPPER.put(Identifier.withDefaultNamespace("jinxed_paletted_permutations"), JinxedPalettedPermutations.CODEC);

    }
}