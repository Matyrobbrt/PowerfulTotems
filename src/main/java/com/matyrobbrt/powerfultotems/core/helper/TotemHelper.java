package com.matyrobbrt.powerfultotems.core.helper;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.Level;

public final class TotemHelper {

	// Remove Durability
	public static void damageItem(ItemStack stack, int damage) {
		int DamageValue = stack.getDamageValue() + damage;
		stack.setDamageValue(DamageValue);
		if (DamageValue == stack.getMaxDamage()) {
			stack.shrink(1);
		}
	}

	// Core remove durability
	public static void coreDamageItem(ItemStack stack, int damage, Level worldIn) {
		int dmgValue = stack.getDamageValue();
		if (NBTHelper.getInt(stack, "Core") == 1) {
			for (int i = 1; i <= damage; ++i) {
				int randChanceNumber = worldIn.random.nextInt(10) + 1;
				if (randChanceNumber > 2)
					dmgValue++;
			}
		} else if (NBTHelper.getInt(stack, "Core") == 2) {
			for (int i = 1; i <= damage; ++i) {
				int randChanceNumber = worldIn.random.nextInt(10) + 1;
				if (randChanceNumber > 4)
					dmgValue++;
			}
		} else if (NBTHelper.getInt(stack, "Core") == 3) {
			for (int i = 1; i <= damage; ++i) {
				int randChanceNumber = worldIn.random.nextInt(10) + 1;
				if (randChanceNumber > 6)
					dmgValue++;
			}
		} else {
			dmgValue = dmgValue + damage;
		}
		stack.setDamageValue(dmgValue);
		if (dmgValue >= stack.getMaxDamage()) {
			stack.shrink(1);
		}
	}

	// Core Tooltip
	public static void coreTooltip(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip,
			TooltipFlag flagIn) {
		if (NBTHelper.getInt(stack, "Core") == 1)
			tooltip.add(new TextComponent("\u00A7f" + "Basic Core"));
		else if (NBTHelper.getInt(stack, "Core") == 2)
			tooltip.add(new TextComponent("\u00A7b" + "Advanced Core"));
		else if (NBTHelper.getInt(stack, "Core") == 3)
			tooltip.add(new TextComponent("\u00A7d" + "Reinforced Core"));
	}

	// Enabled Info
	public static void enabledInfo(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip,
			TooltipFlag flagIn) {
		if (NBTHelper.getBoolean(stack, "Enabled")) {
			String activationInfo = "ENABLED";
			tooltip.add(new TextComponent("\u00A76" + activationInfo));
		}
	}

	// Upgrade
	public static void upgrade(ItemStack totem, ItemStack core, int upgradeLevel, Player player) {
		if (upgradeLevel == 1) {
			if (NBTHelper.getInt(totem, "Core") == 1 || NBTHelper.getInt(totem, "Core") == 2
					|| NBTHelper.getInt(totem, "Core") == 3)
				player.sendMessage(new TextComponent("The totem is already Basic!"), player.getUUID());
			else {
				NBTHelper.setInt(totem, "Core", 1);
				core.shrink(1);
				player.sendMessage(
						new TextComponent("The totem has been succesfully upgraded to the Basic level!"),
						player.getUUID());
			}
		} else if (upgradeLevel == 2) {
			if (NBTHelper.getInt(totem, "Core") == 2)
				player.sendMessage(
						new TextComponent("The totem is already " + "\u00A7b" + "Advanced" + "\u00A7f" + "!"),
						player.getUUID());
			else if (NBTHelper.getInt(totem, "Core") == 1) {
				NBTHelper.setInt(totem, "Core", 2);
				core.shrink(1);
				player.sendMessage(new TextComponent("The totem has been succesfully upgraded to the " + "\u00A7b"
						+ "Advanced" + "\u00A7f" + " level!"), player.getUUID());
			} else {
				player.sendMessage(new TextComponent("The totem needs to be Basic!"), player.getUUID());
			}
		} else if (upgradeLevel == 3) {
			if (NBTHelper.getInt(totem, "Core") == 3)
				player.sendMessage(
						new TextComponent("The totem is already " + "\u00A7d" + "Reinforced" + "\u00A7f" + "!"),
						player.getUUID());
			else if (NBTHelper.getInt(totem, "Core") == 2) {
				NBTHelper.setInt(totem, "Core", 3);
				core.shrink(1);
				player.sendMessage(new TextComponent("The totem has been succesfully upgraded to the " + "\u00A7d"
						+ "Reinforced" + "\u00A7f" + " level!"), player.getUUID());
			} else {
				player.sendMessage(
						new TextComponent("The totem needs to be " + "\u00A7b" + "Advanced" + "\u00A7f" + "!"),
						player.getUUID());
			}
		}
	}
	
	public static boolean isBlock(ItemStack stack) {
		return stack.getItem() instanceof BlockItem;
	}

}
