package com.kekecreations.jinxedlib.core.util;

import com.google.common.collect.Sets;
import com.kekecreations.jinxedlib.JinxedLib;
import com.kekecreations.jinxedlib.core.mixin.SpriteSourcesInvoker;
import com.kekecreations.jinxedlib.core.mixin.WoodTypeInvoker;
import com.kekecreations.jinxedlib.core.platform.Services;
import com.mojang.serialization.Codec;
import net.minecraft.client.renderer.texture.atlas.SpriteSource;
import net.minecraft.client.renderer.texture.atlas.SpriteSourceType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.Collections;
import java.util.Set;
import java.util.function.Supplier;

public class JinxedRegistryHelper {
    /**
     * This method allows you to make your own registry methods (like the ones in this class!)
     */
    public static <T> Supplier<T> register(Registry<T> registry, String modID, String name, Supplier<T> supplier) {
        return Services.REGISTRY.register(registry, modID, name, supplier);
    }

    /**
     * This method allows you to register an item
     * @param modID Your mod Identifier (you can make a method so you don't have to input this all the time)
     * @param name Name of your tab (for example: gold_sword)
     * @param supplier () -> new tab
     */
    public static <T extends CreativeModeTab> Supplier<T> registerCreativeModeTab(String modID, String name, Supplier<T> supplier) {
        return Services.REGISTRY.registerCreativeModeTab(modID, name, supplier);
    }

    /**
     * This method allows you to register an item
     * @param modID Your mod Identifier (you can make a method so you don't have to input this all the time)
     * @param name Name of your item (for example: gold_sword)
     * @param supplier () -> new item
     */
    public static Supplier<Item> registerItem(String modID, String name, Supplier<Item> supplier) {
        return Services.REGISTRY.register(BuiltInRegistries.ITEM, modID, name, supplier);
    }


    /**
     * This method allows you to register block with or without block items
     * @param modID Your mod Identifier (you can make a method so you don't have to input this all the time)
     * @param name Name of your block (for example: gold_block)
     * @param hasItem Should the block have a block item?
     * @param supplier () -> new block
     */
    public static  Supplier<Block> registerBlock(String modID, String name, boolean hasItem, Supplier<Block> supplier) {
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
        return WoodTypeInvoker.invokeRegister(woodType);
    }




    private static final Set<ResourceLocation> LOCATIONS = Sets.newHashSet();
    private static final Set<ResourceLocation> IMMUTABLE_LOCATIONS = Collections.unmodifiableSet(LOCATIONS);


    /**
     * This method allows you to register custom-built in loot tables
     * @param modID Your mod identifier
     * @param name Name of your built-in loot table (can include paths like gameplay/lotus_flower_harvest)
     */
    public static ResourceLocation registerBuiltInLootTable(String modID, String name) {
        return register(JinxedLib.customId(modID, name));
    }

    private static ResourceLocation register(ResourceLocation resourceLocation) {
        if (LOCATIONS.add(resourceLocation)) {
            return resourceLocation;
        }
        throw new IllegalArgumentException(resourceLocation + " is already a registered built-in loot table");
    }

    public static Set<ResourceLocation> all() {
        return IMMUTABLE_LOCATIONS;
    }


    /**
     * This method allows you to register sprite sources but under the Minecraft namespace
     * Its recommended you put your mod ID in front of the sprite source name (for example: jinxedlib_sprite_source)
     * @param id The identifier of your custom sprite source
     * @param codec A map codec that extends the Sprite Source class
     */
    public static SpriteSourceType registerSpriteSource(String id, Codec<? extends SpriteSource> codec) {
        return SpriteSourcesInvoker.invokeRegister(id, codec);
    }
}
