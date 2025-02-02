package com.kekecreations.jinxedlib.common.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.world.level.block.Block;

import java.util.Map;

public record Waxables(Map<Holder<Block>, Holder<Block>> values) {
    public static final Codec<Waxables> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.unboundedMap(RegistryFixedCodec.create(Registries.BLOCK), RegistryFixedCodec.create(Registries.BLOCK))
            .fieldOf("values").forGetter(Waxables::values)
    ).apply(instance, Waxables::new));

    public Waxables(Map<Holder<Block>, Holder<Block>> values) {
        this.values = values;
    }
}
