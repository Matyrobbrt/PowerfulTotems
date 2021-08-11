package com.matyrobbrt.powerfultotems.core.init;

import com.matyrobbrt.powerfultotems.PowerfulTotems;
import com.matyrobbrt.powerfultotems.common.item.totem.BeaconTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.FallTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.FireResistanceTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.FireworkTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.FlightTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.GluttonyTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.GodTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.HealthTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.ImmunityTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.IronGolemTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.JumpTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.LightningTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.MagnetTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.NightVisionTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.RegenerationTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.SpeedTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.StrengthTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.TeleportTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.TotemOfDying;
import com.matyrobbrt.powerfultotems.common.item.totem.TrowelTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.VoidTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.WaterBreathingTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.WaterTotem;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TotemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			PowerfulTotems.MOD_ID);

	public static final RegistryObject<HealthTotem> HEALTH_TOTEM = ITEMS.register("health_totem", HealthTotem::new);
	public static final RegistryObject<NightVisionTotem> NIGHT_VISON_TOTEM = ITEMS.register("night_vision_totem",
			NightVisionTotem::new);
	public static final RegistryObject<FallTotem> FALL_TOTEM = ITEMS.register("fall_totem", FallTotem::new);
	public static final RegistryObject<SpeedTotem> SPEED_TOTEM = ITEMS.register("speed_totem", SpeedTotem::new);
	public static final RegistryObject<WaterBreathingTotem> WATER_BREATHING_TOTEM = ITEMS
			.register("water_breathing_totem", WaterBreathingTotem::new);
	public static final RegistryObject<FlightTotem> FLIGHT_TOTEM = ITEMS.register("flight_totem", FlightTotem::new);
	public static final RegistryObject<GodTotem> GOD_TOTEM = ITEMS.register("god_totem", GodTotem::new);
	public static final RegistryObject<JumpTotem> JUMP_TOTEM = ITEMS.register("jump_totem", JumpTotem::new);
	public static final RegistryObject<GluttonyTotem> GLUTTONY_TOTEM = ITEMS.register("gluttony_totem",
			GluttonyTotem::new);
	public static final RegistryObject<FireResistanceTotem> FIRE_RESISTANCE_TOTEM = ITEMS
			.register("fire_resistance_totem", FireResistanceTotem::new);
	public static final RegistryObject<StrengthTotem> STRENGTH_TOTEM = ITEMS.register("strength_totem",
			StrengthTotem::new);
	public static final RegistryObject<FireworkTotem> FIREWORK_TOTEM = ITEMS.register("firework_totem",
			FireworkTotem::new);
	public static final RegistryObject<MagnetTotem> MAGNET_TOTEM = ITEMS.register("magnet_totem", MagnetTotem::new);
	public static final RegistryObject<ImmunityTotem> IMMUNITY_TOTEM = ITEMS.register("immunity_totem",
			ImmunityTotem::new);
	public static final RegistryObject<RegenerationTotem> REGENERATION_TOTEM = ITEMS.register("regeneration_totem",
			RegenerationTotem::new);
	public static final RegistryObject<BeaconTotem> BEACON_TOTEM = ITEMS.register("beacon_totem", BeaconTotem::new);
	public static final RegistryObject<TeleportTotem> TELEPORT_TOTEM = ITEMS.register("teleport_totem",
			TeleportTotem::new);
	public static final RegistryObject<WaterTotem> WATER_TOTEM = ITEMS.register("water_totem", WaterTotem::new);
	public static final RegistryObject<IronGolemTotem> GOLEM_TOTEM = ITEMS.register("golem_totem", IronGolemTotem::new);
	public static final RegistryObject<TotemOfDying> DYING_TOTEM = ITEMS.register("totem_of_dying", TotemOfDying::new);
	public static final RegistryObject<VoidTotem> VOID_TOTEM = ITEMS.register("void_totem", VoidTotem::new);
	public static final RegistryObject<TrowelTotem> TROWEL_TOTEM = ITEMS.register("trowel_totem", TrowelTotem::new);
	public static final RegistryObject<LightningTotem> LIGHTNING_TOTEM = ITEMS.register("lightning_totem", LightningTotem::new);

}
