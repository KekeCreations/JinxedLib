package com.kekecreations.jinxedlib.examples;


import com.kekecreations.jinxedlib.JinxedLib;
import com.kekecreations.jinxedlib.core.util.JinxedRegistryUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class JinxedExamples {

    //-------------------------BLOCK EXAMPLES-------------------------------------------------------------------------------------------------------------------------------------------------------

    public static final Supplier<Block> NO_BLOCK_ITEM = registerBlock("no_block_item", false,
            () -> new Block(BlockBehaviour.Properties.of().strength(1.75F, 5F)));

    public static final Supplier<Block> HAS_BLOCK_ITEM = registerBlock("has_block_item", true,
            () -> new Block(BlockBehaviour.Properties.of().strength(1.75F, 5F)));

    public static final Supplier<Block> HAS_BLOCK_ITEM_TWO = registerBlockWithCustomID("has_block_item_two", true,
            () -> new Block(BlockBehaviour.Properties.of().strength(1.75F, 5F)));

    //PLEASE DO NOT REGISTER STUFF UNDER THE JINXEDLIB MOD ID, PLEASE USE YOUR OWN!
    public static Supplier<Block> registerBlock(String name, boolean hasItem, Supplier<Block> supplier) {
        return JinxedRegistryUtils.registerBlock(JinxedLib.MOD_ID, name, hasItem, supplier);
    }

    //REPLACE "YOUR MOD ID" WITH YOUR OWN MOD ID
    public static Supplier<Block> registerBlockWithCustomID(String name, boolean hasItem, Supplier<Block> supplier) {
        return JinxedRegistryUtils.registerBlock("your_mod_id", name, hasItem, supplier);
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //-------------------------ITEM EXAMPLES-------------------------------------------------------------------------------------------------------------------------------------------------------

    public static final Supplier<Item> JINX_STICK = registerItem("jinx_stick", () -> new Item(new Item.Properties().stacksTo(1)));


    public static Supplier<Item> registerItem(String name, Supplier<Item> supplier) {
        return JinxedRegistryUtils.registerItem(JinxedLib.MOD_ID, name, supplier);
    }




    //IGNORE
    public static void loadExampleClass() {}
}
