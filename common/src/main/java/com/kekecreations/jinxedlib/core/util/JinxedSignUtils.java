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
}
