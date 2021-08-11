package com.matyrobbrt.powerfultotems.common.item.totem;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.matyrobbrt.powerfultotems.core.helper.TotemHelper;
import com.matyrobbrt.powerfultotems.core.itemgroup.TotemItemGroup;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class TrowelTotem extends Item {

	public TrowelTotem() {
		super(new Item.Properties().stacksTo(1).setNoRepair().tab(TotemItemGroup.TOTEM_ITEM_GROUP).durability(1024)
				.rarity(Rarity.RARE));
	}

	@Override
	public ActionResultType useOn(ItemUseContext context) {
		PlayerEntity player = context.getPlayer();
		Hand hand = context.getHand();
		ItemStack totem_stack = player.getItemInHand(hand);
		World world = context.getLevel();

		List<ItemStack> targets = new ArrayList<>();
		for (int i = 0; i <= 8; i++) {
			ItemStack stack = player.inventory.getItem(i);
			if (TotemHelper.isBlock(stack))
				targets.add(stack);
		}

		if (targets.size() != 0) {
			ItemStack targetItem = targets.get(world.random.nextInt(targets.size()));
			int count = targetItem.getCount();

			ItemUseContext context2 = new ItemUseContext(world, player, hand, targetItem, new BlockRayTraceResult(
					context.getClickLocation(), context.getClickedFace(), context.getClickedPos(), context.isInside()));
			targetItem.useOn(context2);

			if (player.isCreative())
				targetItem.setCount(count);
			else {
				targetItem.setCount(count - 1);
				TotemHelper.coreDamageItem(totem_stack, 1, world);
			}
		}

		return super.useOn(context);
	}

	@Override
	public boolean isFireResistant() {
		return true;
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
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {

		TotemHelper.coreTooltip(stack, worldIn, tooltip, flagIn);

		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}
	
}
