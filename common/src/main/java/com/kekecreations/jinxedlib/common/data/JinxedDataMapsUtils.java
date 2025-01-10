package com.kekecreations.jinxedlib.common.data;

import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import net.minecraft.world.level.ItemLike;

public class JinxedDataMapsUtils {

    /**
     * DO NOT ADD TO THIS VIA JAVA CLASSES.
     */
    protected static final Object2FloatMap<ItemLike> COMPOSTABLES = new Object2FloatOpenHashMap<>();


    public static boolean compostablesContainsKey(ItemLike item) {
        return COMPOSTABLES.containsKey(item);
    }
    
    public static Float getCompostableValue(ItemLike item) {
        return COMPOSTABLES.get(item);
    }
}
