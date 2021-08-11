package com.matyrobbrt.powerfultotems.core.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class TotemConfig {
	
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	
	//Health Totem
	public static final ForgeConfigSpec.ConfigValue<Integer> health_totem_max_effect_level;
	public static final ForgeConfigSpec.ConfigValue<Integer> health_totem_effect_duration;
	
	//Flight Totem
	public static final ForgeConfigSpec.ConfigValue<Integer> flight_totem_effect_duration;
	
	//Night Vison Totem
	public static final ForgeConfigSpec.ConfigValue<Integer> night_vision_totem_effect_duration;
	
	//Fall Totem
	public static final ForgeConfigSpec.ConfigValue<Integer> fall_totem_effect_duration;
	
	//Speed Totem
	public static final ForgeConfigSpec.ConfigValue<Integer> speed_totem_max_effect_level;
	public static final ForgeConfigSpec.ConfigValue<Integer> speed_totem_effect_duration;
	
	//Water Breathing Totem
	public static final ForgeConfigSpec.ConfigValue<Integer> water_breathing_totem_effect_duration;
	
	//God Totem
	public static final ForgeConfigSpec.ConfigValue<Integer> god_totem_effect_duration;
	public static final ForgeConfigSpec.ConfigValue<Boolean> god_totem_has_slowness;
	public static final ForgeConfigSpec.ConfigValue<Integer> god_totem_slowness_level;
	
	//Jump Totem
	public static final ForgeConfigSpec.ConfigValue<Integer> jump_totem_max_effect_level;
	public static final ForgeConfigSpec.ConfigValue<Integer> jump_totem_effect_duration;
	
	//Fire Resistance Totem
	public static final ForgeConfigSpec.ConfigValue<Integer> fire_resistance_effect_duration;
	
	//Strength Totem
	public static final ForgeConfigSpec.ConfigValue<Integer> strength_totem_max_effect_level;
	public static final ForgeConfigSpec.ConfigValue<Integer> strength_totem_effect_duration;
	
	//Magnet Totem
	public static final ForgeConfigSpec.ConfigValue<Double> magnet_totem_range;
	public static final ForgeConfigSpec.ConfigValue<Boolean> magnet_totem_can_repair;
	
	//Regeneration Totem
	public static final ForgeConfigSpec.ConfigValue<Integer> regeneration_totem_effect_duration;
	
	//Beacon Totem
	public static final ForgeConfigSpec.ConfigValue<Integer> beacon_totem_effect_duration;
	
	//Teleport Totem
	public static final ForgeConfigSpec.ConfigValue<Integer> teleport_totem_cooldown;
	
	static {
		
		BUILDER.push("Config for totems");
		
		//Health Totem
		BUILDER.push("Health Totem");
		
		health_totem_max_effect_level = BUILDER.comment("The maximum number of hearts the Health Totem can give. (HAS TO BE A EVEN NUMBER!) (Default is 6)").define("Max effect level", 6);
		health_totem_effect_duration = BUILDER.comment("The duration (in seconds) of the hearts added by the Health Totem. (Default is 300)").define("Effect Duration", 300);
		
		BUILDER.pop();
		
		//Flight Totem
		BUILDER.push("Flight Totem");
		flight_totem_effect_duration = BUILDER.comment("The duration (in seconds) of the Flight effect added by the Health Totem. (Default is 600)").define("Effect Duration", 600);
		BUILDER.pop();
		
		//Night Vison Totem
		BUILDER.push("Night Vison Totem");
		night_vision_totem_effect_duration = BUILDER.comment("The duration (in seconds) of the Night Vision added by the Night Vision Totem. (Default is 300)").define("Effect Duration", 300);
		BUILDER.pop();
		
		//Fall Totem
		BUILDER.push("Fall Totem");
		fall_totem_effect_duration = BUILDER.comment("The duration (in seconds) of the No Fall Damage Effect added by the Fall Totem. (Default is 120)").define("Effect Duration", 120);
		BUILDER.pop();
		
		//Speed Totem
		BUILDER.push("Speed Totem");
		
		speed_totem_max_effect_level = BUILDER.comment("The maximum level of Speed the Speed Totem can give. (Default is 3)").define("Max Speed level", 3);
		speed_totem_effect_duration = BUILDER.comment("The duration (in seconds) of the Speed added by the Speed Totem. (Default is 300)").define("Effect Duration", 300);
		
		BUILDER.pop();
		
		//Water Breathing Totem
		BUILDER.push("Water Breathing Totem");
		water_breathing_totem_effect_duration = BUILDER.comment("The duration (in seconds) of the Water Breathing added by the Water Breathing Totem. (Default is 300)").define("Effect Duration", 300);
		BUILDER.pop();
		
		//God Totem
		BUILDER.push("God Totem");
		
		god_totem_effect_duration = BUILDER.comment("The duration (in seconds) of the invulnerability added by the God Totem. (Default is 20)").define("Effect Duration", 20);
		god_totem_has_slowness = BUILDER.comment("If the God Totem should add Slowness during the invulnerability time. (Default is true)").define("Has Slowness", true);
		god_totem_slowness_level = BUILDER.comment("(ONLY IF HAS SLOWNEES IS TRUE). The level of Slowness the God Totem adds during the invulnerability time. (Default is 4)").define("Slowness Level", 4);
		
		BUILDER.pop();
		
		//Jump Totem
		BUILDER.push("Jump Totem");
		
		jump_totem_max_effect_level = BUILDER.comment("The maximum level of Jump Boost the Jump Totem can give. (Default is 3)").define("Max Effect Level", 3);
		jump_totem_effect_duration = BUILDER.comment("The duration (in seconds) of the Jump Boost added by the Jump Totem. (Default is 300)").define("Effect Duration", 300);
		
		BUILDER.pop();
		
		//Fire Resistance Totem
		BUILDER.push("Fire Resistance Totem");
		fire_resistance_effect_duration = BUILDER.comment("The duration (in seconds) of the Fire Resistance added by the God Totem. (Default is 300)").define("Effect Duration", 300);
		BUILDER.pop();
		
		//Strength Totem
		BUILDER.push("Strength Totem");
		
		strength_totem_max_effect_level = BUILDER.comment("The maximum level of Strength the Strength Totem can give. (Default is 3)").define("Max Effect Level", 3);
		strength_totem_effect_duration = BUILDER.comment("The duration (in seconds) of the Strength added by the Strength Totem. (Default is 300)").define("Effect Duration", 300);
		
		BUILDER.pop();
		
		//Magnet Totem
		BUILDER.push("Magnet Totem");
		magnet_totem_range = BUILDER.comment("The range of the Magnet Totem. (Default is 7.0) (+2 per upgrade level)").define("Magnet Range", 7.0);
		magnet_totem_can_repair = BUILDER.comment("If the Magnet Totem can be repaired in an avnil. (Default is true)").define("Can Repair", true);
		BUILDER.pop();
		
		//Regeneration Totem
		BUILDER.push("Regeneration Totem");
		regeneration_totem_effect_duration = BUILDER.comment("The duration (in seconds) of the Regeneration added by the Regeneration Totem. (Default is 30)").define("Effect Duration", 30);
		BUILDER.pop();
		
		//Beacon Totem
		BUILDER.push("Beacon Totem");
		beacon_totem_effect_duration = BUILDER.comment("The duration (in seconds) of the Beacon effect added by the Beacon Totem. (Default is 300)").define("Effect Duration", 300);
		BUILDER.pop();
		
		//Teleport Totem
		BUILDER.push("Teleport Totem");
		teleport_totem_cooldown = BUILDER.comment("The cooldown (in seconds) of the Teleport Totem. (Default is 60)").define("Cooldown", 60);
		BUILDER.pop();
		
		//final pop
		BUILDER.pop();
		SPEC = BUILDER.build();
	}
}
