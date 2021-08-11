package com.matyrobbrt.powerfultotems.common.events;

import com.matyrobbrt.powerfultotems.PowerfulTotems;
import com.matyrobbrt.powerfultotems.core.helper.TotemHelper;
import com.matyrobbrt.powerfultotems.core.init.PotionInit;
import com.matyrobbrt.powerfultotems.core.init.TotemInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = PowerfulTotems.MOD_ID, bus = Bus.FORGE)
public class PlayerEvents {

	@SubscribeEvent
	public static void handleGluttonyTotem(LivingEntityUseItemEvent.Tick event) {

		if (event.getEntityLiving() instanceof PlayerEntity) {

			final ResourceLocation gluttony_totem_id = TotemInit.GLUTTONY_TOTEM.get().getRegistryName();
			final PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			final ItemStack using = event.getItem();
			ItemStack stack = null;
			World worldIn = player.level;

			if (using.isEdible() && using.getUseDuration() <= 1000) {
				// Off Hand
				if (player.getOffhandItem().getItem().getRegistryName() == gluttony_totem_id) {
					stack = player.getOffhandItem();
				} else {
					// Inventory
					for (int i = 0; i <= 35; ++i) {
						ResourceLocation idLocation = player.inventory.getItem(i).getItem().getRegistryName();
						if (idLocation == gluttony_totem_id) {
							stack = player.inventory.getItem(i);
							break;
						}
					}
				}
				if (stack != null) {
					event.setDuration(1);
					TotemHelper.coreDamageItem(stack, 1, worldIn);
				}
			}
		}

	}

	@SubscribeEvent
	public static void handleFlightEffect(PlayerTickEvent event) {

		PlayerEntity player = event.player;

		if (player.hasEffect(PotionInit.FLIGHT.get())) {
			if (player.abilities.mayfly == false)
				player.abilities.mayfly = true;
		} else if (!player.isCreative())
			player.abilities.mayfly = false;

	}

}
