package com.matyrobbrt.powerfultotems.common.item.totem;

import java.util.List;

import javax.annotation.Nullable;

import com.matyrobbrt.powerfultotems.core.config.TotemConfig;
import com.matyrobbrt.powerfultotems.core.helper.TotemHelper;
import com.matyrobbrt.powerfultotems.core.init.PotionInit;
import com.matyrobbrt.powerfultotems.core.itemgroup.TotemItemGroup;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class FallTotem extends Item {
	
	public FallTotem() {
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
			
			if (!player.hasEffect(PotionInit.NEGATE_FALL_DAMAGE.get())) {
				player.addEffect(new EffectInstance(PotionInit.NEGATE_FALL_DAMAGE.get(), TotemConfig.fall_totem_effect_duration.get()*20, 0, true, true));
				TotemHelper.coreDamageItem(stack, 1, worldIn);
			} else {
				if (worldIn.isClientSide) player.sendMessage(new StringTextComponent("\u00A7b" + "You already negate fall damage!"), player.getUUID());
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

			TotemHelper.coreTooltip(stack, worldIn, tooltip, flagIn);
			StringTextComponent text = new StringTextComponent("\u00A7b" + "Right click to get " + "\u00A76" + "No Fall Damage" + "\u00A7b" + " effect for "  + TotemConfig.fall_totem_effect_duration.get() + " seconds.");
			tooltip.add(text);
			
			super.appendHoverText(stack, worldIn, tooltip, flagIn);
		}
}
