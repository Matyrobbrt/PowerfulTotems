package com.matyrobbrt.powerfultotems.common.item.totem;

import java.util.ArrayList;
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
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class MagnetTotem extends Item {

	public MagnetTotem() {
		super(new Item.Properties().stacksTo(1).setNoRepair().tab(TotemItemGroup.TOTEM_ITEM_GROUP).durability(2048)
				.rarity(Rarity.RARE));
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity player, Hand hand) {

		ItemStack stack = player.getItemInHand(hand);

		player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 0.5F, NBTHelper.getBoolean(stack, "Enabled") ? 0.5F : 1.0F);

		NBTHelper.flipBoolean(stack, "Enabled");
		NBTHelper.flipBoolean(stack, "IsNotMagnetable");

		return new ActionResult<>(ActionResultType.SUCCESS, stack);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {

		if (entity instanceof PlayerEntity && NBTHelper.getBoolean(stack, "Enabled")) {
			PlayerEntity player = (PlayerEntity) entity;
			double range = TotemConfig.magnet_totem_range.get() + NBTHelper.getInt(stack, "Core") * 2.0;
			ArrayList<ItemEntity> rangeItems = (ArrayList<ItemEntity>) world.getEntitiesOfClass(ItemEntity.class,
					entity.getBoundingBox().inflate(range));
			for (ItemEntity item : rangeItems) {
				if (!item.isAlive() || NBTHelper.getBoolean(item.getItem(), "IsNotMagnetable"))
					continue;

				if (item.getThrower() != null && item.getThrower().equals(entity.getUUID()) && item.hasPickUpDelay())
					continue;

				if (!world.isClientSide()) {
					item.setNoPickUpDelay();
					int damage = item.getItem().getCount();
					Boolean shouldDamage = true;
					if (item.distanceTo(entity) < 1.5F)
						shouldDamage = false;
					item.setPos(entity.getX(), entity.getY(), entity.getZ());
					if (shouldDamage == true)
						if (!player.isCreative())
							TotemHelper.coreDamageItem(stack, damage, world);
				}
			}

			List<ExperienceOrbEntity> xp = world.getEntitiesOfClass(ExperienceOrbEntity.class,
					entity.getBoundingBox().inflate(range));
			for (ExperienceOrbEntity orb : xp) {
				if (!world.isClientSide()) {
					orb.setPos(entity.getX(), entity.getY(), entity.getZ());
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
		return NBTHelper.getBoolean(stack, "Enabled");
	}

	@Override
	public boolean isFireResistant() {
		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {

		TotemHelper.coreTooltip(stack, worldIn, tooltip, flagIn);

		if (NBTHelper.getBoolean(stack, "Enabled") == true) {

			String activationInfo = "ENABLED";

			StringTextComponent text = new StringTextComponent("\u00A76" + activationInfo);
			tooltip.add(text);
		}

		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}

}
