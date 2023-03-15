package com.matyrobbrt.powerfultotems.core.helper;

import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public final class NBTHelper {

	public static boolean getBoolean(ItemStack stack, String key) {
		return stack.hasTag() && getTagCompound(stack).getBoolean(key);
	}

	public static void flipBoolean(ItemStack stack, String key) {
		setBoolean(stack, key, !getBoolean(stack, key));
	}

	public static void setBoolean(ItemStack stack, String key, boolean value) {
		getTagCompound(stack).putBoolean(key, value);
	}

	public static void validateCompound(ItemStack stack) {
		if (!stack.hasTag()) {
			CompoundTag tag = new CompoundTag();
			stack.setTag(tag);
		}
	}

	public static void setString(ItemStack stack, String key, String value) {
		getTagCompound(stack).putString(key, value);
	}

	public static String getString(ItemStack stack, String key) {
		return stack.hasTag() ? getTagCompound(stack).getString(key) : "";
	}

	public static CompoundTag getTagCompound(ItemStack stack) {
		validateCompound(stack);
		return stack.getTag();
	}
	
	public static void setInt(ItemStack stack, String key, int value) {
		getTagCompound(stack).putInt(key, value);
	}
	
	public static int getInt(ItemStack stack, String key) {
		return stack.hasTag() ? getTagCompound(stack).getInt(key) : 0;
	}
	
	public static double getDouble(ItemStack stack, String key) {
		return stack.hasTag() ? getTagCompound(stack).getDouble(key) : 0.0;
	}
	
	public static void setDouble(ItemStack stack, String key, double value) {
		getTagCompound(stack).putDouble(key, value);
	}
	
	public static double getFloat(ItemStack stack, String key) {
		return stack.hasTag() ? getTagCompound(stack).getFloat(key) : 0.0F;
	}
	
	public static void setFloat(ItemStack stack, String key, float value) {
		getTagCompound(stack).putFloat(key, value);
	}

}
