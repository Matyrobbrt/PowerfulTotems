package com.matyrobbrt.powerfultotems.core.network;

import com.matyrobbrt.powerfultotems.PowerfulTotems;
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

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class TotemNetwork {

	public static final String NETWORK_VERSION = "0.10.0";

	public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(PowerfulTotems.MOD_ID, "network"), () -> NETWORK_VERSION,
			version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));

	public static final SimpleChannel FALL_TOTEM_CHANNEL = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(PowerfulTotems.MOD_ID, "fall_totem_channel"), () -> NETWORK_VERSION,
			version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));

	public static final SimpleChannel SPEED_TOTEM_CHANNEL = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(PowerfulTotems.MOD_ID, "speed_totem_channel"), () -> NETWORK_VERSION,
			version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));

	public static final SimpleChannel WATER_BREATHING_TOTEM_CHANNEL = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(PowerfulTotems.MOD_ID, "water_breathing_totem_channel"), () -> NETWORK_VERSION,
			version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));

	public static final SimpleChannel GOD_TOTEM_CHANNEL = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(PowerfulTotems.MOD_ID, "god_totem_channel"), () -> NETWORK_VERSION,
			version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));

	public static final SimpleChannel FIREWORK_TOTEM_CHANNEL = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(PowerfulTotems.MOD_ID, "firework_totem_channel"), () -> NETWORK_VERSION,
			version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));

	public static final SimpleChannel FIRE_RESISTANCE_TOTEM_CHANNEL = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(PowerfulTotems.MOD_ID, "fire_resistance_totem_channel"), () -> NETWORK_VERSION,
			version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));

	public static final SimpleChannel FLIGHT_TOTEM_CHANNEL = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(PowerfulTotems.MOD_ID, "flight_totem_channel"), () -> NETWORK_VERSION,
			version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));

	public static final SimpleChannel MAGNET_TOTEM_CHANNEL = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(PowerfulTotems.MOD_ID, "magnet_totem_channel"), () -> NETWORK_VERSION,
			version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));

	public static final SimpleChannel UPGRADE_CHANNEL = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(PowerfulTotems.MOD_ID, "upgrade_channel"), () -> NETWORK_VERSION,
			version -> version.equals(NETWORK_VERSION), version -> version.equals(NETWORK_VERSION));

	public static void init() {

		CHANNEL.registerMessage(0, InputMessage.class, InputMessage::encode, InputMessage::decode,
				InputMessage::handle);
		FALL_TOTEM_CHANNEL.registerMessage(1, FallTotemMessage.class, FallTotemMessage::encode,
				FallTotemMessage::decode, FallTotemMessage::handle);
		SPEED_TOTEM_CHANNEL.registerMessage(2, SpeedTotemMessage.class, SpeedTotemMessage::encode,
				SpeedTotemMessage::decode, SpeedTotemMessage::handle);
		WATER_BREATHING_TOTEM_CHANNEL.registerMessage(3, WaterBreathingTotemMessage.class,
				WaterBreathingTotemMessage::encode, WaterBreathingTotemMessage::decode,
				WaterBreathingTotemMessage::handle);
		GOD_TOTEM_CHANNEL.registerMessage(4, GodTotemMessage.class, GodTotemMessage::encode, GodTotemMessage::decode,
				GodTotemMessage::handle);
		FLIGHT_TOTEM_CHANNEL.registerMessage(5, FlightTotemMessage.class, FlightTotemMessage::encode,
				FlightTotemMessage::decode, FlightTotemMessage::handle);
		FIRE_RESISTANCE_TOTEM_CHANNEL.registerMessage(6, FireResistanceTotemMessage.class,
				FireResistanceTotemMessage::encode, FireResistanceTotemMessage::decode,
				FireResistanceTotemMessage::handle);
		FIREWORK_TOTEM_CHANNEL.registerMessage(7, FireworkTotemMessage.class, FireworkTotemMessage::encode,
				FireworkTotemMessage::decode, FireworkTotemMessage::handle);
		MAGNET_TOTEM_CHANNEL.registerMessage(8, MagnetTotemMessage.class, MagnetTotemMessage::encode,
				MagnetTotemMessage::decode, MagnetTotemMessage::handle);
		UPGRADE_CHANNEL.registerMessage(9, UpgradeMessage.class, UpgradeMessage::encode, UpgradeMessage::decode,
				UpgradeMessage::handle);

	}

}
