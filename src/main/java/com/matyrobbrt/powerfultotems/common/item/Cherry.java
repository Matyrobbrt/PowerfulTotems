package com.matyrobbrt.powerfultotems.common.item;

import java.util.List;

import javax.annotation.Nullable;

import com.matyrobbrt.powerfultotems.core.itemgroup.TotemItemGroup;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.Level;

public class Cherry extends Item {

	public Cherry() {
		super(new Item.Properties().stacksTo(20).tab(TotemItemGroup.TOTEM_ITEM_GROUP).rarity(Rarity.COMMON)
				.food(new FoodProperties.Builder().alwaysEat().nutrition(6).saturationMod(13.3F).build()));
	}
	
	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entity) {
		entity.heal(1F);
		return super.finishUsingItem(stack, worldIn, entity);
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, InteractionHand hand) {
		
		ItemStack stack = player.getItemInHand(hand);
		
		if (player.getHealth() == player.getMaxHealth()) {
			return InteractionResultHolder.fail(stack);
		}
		
		return super.use(worldIn, player, hand);
	}
	
	
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {

		TextComponent text = new TextComponent("Eat to get half a heart worth of health.");
		tooltip.add(text);
		
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}

}
