package com.matyrobbrt.powerfultotems.datagen;

import com.matyrobbrt.powerfultotems.PowerfulTotems;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.data.event.GatherDataEvent;

@Mod.EventBusSubscriber(modid = PowerfulTotems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PowerfulTotemsDatagen {

    @SubscribeEvent
    public static void onDatagen(GatherDataEvent event) {

    }
}
