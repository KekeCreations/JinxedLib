package com.kekecreations.jinxedlib.core.util;

import com.kekecreations.jinxedlib.JinxedLib;
import com.kekecreations.jinxedlib.core.mixin.SpriteSourcesMixin;
import com.kekecreations.jinxedlib.core.mixin.WoodTypeMixin;
import com.kekecreations.jinxedlib.core.platform.Services;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.texture.atlas.SpriteSource;
import net.minecraft.client.renderer.texture.atlas.SpriteSourceType;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class JinxedRegistryUtils {

    /**
     * This method allows you to make your own registry methods (like the ones in this class!)
     */
    public static <T> Supplier<T> register(Registry<? super T> registry, String modID, String name, Supplier<T> supplier) {
        return Services.REGISTRY.register(registry, modID, name, supplier);
    }

    /**
     * This method allows you to register an item
     * @param modID Your mod Identifier (you can make a method so you don't have to input this all the time)
     * @param name Name of your item (for example: gold_sword)
     * @param supplier () -> new item
     */
    public static <T extends Item> Supplier<T> registerItem(String modID, String name, Supplier<T> supplier) {
        return Services.REGISTRY.register(BuiltInRegistries.ITEM, modID, name, supplier);
    }


    /**
     * This method allows you to register block with or without block items
     * @param modID Your mod Identifier (you can make a method so you don't have to input this all the time)
     * @param name Name of your block (for example: gold_block)
     * @param hasItem Should the block have a block item?
     * @param supplier () -> new block
     */
    public static <T extends Block> Supplier<T> registerBlock(String modID, String name, boolean hasItem, Supplier<T> supplier) {
        var block = Services.REGISTRY.register(BuiltInRegistries.BLOCK, modID, name, supplier);
        if (hasItem) {
            registerItem(modID, name, () -> new BlockItem(block.get(), new Item.Properties()));
        }
        return block;
    }


    /**
     * This method allows you to register custom wood types
     * @param woodType Your custom wood type
     */
    public static WoodType registerWoodType(WoodType woodType) {
        return WoodTypeMixin.invokeRegister(woodType);
    }

    /**
     * This method allows you to register custom tree growers
     * @param modID Your mod identifier
     * @param name Your tree grower's name
     * @param tree Optional tree feature
     * @param secondaryTree Optional secondary tree feature
     */
    public static TreeGrower register(String modID, String name, Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree) {
        return register(modID, name, 0, Optional.empty(), Optional.empty(), tree, secondaryTree, Optional.empty(), Optional.empty());
    }

    private static TreeGrower register(String modID, String name, float secondaryChance, Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryMegaTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> flowers, Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryFlowers) {
        return new TreeGrower(String.format("%s:%s", modID, name), secondaryChance, megaTree, secondaryMegaTree, tree, secondaryTree, flowers, secondaryFlowers);
    }



    private static final Set<ResourceKey<LootTable>> LOCATIONS = new HashSet();
    private static final Set<ResourceKey<LootTable>> IMMUTABLE_LOCATIONS;


    /**
     * This method allows you to register custom-built in loot tables
     * @param modID Your mod identifier
     * @param name Name of your built-in loot table (can include paths like gameplay/lotus_flower_harvest)
     */
    public static ResourceKey<LootTable> registerBuiltInLootTable(String modID, String name) {
        return register(ResourceKey.create(Registries.LOOT_TABLE, JinxedLib.customId(modID, name)));
    }

    private static ResourceKey<LootTable> register(ResourceKey<LootTable> $$0) {
        if (LOCATIONS.add($$0)) {
            return $$0;
        } else {
            throw new IllegalArgumentException(String.valueOf($$0.location()) + " is already a registered built-in loot table");
        }
    }

    public static Set<ResourceKey<LootTable>> all() {
        return IMMUTABLE_LOCATIONS;
    }


    /**
     * This method allows you to register sprite sources but under the Minecraft namespace
     * Its recommended you put your mod ID in front of the sprite source name (for example: jinxedlib_sprite_source)
     * @param id The identifier of your custom sprite source
     * @param codec A map codec that extends the Sprite Source class
     */
    public static SpriteSourceType registerSpriteSource(String id, MapCodec<? extends SpriteSource> codec) {
        return SpriteSourcesMixin.invokeRegister(id, codec);
    }

    static {
        IMMUTABLE_LOCATIONS = Collections.unmodifiableSet(LOCATIONS);
    }
}
