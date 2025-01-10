package com.kekecreations.jinxedlib.common.data;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.kekecreations.jinxedlib.core.registry.JinxedRegistries;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class CompostingManager extends SimpleJsonResourceReloadListener {
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    private static final Logger LOGGER = LogUtils.getLogger();
    private final HolderLookup.Provider registries;

    private static Map<ResourceLocation, Map<Holder<Item>, Float>> registeredCompostableItems = ImmutableMap.of();

    public CompostingManager(HolderLookup.Provider pRegistries) {
        super(GSON, "jinxedlib/composting");
        this.registries = pRegistries;
        System.out.println(getAllCompostableItems());
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsonElementMap, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        RegistryOps<JsonElement> registryops = this.registries.createSerializationContext(JsonOps.INSTANCE);
        JinxedDataMapsUtils.COMPOSTABLES.clear();

        ImmutableMap.Builder<ResourceLocation, Map<Holder<Item>, Float>> builder = ImmutableMap.builder();

        for(Map.Entry<ResourceLocation, JsonElement> entry : jsonElementMap.entrySet()) {
            ResourceLocation resourcelocation = entry.getKey();

            try {
                Optional<Composting> composting = Composting.CODEC.parse(registryops, entry.getValue())
                        .resultOrPartial(errorMsg -> LOGGER.warn("Could not decode CompostableItem with json id {} - error: {}", entry.getKey(), errorMsg));
                composting.ifPresent(value -> builder.put(entry.getKey(), value.values()));
            } catch (JsonParseException | IllegalArgumentException jsonparseexception) {
                LOGGER.error("Parsing error loading recipe {}", resourcelocation, jsonparseexception);
            }
        }
        registeredCompostableItems = builder.build();
        for (Map<Holder<Item>, Float> map : CompostingManager.getAllCompostableItems()) {
            for (Holder<Item> key : map.keySet()) {
                JinxedDataMapsUtils.COMPOSTABLES.put(key.value(), map.get(key));
            }
        }
    }

    public static Collection<Map<Holder<Item>, Float>> getAllCompostableItems() {
        return registeredCompostableItems.values().stream().toList();
    }
}
