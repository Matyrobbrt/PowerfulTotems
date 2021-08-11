package com.matyrobbrt.powerfultotems.core.jei;

import com.matyrobbrt.powerfultotems.core.config.TotemConfig;
import com.matyrobbrt.powerfultotems.core.init.TotemInit;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

@JeiPlugin
public class DescriptionPlugin implements IModPlugin {

	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation("powerfultotems", "default");
	}

	@Override
	public void registerRecipes(IRecipeRegistration registry) {
		IIngredientManager ingredientManager = registry.getIngredientManager();

		IIngredientType<ItemStack> itemType = ingredientManager.getIngredientType(ItemStack.class);

		registry.addIngredientInfo(new ItemStack(TotemInit.MAGNET_TOTEM.get()), itemType,
				new StringTextComponent("This totem attracts items in a range of "
						+ TotemConfig.magnet_totem_range.get() + " blocks. (+2 blocks per core)"));

	}

}
