package com.kekecreations.jinxedlib.core;


import com.kekecreations.jinxedlib.JinxedLib;
import com.kekecreations.jinxedlib.core.util.JinxedRegistryHelper;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;
import java.util.function.Supplier;

public class JinxedRegistryExamples {

    //-------------------------BLOCK EXAMPLES-------------------------------------------------------------------------------------------------------------------------------------------------------

    public static final Supplier<Block> NO_BLOCK_ITEM = registerBlock("no_block_item",
            true,
            Block::new,
            BlockBehaviour.Properties.of().strength(1.75F, 5F));

    public static final Supplier<Block> HAS_BLOCK_ITEM = registerBlock("has_block_item",
            true,
            Block::new,
            BlockBehaviour.Properties.of().strength(1.75F, 5F));

    public static final Supplier<Block> HAS_BLOCK_ITEM_TWO = registerBlockWithCustomID("has_block_item_two",
            true,
           Block::new,
           BlockBehaviour.Properties.of().strength(1.75F, 5F));

    //PLEASE DO NOT REGISTER STUFF UNDER THE JINXEDLIB MOD ID, PLEASE USE YOUR OWN!
    public static Supplier<Block> registerBlock(String name, boolean hasItem, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        return JinxedRegistryHelper.registerBlock(JinxedLib.MOD_ID, name, hasItem, blockFactory, properties);
    }

    //REPLACE "YOUR MOD ID" WITH YOUR OWN MOD ID
    public static Supplier<Block> registerBlockWithCustomID(String name, boolean hasItem, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        return JinxedRegistryHelper.registerBlock("your_mod_id", name, hasItem, blockFactory, properties);
    }


    public static final Supplier<Block> EXAMPLE_BLOCK = registerBlock(
            "example_block", true, registryName -> new Block(
                    BlockBehaviour.Properties.of()
                            .setId(JinxedRegistryHelper.blockKey(registryName))
            )
    );

    //PLEASE DO NOT REGISTER STUFF UNDER THE JINXEDLIB MOD ID, PLEASE USE YOUR OWN!
    public static Supplier<Block> registerBlock(String name, boolean hasItem, Function<Identifier, ? extends Block> function) {
        return JinxedRegistryHelper.registerBlock(JinxedLib.MOD_ID, name, hasItem, function);
    }


    //---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //-------------------------ITEM EXAMPLES-------------------------------------------------------------------------------------------------------------------------------------------------------


    public static final Supplier<Item> JINX_STICK = registerItem("jinx_stick",
                    FlintAndSteelItem::new,
                    new Item.Properties().stacksTo(1));

    public static final Supplier<Item> JINX_SWORD = registerItem(
            "jinx_sword",  registryName -> new TridentItem(
                    new Item.Properties().stacksTo(1).setId(JinxedRegistryHelper.itemKey(registryName))
            )
    );

    public static Supplier<Item> registerItem(String name, Function<Item.Properties, Item> itemFactory, Item.Properties properties) {
        return JinxedRegistryHelper.registerItem(JinxedLib.MOD_ID, name, itemFactory, properties);
    }

    public static Supplier<Item> registerItem(String name, Function<Identifier, ? extends Item> function) {
        return JinxedRegistryHelper.registerItem(JinxedLib.MOD_ID, name, function);
    }




    //IGNORE
    public static void loadExampleClass() {}
}
