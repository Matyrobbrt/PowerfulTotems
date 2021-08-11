package com.matyrobbrt.powerfultotems.core.init;

import com.matyrobbrt.powerfultotems.PowerfulTotems;
import com.matyrobbrt.powerfultotems.common.effect.Beacon;
import com.matyrobbrt.powerfultotems.common.effect.Flight;
import com.matyrobbrt.powerfultotems.common.effect.NegateFallDamage;

import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionInit {
	
	public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, PowerfulTotems.MOD_ID);
	
	public static final RegistryObject<NegateFallDamage> NEGATE_FALL_DAMAGE = EFFECTS.register("negate_fall_damage", NegateFallDamage::new);
	public static final RegistryObject<Flight> FLIGHT = EFFECTS.register("flight", Flight::new);
	public static final RegistryObject<Beacon> BEACON = EFFECTS.register("beacon", Beacon::new);
	
}
