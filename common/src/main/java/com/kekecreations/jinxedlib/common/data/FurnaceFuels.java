package com.kekecreations.jinxedlib.common.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.world.item.Item;

import java.util.Map;

public record FurnaceFuels(Map<Holder<Item>, Integer> values) {

    public static final Codec<FurnaceFuels> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.unboundedMap(RegistryFixedCodec.create(Registries.ITEM), Codec.INT)
                    .fieldOf("values")
                    .forGetter(FurnaceFuels::values)
    ).apply(instance, FurnaceFuels::new));

    public FurnaceFuels(Map<Holder<Item>, Integer> values) {
        this.values = values;
    }
}
