package com.matyrobbrt.powerfultotems.common.item.totem;

import java.util.List;

import javax.annotation.Nullable;

import com.matyrobbrt.powerfultotems.core.config.TotemConfig;
import com.matyrobbrt.powerfultotems.core.itemgroup.TotemItemGroup;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.Level;

public class StrengthTotem extends Item {

	public StrengthTotem() {
		super(new Item.Properties().stacksTo(1).setNoRepair().tab(TotemItemGroup.TOTEM_ITEM_GROUP).durability(8)
				.rarity(Rarity.RARE));
	}
	
	// Remove Durability
	public static void damageItem(ItemStack stack) {
		int DamageValue = stack.getDamageValue() + 1;
		stack.setDamageValue(DamageValue);
		if (DamageValue == stack.getMaxDamage()) {
			stack.shrink(1);
		}
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		
		ItemStack stack = player.getItemInHand(hand);
		
		if (player.hasEffect(MobEffects.DAMAGE_BOOST) == true && player.getEffect(MobEffects.DAMAGE_BOOST)
				.getAmplifier() < TotemConfig.strength_totem_max_effect_level.get() - 1) {
			int level = player.getEffect(MobEffects.DAMAGE_BOOST).getAmplifier() + 1;
			player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,
					TotemConfig.strength_totem_effect_duration.get() * 20, level, true, true));
			damageItem(stack);
		} else if (player.hasEffect(MobEffects.DAMAGE_BOOST) == false) {
			player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,
					TotemConfig.strength_totem_effect_duration.get() * 20, 0, true, true));
			damageItem(stack);
		} else {
			if (world.isClientSide) player.sendMessage(new TextComponent("\u00A7b" + "You have reached the maximum level of Strength!"),
					player.getUUID());
		}
		return super.use(world, player, hand);
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {

		if (enchantment.isAllowedOnBooks()) {
			return false;
		}

		if (!enchantment.isCompatibleWith(Enchantments.MENDING)) {
			return false;
		}

		return false;
	}
	
	@Override
	public boolean isFoil(ItemStack stack) {
		return true;
	}
	
	@Override
	public boolean isFireResistant() {
		return true;
	}
	
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {

		TextComponent text = new TextComponent("\u00A7b" + "Right click to get " + "\u00A76" + "Strength" + "\u00A7b" + " effect for " + TotemConfig.strength_totem_effect_duration.get() + " seconds.");
		tooltip.add(text);
		
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}

}
