package com.matyrobbrt.powerfultotems.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class GlintEnchant extends Enchantment{

	   public GlintEnchant(Enchantment.Rarity rarityIn, EquipmentSlotType... slots) {
		      super(rarityIn, EnchantmentType.BREAKABLE, slots);
	}

}
