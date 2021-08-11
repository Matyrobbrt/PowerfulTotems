package com.matyrobbrt.powerfultotems.common.item.totem;

import java.util.List;

import javax.annotation.Nullable;

import com.matyrobbrt.powerfultotems.core.config.TotemConfig;
import com.matyrobbrt.powerfultotems.core.itemgroup.TotemItemGroup;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class NightVisionTotem extends Item {

	public NightVisionTotem() {
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
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity player, Hand hand) {
		
		ItemStack stack = player.getItemInHand(hand);
		
		if (!player.hasEffect(Effects.NIGHT_VISION)) {
			player.addEffect(new EffectInstance(Effects.NIGHT_VISION, TotemConfig.night_vision_totem_effect_duration.get()*20, 0, true, true));
			damageItem(stack);
		} else {
			if (worldIn.isClientSide) player.sendMessage(new StringTextComponent("\u00A7b" + "You already have night vision!"), player.getUUID());
		}

		return super.use(worldIn, player, hand);
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {

		if (enchantment.isAllowedOnBooks()) {
			return false;
		}

		if (enchantment.isCompatibleWith(Enchantments.MENDING)) {
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
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

		StringTextComponent text = new StringTextComponent("\u00A7b" + "Right click to get " + "\u00A76" + "Night Vision" + "\u00A7b" + " effect for " + TotemConfig.night_vision_totem_effect_duration.get() + " seconds.");
		tooltip.add(text);
		
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}
	
}
