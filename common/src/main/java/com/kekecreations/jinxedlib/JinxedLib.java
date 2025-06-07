package com.kekecreations.jinxedlib;

import com.kekecreations.jinxedlib.core.examples.JinxedRegistryExamples;
import com.kekecreations.jinxedlib.core.registry.JinxedDatapackRegistries;
import com.kekecreations.jinxedlib.core.util.JinxedCreativeCategoryHelper;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JinxedLib {
    public static final String MOD_ID = "jinxedlib";
    public static final String MOD_NAME = "JinxedLib";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);


    public static void init() {
        JinxedDatapackRegistries.register();
        JinxedCreativeCategoryHelper.addVanillaDyesToColourOrderBackToFront();
        JinxedCreativeCategoryHelper.addVanillaDyesToColourOrder();


       // JinxedRegistryExamples.loadExampleClass();
    }

    /**
     * This method is used where we need to use a different mod identifier
     * It is recommended you only use one identifier per mod. (This mod does this because it is a library)
     */
    public static ResourceLocation customId(String modID, String name) {
        return new ResourceLocation(modID, name);
    }

    public static ResourceLocation modID(String name) {
        return new ResourceLocation(MOD_ID, name);
    }
}