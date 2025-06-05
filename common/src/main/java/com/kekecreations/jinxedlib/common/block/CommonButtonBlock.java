package com.kekecreations.jinxedlib.common.block;

import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class CommonButtonBlock extends ButtonBlock {
    public CommonButtonBlock(BlockSetType blockSetType, int ticksToStayPressed, boolean arrowsCanPress, Properties properties) {
        super(properties, blockSetType, ticksToStayPressed, arrowsCanPress);
    }
}
