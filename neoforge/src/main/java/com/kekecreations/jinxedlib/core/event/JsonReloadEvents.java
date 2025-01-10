package com.kekecreations.jinxedlib.core.event;

import com.kekecreations.jinxedlib.common.data.CompostingManager;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.AddReloadListenerEvent;

public class JsonReloadEvents {

    @SubscribeEvent
    public void reloadEvent(AddReloadListenerEvent event) {
        event.addListener(new CompostingManager(event.getRegistryAccess()));
    }
}
