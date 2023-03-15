package com.matyrobbrt.powerfultotems.common.events;

import com.matyrobbrt.powerfultotems.PowerfulTotems;
import com.matyrobbrt.powerfultotems.core.config.TotemConfig;
import com.matyrobbrt.powerfultotems.core.init.TotemInit;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = PowerfulTotems.MOD_ID, bus = Bus.FORGE)
public class ModEvents {

    @SubscribeEvent
    public static void anvilUpdate(AnvilUpdateEvent event) {
        if (TotemConfig.magnet_totem_can_repair.get()) {
            if (event.getLeft().getItem() == TotemInit.MAGNET_TOTEM.get() && event.getRight().getItem() == Items.IRON_BLOCK) {
                ItemStack leftStack = event.getLeft();
                ItemStack stack = event.getLeft().copy();
                if (leftStack.getDamageValue() >= 256) {
                    stack.setDamageValue(leftStack.getDamageValue() - 256);
                    event.setMaterialCost(1);
                    event.setCost(2);
                    event.setOutput(stack);
                }
            }
        }
    }
}
