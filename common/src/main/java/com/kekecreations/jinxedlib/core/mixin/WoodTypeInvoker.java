package com.kekecreations.jinxedlib.core.mixin;

import net.minecraft.world.level.block.state.properties.WoodType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(WoodType.class)
public interface WoodTypeInvoker {

    @Invoker
    static WoodType invokeRegister(WoodType woodType) {
        throw new AssertionError();
    }
}
