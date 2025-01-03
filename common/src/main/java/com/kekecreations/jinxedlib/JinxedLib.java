package com.kekecreations.jinxedlib;

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
    }


    public static ResourceLocation customId(String modID, String name) {
        return ResourceLocation.fromNamespaceAndPath(modID, name);
    }

    public static ResourceLocation modID(String name) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
    }
}