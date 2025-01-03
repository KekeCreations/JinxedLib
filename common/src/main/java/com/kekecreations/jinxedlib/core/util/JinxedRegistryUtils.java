package com.kekecreations.jinxedlib.core.util;

import com.kekecreations.jinxedlib.core.mixin.SpriteSourcesMixin;
import com.kekecreations.jinxedlib.core.platform.Services;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.texture.atlas.SpriteSource;
import net.minecraft.client.renderer.texture.atlas.SpriteSourceType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class JinxedRegistryUtils {
    //TO CREATE YOUR OWN REGISTRY METHODS  (Like the ones in this class)
    public static <T> Supplier<T> register(Registry<? super T> registry, String modID, String name, Supplier<T> supplier) {
        return Services.REGISTRY.register(registry, modID, name, supplier);
    }

    //TO REGISTER AN ITEM
    public static <T extends Item> Supplier<T> registerItem(String modID, String name, Supplier<T> supplier) {
        return Services.REGISTRY.register(BuiltInRegistries.ITEM, modID, name, supplier);
    }


    //TO REGISTER BLOCK WITH OR WITHOUT BLOCK ITEMS
    public static <T extends Block> Supplier<T> registerBlock(String modID, String name, boolean hasItem, Supplier<T> supplier) {
        var block = Services.REGISTRY.register(BuiltInRegistries.BLOCK, modID, name, supplier);
        if (hasItem) {
            registerItem(modID, name, () -> new BlockItem(block.get(), new Item.Properties()));
        }
        return block;
    }

    //REGISTER SPRITE SOURCES UNDER THE MINECRAFT NAMESPACE
    public static SpriteSourceType registerSpriteSources(String id, MapCodec<? extends SpriteSource> codec) {
        return SpriteSourcesMixin.invokeRegister(id, codec);
    }




}
