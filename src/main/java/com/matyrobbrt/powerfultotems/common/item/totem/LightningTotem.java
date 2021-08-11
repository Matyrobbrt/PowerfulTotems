package com.matyrobbrt.powerfultotems.common.item.totem;

import com.matyrobbrt.powerfultotems.core.helper.TotemHelper;
import com.matyrobbrt.powerfultotems.core.itemgroup.TotemItemGroup;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LightningTotem extends Item {

	public LightningTotem() {
		super(new Item.Properties().stacksTo(1).setNoRepair().tab(TotemItemGroup.TOTEM_ITEM_GROUP).durability(5)
				.rarity(Rarity.EPIC));
	}
	
	@Override
	public ActionResultType useOn(ItemUseContext context) {
		PlayerEntity player = context.getPlayer();
		Hand hand = context.getHand();
		ItemStack stack = player.getItemInHand(hand);
		World world = context.getLevel();
		BlockPos blockPos = context.getClickedPos();
		
		double x = blockPos.getX();
		double y = blockPos.getY();
		double z = blockPos.getZ();
		
		LightningBoltEntity lightning = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, world);
		lightning.setPos(x, y, z);
		world.addFreshEntity(lightning);
		if (!player.isCreative()) TotemHelper.damageItem(stack, 1);
		
		return super.useOn(context);
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
		return true;
	}

}
