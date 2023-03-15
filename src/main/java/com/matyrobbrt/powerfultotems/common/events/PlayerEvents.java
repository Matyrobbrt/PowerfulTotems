package com.matyrobbrt.powerfultotems.common.events;

import com.matyrobbrt.powerfultotems.PowerfulTotems;
import com.matyrobbrt.powerfultotems.core.helper.TotemHelper;
import com.matyrobbrt.powerfultotems.core.init.PotionInit;
import com.matyrobbrt.powerfultotems.core.init.TotemInit;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = PowerfulTotems.MOD_ID, bus = Bus.FORGE)
public class PlayerEvents {

    @SubscribeEvent
    public static void handleGluttonyTotem(LivingEntityUseItemEvent.Tick event) {
        if (event.getEntityLiving() instanceof Player player) {
            final ItemStack using = event.getItem();
            ItemStack stack = null;
            Level worldIn = player.level;

            if (using.isEdible() && using.getUseDuration() <= 1000) {
                // Off Hand
                if (player.getOffhandItem().getItem() == TotemInit.GLUTTONY_TOTEM.get()) {
                    stack = player.getOffhandItem();
                } else {
                    // Inventory
                    for (int i = 0; i < Inventory.INVENTORY_SIZE; ++i) {
                        final ItemStack stack1 = player.getInventory().getItem(i);
                        if (stack1.is(TotemInit.GLUTTONY_TOTEM.get())) {
                            stack = stack1;
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
        final Player player = event.player;
        if (player.hasEffect(PotionInit.FLIGHT.get())) {
            if (!player.getAbilities().mayfly)
                player.getAbilities().mayfly = true;
        } else if (!player.isCreative())
            player.getAbilities().mayfly = false;
    }

}
