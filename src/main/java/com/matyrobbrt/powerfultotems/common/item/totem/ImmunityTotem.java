package com.matyrobbrt.powerfultotems.common.item.totem;

import java.util.List;

import javax.annotation.Nullable;

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
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class ImmunityTotem extends Item {

	public ImmunityTotem() {
		super(new Item.Properties().stacksTo(1).setNoRepair().tab(TotemItemGroup.TOTEM_ITEM_GROUP).durability(128)
				.rarity(Rarity.EPIC));
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity player, Hand hand) {

		ItemStack stack = player.getItemInHand(hand);

		if (stack.getTag().getBoolean("Rolled") == false) {

			int randNumber = worldIn.random.nextInt(12) + 1;
			String immunity = null;
			int immunityCode = 0;

			if (randNumber == 1) {
				immunity = "Wither";
				immunityCode = 10;
			}
			else if (randNumber == 2) {
				immunity = "Mining Fatigue";
				immunityCode = 11;
			}
			else if (randNumber == 3) {
				immunity = "Levitation";
				immunityCode = 12;
			}
			else if (randNumber == 4) {
				immunity = "Weakness";
				immunityCode = 13;
			}
			else if (randNumber == 5) {
				immunity = "Blindness";
				immunityCode = 14;
			}
			else if (randNumber > 5 && randNumber <= 7) {
				immunity = "Slowness";
				immunityCode = 15;
			}
			else if (randNumber > 7 && randNumber <= 9) {
				immunity = "Poison";
				immunityCode = 16;
			}
			else if (randNumber > 9 && randNumber <= 12) {
				immunity = "Hunger";
				immunityCode = 17;
			}

			NBTHelper.setString(stack, "ImmunityEffect", immunity);
			NBTHelper.setBoolean(stack, "Rolled", true);
			NBTHelper.setInt(stack, "CustomModelData", immunityCode);

		}
		return super.use(worldIn, player, hand);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {

		if (entity instanceof PlayerEntity) {

			PlayerEntity player = (PlayerEntity) entity;

			if (NBTHelper.getString(stack, "ImmunityEffect") == "Hunger") {
				if (player.hasEffect(Effects.HUNGER)) {
					player.removeEffect(Effects.HUNGER);
					TotemHelper.coreDamageItem(stack, 1, world);
				}
			} else if (NBTHelper.getString(stack, "ImmunityEffect") == "Poison") {
				if (player.hasEffect(Effects.POISON)) {
					player.removeEffect(Effects.POISON);
					TotemHelper.coreDamageItem(stack, 1, world);
				}
			} else if (NBTHelper.getString(stack, "ImmunityEffect") == "Slowness") {
				if (player.hasEffect(Effects.MOVEMENT_SLOWDOWN)) {
					player.removeEffect(Effects.MOVEMENT_SLOWDOWN);
					TotemHelper.coreDamageItem(stack, 1, world);
				}
			} else if (NBTHelper.getString(stack, "ImmunityEffect") == "Blindness") {
				if (player.hasEffect(Effects.BLINDNESS)) {
					player.removeEffect(Effects.BLINDNESS);
					TotemHelper.coreDamageItem(stack, 1, world);
				}
			} else if (NBTHelper.getString(stack, "ImmunityEffect") == "Weakness") {
				if (player.hasEffect(Effects.WEAKNESS)) {
					player.removeEffect(Effects.WEAKNESS);
					TotemHelper.coreDamageItem(stack, 1, world);
				}
			} else if (NBTHelper.getString(stack, "ImmunityEffect") == "Levitation") {
				if (player.hasEffect(Effects.LEVITATION)) {
					player.removeEffect(Effects.LEVITATION);
					TotemHelper.coreDamageItem(stack, 1, world);
				}
			} else if (NBTHelper.getString(stack, "ImmunityEffect") == "Mining Fatigue") {
				if (player.hasEffect(Effects.DIG_SLOWDOWN)) {
					player.removeEffect(Effects.DIG_SLOWDOWN);
					TotemHelper.coreDamageItem(stack, 1, world);
				}
			} else if (NBTHelper.getString(stack, "ImmunityEffect") == "Wither") {
				if (player.hasEffect(Effects.WITHER)) {
					player.removeEffect(Effects.WITHER);
					TotemHelper.coreDamageItem(stack, 1, world);
				}
			}
		}
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
		return NBTHelper.getBoolean(stack, "Rolled");
	}

	@Override
	public boolean isFireResistant() {
		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {

		StringTextComponent text = null;

		TotemHelper.coreTooltip(stack, worldIn, tooltip, flagIn);

		if (NBTHelper.getBoolean(stack, "Rolled") == true) {
			text = new StringTextComponent("Grands immunity to " + "\u00A76"
					+ NBTHelper.getString(stack, "ImmunityEffect") + "\u00A7f" + " effect.");
			tooltip.add(text);
		} else {
			text = new StringTextComponent("Right click to roll.");

			tooltip.add(text);
		}

		super.appendHoverText(stack, worldIn, tooltip, flagIn);

	}

}
