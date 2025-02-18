package com.kekecreations.jinxedlib.core.mixin;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.texture.atlas.SpriteSource;
import net.minecraft.client.renderer.texture.atlas.SpriteSourceType;
import net.minecraft.client.renderer.texture.atlas.SpriteSources;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SpriteSources.class)
public interface SpriteSourcesInvoker {


    @Invoker
    static SpriteSourceType invokeRegister(String string, MapCodec<? extends SpriteSource> spriteSource) {
        throw new AssertionError();
    }
}
