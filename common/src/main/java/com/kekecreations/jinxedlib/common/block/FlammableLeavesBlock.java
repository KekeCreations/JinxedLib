package com.kekecreations.jinxedlib.common.block;

import net.minecraft.world.level.block.LeavesBlock;

/**
 * Any blocks using this class will be automatically flammable on the NeoForge side.
 * It is recommended you make it flammable on the fabric side as well!
 */
public abstract class FlammableLeavesBlock extends LeavesBlock {

    public FlammableLeavesBlock(float leafParticleChance, Properties properties) {
        super(leafParticleChance, properties);
    }
}
