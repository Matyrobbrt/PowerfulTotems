package com.matyrobbrt.powerfultotems.core.init;

import com.matyrobbrt.powerfultotems.PowerfulTotems;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.awt.event.KeyEvent;

public class KeybindsInit {
	
	public static KeyMapping fallTotemKeyBinding;
	public static KeyMapping speedTotemKeyBinding;
	public static KeyMapping waterBreathingTotemKeyBinding;
	public static KeyMapping fireResistanceTotemKeyBinding;
	public static KeyMapping godTotemKeyBinding;
	public static KeyMapping flightTotemKeyBinding;
	public static KeyMapping fireworkTotemKeyBinding;
	public static KeyMapping magnetTotemKeyBinding;
	public static KeyMapping upgradeKeyBinding;
	
	public static void register(final FMLClientSetupEvent event) {
		fallTotemKeyBinding = create("fall_totem_keybinding", KeyEvent.VK_G);
		speedTotemKeyBinding = create("speed_totem_keybinding", KeyEvent.VK_J);
		flightTotemKeyBinding = create("flight_totem_keybinding", KeyEvent.VK_X);
		fireworkTotemKeyBinding = create("firework_totem_keybinding", KeyEvent.VK_C);
		godTotemKeyBinding = create("god_totem_keybinding", KeyEvent.VK_H);
		waterBreathingTotemKeyBinding = create("water_breathing_totem_keybinding", KeyEvent.VK_B);
		fireResistanceTotemKeyBinding = create("fire_resistance_totem_keybinding", KeyEvent.VK_R);
		magnetTotemKeyBinding = create("magnet_totem_keybinding", KeyEvent.VK_M);
		upgradeKeyBinding = create("upgrade_keybinding", KeyEvent.VK_U);

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
	
	private static KeyMapping create(String name, int key) {
		return new KeyMapping("key." + PowerfulTotems.MOD_ID + "." + name , key, "key.category" + PowerfulTotems.MOD_ID);
	}
	
}
