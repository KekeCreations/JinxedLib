package com.kekecreations.jinxedlib;


import com.kekecreations.jinxedlib.core.event.JsonReloadEvents;
import com.kekecreations.jinxedlib.core.registry.JinxedSpriteSources;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

@Mod(JinxedLib.MOD_ID)
public class JinxedLibNeoForge {

    public JinxedLibNeoForge(IEventBus eventBus) {
        JinxedLib.init();

        if (FMLEnvironment.dist == Dist.CLIENT) {
            JinxedSpriteSources.register();
        }
        final var compostables = new DataMapProvider.Builder(NeoForgeDataMaps.COMPOSTABLES);
        compostables.add(Items.DIAMOND.builtInRegistryHolder(), new Compostable(0.5F, false), false);
        NeoForge.EVENT_BUS.register(new JsonReloadEvents());


    }
}