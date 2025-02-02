package com.kekecreations.jinxedlib.common.data;

import com.kekecreations.jinxedlib.core.registry.JinxedDatapackRegistries;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class WaxableUtils {

    public static boolean canWaxBlock(RegistryAccess access, BlockState state) {
        Optional<Waxables> optionalPalette = access.registryOrThrow(JinxedDatapackRegistries.WAXABLES).stream().filter(
                searchPalette -> searchPalette.values().containsKey(state.getBlockHolder())
        ).findFirst();
        return optionalPalette.isPresent();
    }

    @Nullable
    public static Block getWaxedBlock(RegistryAccess access, BlockState state) {
        Optional<Waxables> optionalPalette = access.registryOrThrow(JinxedDatapackRegistries.WAXABLES).stream().filter(
                searchPalette -> searchPalette.values().containsKey(state.getBlockHolder())
        ).findFirst();
        if (optionalPalette.isEmpty()) return null;
        Waxables palette = optionalPalette.get();
        Holder<Block> holder = palette.values().get(state.getBlockHolder());
        if (holder.unwrapKey().isEmpty()) return null;
        return access.registryOrThrow(Registries.BLOCK).getOrThrow(holder.unwrapKey().get());
    }
}
