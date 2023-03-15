package com.matyrobbrt.powerfultotems;

import com.matyrobbrt.powerfultotems.core.config.TotemConfig;
import com.matyrobbrt.powerfultotems.core.init.ItemInit;
import com.matyrobbrt.powerfultotems.core.init.PotionInit;
import com.matyrobbrt.powerfultotems.core.init.TotemInit;
import com.matyrobbrt.powerfultotems.core.network.TotemNetwork;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("powerfultotems")
public class PowerfulTotems {

    public static final String MOD_ID = "powerfultotems";

    public PowerfulTotems() {

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::commonSetup);

        ModLoadingContext.get().registerConfig(Type.COMMON, TotemConfig.SPEC, "powerfultotems-common.toml");

        ItemInit.ITEMS.register(bus);
        TotemInit.ITEMS.register(bus);

        PotionInit.EFFECTS.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public void commonSetup(final FMLCommonSetupEvent event) {
        TotemNetwork.init();
    }

}
