package com.kekecreations.jinxedlib.common.block;

import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class CommonPressurePlateBlock extends PressurePlateBlock {
    public CommonPressurePlateBlock(Sensitivity sensitivity, BlockSetType blockSetType, Properties properties) {
        super(sensitivity, properties, blockSetType);
    }
}
