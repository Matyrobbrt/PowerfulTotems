package com.matyrobbrt.powerfultotems.common.item.cores;

import com.matyrobbrt.powerfultotems.core.itemgroup.TotemItemGroup;

import net.minecraft.item.Item;
import net.minecraft.item.Rarity;

public class AdvancedCore extends Item {

	public AdvancedCore() {
		super(new Item.Properties().tab(TotemItemGroup.TOTEM_ITEM_GROUP).rarity(Rarity.RARE));
	}

}
