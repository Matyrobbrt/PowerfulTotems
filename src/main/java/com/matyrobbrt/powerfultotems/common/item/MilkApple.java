package com.matyrobbrt.powerfultotems.common.item;

import java.util.List;

import javax.annotation.Nullable;

import com.matyrobbrt.powerfultotems.core.itemgroup.TotemItemGroup;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class MilkApple extends Item {

	public MilkApple() {
		super(new Item.Properties().tab(TotemItemGroup.TOTEM_ITEM_GROUP).rarity(Rarity.COMMON)
				.food(new Food.Builder().alwaysEat().nutrition(6).saturationMod(13.3F).build()));
	}
	
	@Override
	public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entity) {
		entity.removeAllEffects();
		return super.finishUsingItem(stack, worldIn, entity);
	}
	
	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity player, Hand hand) {
		
		ItemStack stack = player.getItemInHand(hand);
		
		if (player.getActiveEffects().toString() == "[]") {
			return ActionResult.fail(stack);
		}
		
		return super.use(worldIn, player, hand);
	}
	
	
	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

		StringTextComponent text = new StringTextComponent("Eat to clear all effects!");
		tooltip.add(text);
		
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}
	
}
