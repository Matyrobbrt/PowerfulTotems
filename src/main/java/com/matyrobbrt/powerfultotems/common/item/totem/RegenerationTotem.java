package com.matyrobbrt.powerfultotems.common.item.totem;

import java.util.List;

import javax.annotation.Nullable;

import com.matyrobbrt.powerfultotems.core.config.TotemConfig;
import com.matyrobbrt.powerfultotems.core.helper.NBTHelper;
import com.matyrobbrt.powerfultotems.core.helper.TotemHelper;
import com.matyrobbrt.powerfultotems.core.itemgroup.TotemItemGroup;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
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

public class RegenerationTotem extends Item {

	public RegenerationTotem() {
		super(new Item.Properties().stacksTo(1).setNoRepair().tab(TotemItemGroup.TOTEM_ITEM_GROUP).durability(6)
				.rarity(Rarity.EPIC));
	}

	private boolean hasCooldown;
	
	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

		ItemStack stack = player.getItemInHand(hand);

		if (!player.getCooldowns().isOnCooldown(this)) {
			if (!player.hasEffect(Effects.REGENERATION)) {
				player.addEffect(new EffectInstance(Effects.REGENERATION,
						TotemConfig.regeneration_totem_effect_duration.get() * 20, NBTHelper.getInt(stack, "Core"),
						true, true));
				TotemHelper.coreDamageItem(stack, 1, world);
				player.getCooldowns().addCooldown(this, 6000);
			} else
				if (world.isClientSide) player.sendMessage(new StringTextComponent("You already have Regeneration!"), player.getUUID());
		}

		return super.use(world, player, hand);
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {
		if (entity instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entity;
			hasCooldown = player.getCooldowns().isOnCooldown(this);
		}
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
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		
		TotemHelper.coreTooltip(stack, worldIn, tooltip, flagIn);
		StringTextComponent text = null;
		
		if (hasCooldown == false)
		text = new StringTextComponent(
				"\u00A7b" + "Right click to get " + "\u00A7d" + "Regeneration" + "\u00A7b" + " effect for "
						+ TotemConfig.regeneration_totem_effect_duration.get() + " seconds.");
		else 
			text = new StringTextComponent("Is on cooldown!");
		tooltip.add(text);

		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}

}
