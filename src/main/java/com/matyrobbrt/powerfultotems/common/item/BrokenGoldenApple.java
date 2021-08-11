package com.matyrobbrt.powerfultotems.common.item;

import com.matyrobbrt.powerfultotems.core.itemgroup.TotemItemGroup;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BrokenGoldenApple extends Item {

	public BrokenGoldenApple() {
		super(new Item.Properties().stacksTo(16).tab(TotemItemGroup.TOTEM_ITEM_GROUP).rarity(Rarity.COMMON));
	}
	
	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity player, Hand hand) {
		
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
