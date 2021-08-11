package com.matyrobbrt.powerfultotems.core.util;

import com.matyrobbrt.powerfultotems.PowerfulTotems;
import com.matyrobbrt.powerfultotems.core.init.KeybindsInit;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = PowerfulTotems.MOD_ID, bus=Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
	
	@SubscribeEvent
	public static void ClientSetup(FMLClientSetupEvent event) {
		
		KeybindsInit.register(event);
		
		
	}
}
