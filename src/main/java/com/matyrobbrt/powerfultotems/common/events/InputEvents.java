package com.matyrobbrt.powerfultotems.common.events;

import com.matyrobbrt.powerfultotems.PowerfulTotems;
import com.matyrobbrt.powerfultotems.core.init.KeybindsInit;
import com.matyrobbrt.powerfultotems.core.network.TotemNetwork;
import com.matyrobbrt.powerfultotems.core.network.message.FallTotemMessage;
import com.matyrobbrt.powerfultotems.core.network.message.FireResistanceTotemMessage;
import com.matyrobbrt.powerfultotems.core.network.message.FireworkTotemMessage;
import com.matyrobbrt.powerfultotems.core.network.message.FlightTotemMessage;
import com.matyrobbrt.powerfultotems.core.network.message.GodTotemMessage;
import com.matyrobbrt.powerfultotems.core.network.message.InputMessage;
import com.matyrobbrt.powerfultotems.core.network.message.MagnetTotemMessage;
import com.matyrobbrt.powerfultotems.core.network.message.SpeedTotemMessage;
import com.matyrobbrt.powerfultotems.core.network.message.UpgradeMessage;
import com.matyrobbrt.powerfultotems.core.network.message.WaterBreathingTotemMessage;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = PowerfulTotems.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class InputEvents {

	@SubscribeEvent
	public static void onKeyPress(InputEvent.KeyInputEvent event) {
		Minecraft mc = Minecraft.getInstance();
		if (mc.level == null)
			return;
		onInput(mc, event.getKey(), event.getAction());
	}

	private static void onInput(Minecraft mc, int key, int action) {

		if (mc.screen == null && KeybindsInit.activateTotem.isDown()) {
			TotemNetwork.CHANNEL.sendToServer(new InputMessage(key));
		}

		if (mc.screen == null && KeybindsInit.fallTotemKeyBinding.isDown()) {
			TotemNetwork.FALL_TOTEM_CHANNEL.sendToServer(new FallTotemMessage(key));
		}

		if (mc.screen == null && KeybindsInit.speedTotemKeyBinding.isDown()) {
			TotemNetwork.SPEED_TOTEM_CHANNEL.sendToServer(new SpeedTotemMessage(key));
		}

		if (mc.screen == null && KeybindsInit.waterBreathingTotemKeyBinding.isDown()) {
			TotemNetwork.WATER_BREATHING_TOTEM_CHANNEL.sendToServer(new WaterBreathingTotemMessage(key));
		}

		if (mc.screen == null && KeybindsInit.godTotemKeyBinding.isDown()) {
			TotemNetwork.GOD_TOTEM_CHANNEL.sendToServer(new GodTotemMessage(key));
		}

		if (mc.screen == null && KeybindsInit.fireResistanceTotemKeyBinding.isDown()) {
			TotemNetwork.FIRE_RESISTANCE_TOTEM_CHANNEL.sendToServer(new FireResistanceTotemMessage(key));
		}

		if (mc.screen == null && KeybindsInit.flightTotemKeyBinding.isDown()) {
			TotemNetwork.FLIGHT_TOTEM_CHANNEL.sendToServer(new FlightTotemMessage(key));
		}

		if (mc.screen == null && KeybindsInit.fireworkTotemKeyBinding.isDown()) {
			TotemNetwork.FIREWORK_TOTEM_CHANNEL.sendToServer(new FireworkTotemMessage(key));
		}
		
		if (mc.screen == null && KeybindsInit.upgradeKeyBinding.isDown()) {
			TotemNetwork.UPGRADE_CHANNEL.sendToServer(new UpgradeMessage(key));
		}
		
		if (KeybindsInit.magnetTotemKeyBinding.isDown()) {
			TotemNetwork.MAGNET_TOTEM_CHANNEL.sendToServer(new MagnetTotemMessage(-1));
		}

	}
}
