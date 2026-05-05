package com.kekecreations.jinxedlib;

import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JinxedLib {

    public static final String MOD_ID = "jinxedlib";
    public static final String MOD_NAME = "JinxedLib";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);


    public static void init() {
    }


    /**
     * This method is used where we need to use a different mod identifier
     * It is recommended you only use one identifier per mod. (This mod does this because it is a library)
     */
    public static Identifier customId(String modID, String name) {
        return Identifier.fromNamespaceAndPath(modID, name);
    }

    public static Identifier modID(String name) {
        return Identifier.fromNamespaceAndPath(MOD_ID, name);
    }
}