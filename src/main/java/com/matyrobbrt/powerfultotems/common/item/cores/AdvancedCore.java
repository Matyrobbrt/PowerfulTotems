package com.matyrobbrt.powerfultotems.common.item.cores;

import com.matyrobbrt.powerfultotems.core.itemgroup.TotemItemGroup;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class AdvancedCore extends Item {

	public AdvancedCore() {
		super(new Item.Properties().tab(TotemItemGroup.TOTEM_ITEM_GROUP).rarity(Rarity.RARE));
	}

}
