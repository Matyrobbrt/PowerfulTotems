package com.matyrobbrt.powerfultotems.core.helper;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

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
	public static void coreDamageItem(ItemStack stack, int damage, World worldIn) {
		int DamageValue = stack.getDamageValue();
		if (NBTHelper.getInt(stack, "Core") == 1) {
			for (int i = 1; i <= damage; ++i) {
				int randChanceNumber = worldIn.random.nextInt(10) + 1;
				if (randChanceNumber > 2)
					DamageValue++;
			}
		} else if (NBTHelper.getInt(stack, "Core") == 2) {
			for (int i = 1; i <= damage; ++i) {
				int randChanceNumber = worldIn.random.nextInt(10) + 1;
				if (randChanceNumber > 4)
					DamageValue++;
			}
		} else if (NBTHelper.getInt(stack, "Core") == 3) {
			for (int i = 1; i <= damage; ++i) {
				int randChanceNumber = worldIn.random.nextInt(10) + 1;
				if (randChanceNumber > 6)
					DamageValue++;
			}
		} else {
			DamageValue = DamageValue + damage;
		}
		stack.setDamageValue(DamageValue);
		if (DamageValue >= stack.getMaxDamage()) {
			stack.shrink(1);
		}
	}

	// Core Tooltip
	public static void coreTooltip(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		if (NBTHelper.getInt(stack, "Core") == 1)
			tooltip.add(new StringTextComponent("\u00A7f" + "Basic Core"));
		else if (NBTHelper.getInt(stack, "Core") == 2)
			tooltip.add(new StringTextComponent("\u00A7b" + "Advanced Core"));
		else if (NBTHelper.getInt(stack, "Core") == 3)
			tooltip.add(new StringTextComponent("\u00A7d" + "Reinforced Core"));
	}

	// Enabled Info
	public static void enabledInfo(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		if (NBTHelper.getBoolean(stack, "Enabled") == true) {
			String activationInfo = "ENABLED";
			tooltip.add(new StringTextComponent("\u00A76" + activationInfo));
		}
	}

	// Upgrade
	public static void upgrade(ItemStack totem, ItemStack core, int upgradeLevel, PlayerEntity player) {
		if (upgradeLevel == 1) {
			if (NBTHelper.getInt(totem, "Core") == 1 || NBTHelper.getInt(totem, "Core") == 2
					|| NBTHelper.getInt(totem, "Core") == 3)
				player.sendMessage(new StringTextComponent("The totem is already Basic!"), player.getUUID());
			else {
				NBTHelper.setInt(totem, "Core", 1);
				core.shrink(1);
				player.sendMessage(
						new StringTextComponent("The totem has been succesfully upgraded to the Basic level!"),
						player.getUUID());
			}
		} else if (upgradeLevel == 2) {
			if (NBTHelper.getInt(totem, "Core") == 2)
				player.sendMessage(
						new StringTextComponent("The totem is already " + "\u00A7b" + "Advanced" + "\u00A7f" + "!"),
						player.getUUID());
			else if (NBTHelper.getInt(totem, "Core") == 1) {
				NBTHelper.setInt(totem, "Core", 2);
				core.shrink(1);
				player.sendMessage(new StringTextComponent("The totem has been succesfully upgraded to the " + "\u00A7b"
						+ "Advanced" + "\u00A7f" + " level!"), player.getUUID());
			} else {
				player.sendMessage(new StringTextComponent("The totem needs to be Basic!"), player.getUUID());
			}
		} else if (upgradeLevel == 3) {
			if (NBTHelper.getInt(totem, "Core") == 3)
				player.sendMessage(
						new StringTextComponent("The totem is already " + "\u00A7d" + "Reinforced" + "\u00A7f" + "!"),
						player.getUUID());
			else if (NBTHelper.getInt(totem, "Core") == 2) {
				NBTHelper.setInt(totem, "Core", 3);
				core.shrink(1);
				player.sendMessage(new StringTextComponent("The totem has been succesfully upgraded to the " + "\u00A7d"
						+ "Reinforced" + "\u00A7f" + " level!"), player.getUUID());
			} else {
				player.sendMessage(
						new StringTextComponent("The totem needs to be " + "\u00A7b" + "Advanced" + "\u00A7f" + "!"),
						player.getUUID());
			}
		}
	}
	
	//Is Block
	public static boolean isBlock(ItemStack stack) {
		Item item = stack.getItem();
		return item instanceof BlockItem;
	}
	
	//Can Block Contain Fluid
	public static boolean canBlockContainFluid(World worldIn, BlockPos posIn, BlockState blockstate) {
		return blockstate.getBlock() instanceof ILiquidContainer
				&& ((ILiquidContainer) blockstate.getBlock()).canPlaceLiquid(worldIn, posIn, blockstate, Fluids.WATER);
	}

}
