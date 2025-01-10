package com.kekecreations.jinxedlib.common.data;

import com.kekecreations.jinxedlib.core.registry.JinxedDatapackRegistries;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class CompostableUtils {

    public static Float getCompostableValue(RegistryAccess access, ItemStack stack) {
        Optional<Compostables> optionalPalette = access.registryOrThrow(JinxedDatapackRegistries.COMPOSTABLES).stream().filter(
                searchPalette -> searchPalette.values().containsKey(stack.getItemHolder())
        ).findFirst();
        if (optionalPalette.isEmpty()) return 0.0F;
        Compostables palette = optionalPalette.get();
        Float holder = palette.values().get(stack.getItemHolder());
        if (holder.isNaN()) return 0.0F;
        return holder;
    }

    public static boolean isItemCompostable(RegistryAccess access, ItemStack stack) {
        Optional<Compostables> optionalPalette = access.registryOrThrow(JinxedDatapackRegistries.COMPOSTABLES).stream().filter(
                searchPalette -> searchPalette.values().containsKey(stack.getItemHolder())
        ).findFirst();
        return optionalPalette.isPresent();
    }
}
