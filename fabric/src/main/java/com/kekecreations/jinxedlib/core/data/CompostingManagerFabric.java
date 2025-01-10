package com.kekecreations.jinxedlib.core.data;

import com.kekecreations.jinxedlib.common.data.CompostingManager;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;

public class CompostingManagerFabric extends CompostingManager implements IdentifiableResourceReloadListener {
    public CompostingManagerFabric(HolderLookup.Provider pRegistries) {
        super(pRegistries);
    }

    @Override
    public ResourceLocation getFabricId() {
        return ResourceLocation.parse("composting");
    }
}
