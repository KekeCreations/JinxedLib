package com.kekecreations.jinxedlib.common.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.world.item.Item;

import java.util.Map;

public record Compostables(Map<Holder<Item>, Float> values, boolean canVillagerCompost) {

    public static final Codec<Compostables> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.unboundedMap(RegistryFixedCodec.create(Registries.ITEM), Codec.floatRange(0, 1))
                    .fieldOf("values")
                    .forGetter(Compostables::values),
            Codec.BOOL.fieldOf("can_villager_compost").forGetter(Compostables::canVillagerCompost)
    ).apply(instance, Compostables::new));

    public Compostables(Map<Holder<Item>, Float> values, boolean canVillagerCompost) {
        this.values = values;
        this.canVillagerCompost = canVillagerCompost;
    }
}
