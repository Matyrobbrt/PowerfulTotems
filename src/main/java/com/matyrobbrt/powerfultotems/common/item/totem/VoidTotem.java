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
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class VoidTotem extends Item {

	public VoidTotem() {
		super(new Item.Properties().stacksTo(1).setNoRepair().tab(TotemItemGroup.TOTEM_ITEM_GROUP).durability(2048)
				.rarity(Rarity.RARE));
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity player, Hand hand) {

		ItemStack stack = player.getItemInHand(hand);

		if (!player.isShiftKeyDown()) {
			player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 0.5F,
					NBTHelper.getBoolean(stack, "Enabled") ? 0.5F : 1.0F);

			NBTHelper.flipBoolean(stack, "Enabled");
			NBTHelper.flipBoolean(stack, "IsNotMagnetable");
		} else {
			ItemStack voidStack = player.getOffhandItem();
			NBTHelper.setString(stack, "VoidItem", voidStack.getItem().getRegistryName().toString());
		}

		return new ActionResult<>(ActionResultType.SUCCESS, stack);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {

		if (entity instanceof PlayerEntity && NBTHelper.getBoolean(stack, "Enabled") == true) {
			PlayerEntity player = (PlayerEntity) entity;
			String voidItem = NBTHelper.getString(stack, "VoidItem");

			// Inventory
			for (int i = 0; i <= 35; ++i) {
				if (player.inventory.getItem(i).getItem().getRegistryName().toString().equals(voidItem)) {
					int count = player.inventory.getItem(i).getCount();
					player.inventory.getItem(i).shrink(count);
					TotemHelper.coreDamageItem(stack, count, world);
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
		TotemHelper.enabledInfo(stack, worldIn, tooltip, flagIn);
		if (NBTHelper.getString(stack, "VoidItem") != "")
			tooltip.add(new StringTextComponent("Is voiding " + "\u00A7b" + NBTHelper.getString(stack, "VoidItem")));
		else
			tooltip.add(new StringTextComponent("Is not voiding anythig!"));

		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}

}
