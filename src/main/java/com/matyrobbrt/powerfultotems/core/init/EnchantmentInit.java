package com.matyrobbrt.powerfultotems.core.init;

import com.matyrobbrt.powerfultotems.PowerfulTotems;
import com.matyrobbrt.powerfultotems.common.enchantment.GlintEnchant;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentInit {
	public static final DeferredRegister<Enchantment> ENCHANTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, PowerfulTotems.MOD_ID);
	
	public static final RegistryObject<GlintEnchant> GLINT_ENCHANTMENT = ENCHANTS.register("glint", () -> new GlintEnchant(Enchantment.Rarity.VERY_RARE, EquipmentSlotType.MAINHAND));
}
