package com.kekecreations.jinxedlib.common.data;

import com.kekecreations.jinxedlib.core.registry.JinxedDatapackRegistries;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;

import java.util.*;

public class FurnaceFuelUtils {

    public static Integer getBurnTime(RegistryAccess access, ItemStack stack) {
        Optional<FurnaceFuels> optionalPalette = access.registryOrThrow(JinxedDatapackRegistries.FURNACE_FUELS).stream().filter(
                searchPalette -> searchPalette.values().containsKey(stack.getItemHolder())
        ).findFirst();
        if (optionalPalette.isEmpty()) return 0;
        FurnaceFuels palette = optionalPalette.get();
        return palette.values().get(stack.getItemHolder());
    }

    public static boolean isItemFurnaceFuel(RegistryAccess access, ItemStack stack) {
        Optional<FurnaceFuels> optionalPalette = access.registryOrThrow(JinxedDatapackRegistries.FURNACE_FUELS).stream().filter(
                searchPalette -> searchPalette.values().containsKey(stack.getItemHolder())
        ).findFirst();
        return optionalPalette.isPresent();
    }
}
