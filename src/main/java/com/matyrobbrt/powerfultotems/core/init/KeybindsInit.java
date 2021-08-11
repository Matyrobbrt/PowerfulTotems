package com.matyrobbrt.powerfultotems.core.init;

import java.awt.event.KeyEvent;

import com.matyrobbrt.powerfultotems.PowerfulTotems;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class KeybindsInit {
	
	public static KeyBinding activateTotem;
	public static KeyBinding fallTotemKeyBinding;
	public static KeyBinding speedTotemKeyBinding;
	public static KeyBinding waterBreathingTotemKeyBinding;
	public static KeyBinding fireResistanceTotemKeyBinding;
	public static KeyBinding godTotemKeyBinding;
	public static KeyBinding flightTotemKeyBinding;
	public static KeyBinding fireworkTotemKeyBinding;
	public static KeyBinding magnetTotemKeyBinding;
	public static KeyBinding upgradeKeyBinding;
	
	public static void register(final FMLClientSetupEvent event) {
		activateTotem = create("activate_totem", KeyEvent.VK_Z);
		fallTotemKeyBinding = create("fall_totem_keybinding", KeyEvent.VK_G);
		speedTotemKeyBinding = create("speed_totem_keybinding", KeyEvent.VK_J);
		flightTotemKeyBinding = create("flight_totem_keybinding", KeyEvent.VK_X);
		fireworkTotemKeyBinding = create("firework_totem_keybinding", KeyEvent.VK_C);
		godTotemKeyBinding = create("god_totem_keybinding", KeyEvent.VK_H);
		waterBreathingTotemKeyBinding = create("water_breathing_totem_keybinding", KeyEvent.VK_B);
		fireResistanceTotemKeyBinding = create("fire_resistance_totem_keybinding", KeyEvent.VK_R);
		magnetTotemKeyBinding = create("magnet_totem_keybinding", KeyEvent.VK_M);
		upgradeKeyBinding = create("upgrade_keybinding", KeyEvent.VK_U);
		
		ClientRegistry.registerKeyBinding(activateTotem);
		ClientRegistry.registerKeyBinding(fallTotemKeyBinding);
		ClientRegistry.registerKeyBinding(speedTotemKeyBinding);
		ClientRegistry.registerKeyBinding(waterBreathingTotemKeyBinding);
		ClientRegistry.registerKeyBinding(flightTotemKeyBinding);
		ClientRegistry.registerKeyBinding(godTotemKeyBinding);
		ClientRegistry.registerKeyBinding(fireResistanceTotemKeyBinding);
		ClientRegistry.registerKeyBinding(fireworkTotemKeyBinding);
		ClientRegistry.registerKeyBinding(magnetTotemKeyBinding);
		ClientRegistry.registerKeyBinding(upgradeKeyBinding);
	}
	
	private static KeyBinding create(String name, int key) {
		return new KeyBinding("key." + PowerfulTotems.MOD_ID + "." + name , key, "key.category" + PowerfulTotems.MOD_ID);
	}
	
}
