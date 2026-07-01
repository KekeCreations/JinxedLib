package com.kekecreations.jinxedlib.core.mixin;


import net.minecraft.client.renderer.texture.atlas.SpriteSources;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(SpriteSources.class)
public class SpriteSourcesMixin {

    @Inject(method = "bootstrap", at = @At(value = "TAIL"))
    public static void bootstrap() {

    }

}