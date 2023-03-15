package com.matyrobbrt.powerfultotems.common.item;

import com.matyrobbrt.powerfultotems.core.itemgroup.TotemItemGroup;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;

public class BrokenGoldenApple extends Item {

	public BrokenGoldenApple() {
		super(new Item.Properties().stacksTo(16).tab(TotemItemGroup.TOTEM_ITEM_GROUP).rarity(Rarity.COMMON));
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, InteractionHand hand) {
		
		ItemStack stack = player.getItemInHand(hand);
		int randNumber = worldIn.random.nextInt(5) + 1;
		
		if (randNumber == 4) {
			player.drop(new ItemStack(Items.ENCHANTED_GOLDEN_APPLE), true);
			stack.shrink(1);
		} else {
			stack.shrink(1);
		}
		
		return super.use(worldIn, player, hand);
	}
	
	@Override
	public boolean isFoil(ItemStack stack) {
		return true;
	}
	
}
