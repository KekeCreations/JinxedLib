package com.kekecreations.jinxedlib.common.block;

import net.minecraft.world.level.block.RotatedPillarBlock;

/**
 * Any blocks using this class will be automatically flammable on the NeoForge side.
 * It is recommended you make it flammable on the fabric side as well!
 */
public class FlammableRotatedPillarBlock extends RotatedPillarBlock {
    public FlammableRotatedPillarBlock(Properties properties) {
        super(properties);
    }
}
