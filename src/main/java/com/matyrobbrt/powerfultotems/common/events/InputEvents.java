package com.matyrobbrt.powerfultotems.common.events;

import com.matyrobbrt.powerfultotems.PowerfulTotems;
import com.matyrobbrt.powerfultotems.core.init.KeybindsInit;
import com.matyrobbrt.powerfultotems.core.init.TotemInit;
import com.matyrobbrt.powerfultotems.core.network.TotemNetwork;
import com.matyrobbrt.powerfultotems.core.network.message.MagnetTotemMessage;
import com.matyrobbrt.powerfultotems.core.network.message.UpgradeMessage;
import com.matyrobbrt.powerfultotems.core.network.message.UseTotemMessage;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = PowerfulTotems.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class InputEvents {
    
    @SubscribeEvent
    public static void onKeyPress(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.screen != null)
            return;
        if (event.getAction() == GLFW.GLFW_PRESS) {
            onInput(mc, event.getKey(), event.getAction());
        }
    }

    private static void onInput(Minecraft mc, int key, int action) {
        if (KeybindsInit.fallTotemKeyBinding.isDown()) {
            TotemNetwork.CHANNEL.sendToServer(new UseTotemMessage(TotemInit.FALL_TOTEM.get()));
        }

        if (KeybindsInit.speedTotemKeyBinding.isDown()) {
            TotemNetwork.CHANNEL.sendToServer(new UseTotemMessage(TotemInit.SPEED_TOTEM.get()));
        }

        if (KeybindsInit.waterBreathingTotemKeyBinding.isDown()) {
            TotemNetwork.CHANNEL.sendToServer(new UseTotemMessage(TotemInit.WATER_BREATHING_TOTEM.get()));
        }

        if (KeybindsInit.godTotemKeyBinding.isDown()) {
            TotemNetwork.CHANNEL.sendToServer(new UseTotemMessage(TotemInit.GOD_TOTEM.get()));
        }

        if (KeybindsInit.fireResistanceTotemKeyBinding.isDown()) {
            TotemNetwork.CHANNEL.sendToServer(new UseTotemMessage(TotemInit.FIRE_RESISTANCE_TOTEM.get()));
        }

        if (KeybindsInit.flightTotemKeyBinding.isDown()) {
            TotemNetwork.CHANNEL.sendToServer(new UseTotemMessage(TotemInit.FLIGHT_TOTEM.get()));
        }

        if (KeybindsInit.fireworkTotemKeyBinding.isDown()) {
            TotemNetwork.CHANNEL.sendToServer(new UseTotemMessage(TotemInit.FIREWORK_TOTEM.get()));
        }

        if (KeybindsInit.upgradeKeyBinding.isDown()) {
            TotemNetwork.CHANNEL.sendToServer(new UpgradeMessage(key));
        }

        if (KeybindsInit.magnetTotemKeyBinding.isDown()) {
            TotemNetwork.CHANNEL.sendToServer(new MagnetTotemMessage());
        }

    }
}
