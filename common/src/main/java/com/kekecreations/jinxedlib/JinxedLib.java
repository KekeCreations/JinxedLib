package com.kekecreations.jinxedlib;

import com.kekecreations.jinxedlib.core.registry.JinxedRegistries;
import com.kekecreations.jinxedlib.core.util.JinxedCreativeCategoryUtils;
import com.kekecreations.jinxedlib.examples.JinxedExamples;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JinxedLib {

    public static final String MOD_ID = "jinxedlib";
    public static final String MOD_NAME = "JinxedLib";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {
        JinxedExamples.loadExampleClass();

        JinxedRegistries.register();
        JinxedCreativeCategoryUtils.addVanillaDyesToColourOrder();
        JinxedCreativeCategoryUtils.addVanillaDyesToColourOrderBackToFront();
    }


    /**
     * This method is used where we need to use a different mod identifier
     * It is recommended you only use one identifier per mod. (This mod does this because it is a library)
     */
    public static ResourceLocation customId(String modID, String name) {
        return ResourceLocation.fromNamespaceAndPath(modID, name);
    }

    public static ResourceLocation modID(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }
}