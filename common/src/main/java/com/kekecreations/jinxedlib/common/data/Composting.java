package com.kekecreations.jinxedlib.common.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.world.item.Item;

import java.util.Map;

public record Composting(Map<Holder<Item>, Float> values) {

    public static final Codec<Composting> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.unboundedMap(RegistryFixedCodec.create(Registries.ITEM), Codec.floatRange(0, 1))
                    .fieldOf("values")
                    .forGetter(Composting::values)
    ).apply(instance, Composting::new));

    public Composting(Map<Holder<Item>, Float> values) {
        this.values = values;
    }
}
