package com.kekecreations.jinxedlib.core.util;

import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public class JinxedSignUtils {

    /**
     * These lists allow you to add your custom sign to the isValid method (Fixes sign blocks being invisible)
     */
    public static List<Block> SIGN_IS_VALID = new ArrayList<>();
    public static List<Block> HANGING_SIGN_IS_VALID = new ArrayList<>();


    /**
     * This method adds the sign to the isValid method (Fixes sign blocks being invisible)
     */
    public static void isSignValid(Block block) {
        SIGN_IS_VALID.add(block);
    }

    /**
     * This method adds the hanging sign to the isValid method (Fixes sign blocks being invisible)
     */
    public static void isHangingSignValid(Block block) {
        HANGING_SIGN_IS_VALID.add(block);
    }
}
