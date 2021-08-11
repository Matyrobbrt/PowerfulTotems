package com.matyrobbrt.powerfultotems.common.item.totem;

import java.util.ArrayList;
import java.util.Random;

import com.google.common.collect.Lists;
import com.matyrobbrt.powerfultotems.core.itemgroup.TotemItemGroup;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

@SuppressWarnings("unused")
public class FireworkTotem extends Item {

	public FireworkTotem() {
		super(new Item.Properties().stacksTo(1).setNoRepair().tab(TotemItemGroup.TOTEM_ITEM_GROUP).durability(256)
				.rarity(Rarity.UNCOMMON));
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

		if (!player.isShiftKeyDown()) {
			FireworkRocketEntity fireworkRocket = new FireworkRocketEntity(worldIn, rocketCreate(), player);
			fireworkRocket.shootFromRotation(player, player.xRot, player.yRot, 0.0F, 1.5F, 1.0F);
			worldIn.addFreshEntity(fireworkRocket);
			damageItem(stack);
		} else if (player.isShiftKeyDown()) {

			ResourceLocation firework_id = Items.FIREWORK_ROCKET.getRegistryName();

			for (int i = 0; i <= 35; ++i) {

				if (player.inventory.getItem(i).getItem().getRegistryName() == firework_id) {
					if (player.inventory.getItem(i).getCount() >= 16 && stack.getDamageValue() >= 16) {
						stack.setDamageValue(stack.getDamageValue() - 16);
						player.inventory.getItem(i).shrink(16);
					} else if (stack.getDamageValue() != 0
							&& player.inventory.getItem(i).getCount() >= stack.getDamageValue()) {
						player.inventory.getItem(i).shrink(stack.getDamageValue());
						stack.setDamageValue(0);
					} else if (stack.getDamageValue() != 0
							&& player.inventory.getItem(i).getCount() <= stack.getDamageValue()) {
						stack.setDamageValue(stack.getDamageValue() - player.inventory.getItem(i).getCount());
						player.inventory.getItem(i).shrink(player.inventory.getItem(i).getCount());

					}
				}

			}

		}

		return super.use(worldIn, player, hand);
	}

	private ItemStack rocketCreate() {

		Random random = new Random();

		ItemStack rocket = new ItemStack(Items.FIREWORK_ROCKET);

		rocket.getOrCreateTagElement("Fireworks");

		CompoundNBT compoundnbt = rocket.getTagElement("Fireworks");

		ItemStack itemstack1 = new ItemStack(Items.FIREWORK_STAR);

		compoundnbt.putByte("Flight", (byte) 1);

		return rocket;

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

}
