package com.matyrobbrt.powerfultotems.core.itemgroup;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class TotemItemGroup extends ItemGroup {
	
	public static final TotemItemGroup TOTEM_ITEM_GROUP = new TotemItemGroup(TABS.length, "powerful_totems");

	public TotemItemGroup(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(Items.TOTEM_OF_UNDYING);
	}

}
