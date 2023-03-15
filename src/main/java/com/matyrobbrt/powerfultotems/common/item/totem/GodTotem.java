package com.matyrobbrt.powerfultotems.common.item.totem;

import java.util.List;

import javax.annotation.Nullable;

import com.matyrobbrt.powerfultotems.core.config.TotemConfig;
import com.matyrobbrt.powerfultotems.core.helper.NBTHelper;
import com.matyrobbrt.powerfultotems.core.itemgroup.TotemItemGroup;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.Level;

public class GodTotem extends BaseTotem {

    public GodTotem() {
        super(new Item.Properties().stacksTo(1).setNoRepair().tab(TotemItemGroup.TOTEM_ITEM_GROUP).durability(3)
                .rarity(Rarity.EPIC));
    }

    @Override
    public void use(Level world, Player player, ItemStack stack) {
        if (!player.hasEffect(MobEffects.DAMAGE_RESISTANCE)) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,
                    TotemConfig.god_totem_effect_duration.get() * 20, 4, true, true));
            if (TotemConfig.god_totem_has_slowness.get()) {
                player.addEffect(
                        new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, TotemConfig.god_totem_effect_duration.get() * 20,
                                TotemConfig.god_totem_slowness_level.get() - 1, true, true));
            }
            stack.hurtAndBreak(1, player, e -> {
            });
        } else if (player.hasEffect(MobEffects.DAMAGE_RESISTANCE)
                && player.getEffect(MobEffects.DAMAGE_RESISTANCE).getAmplifier() != 5) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE,
                    TotemConfig.god_totem_effect_duration.get() * 20, 4, true, true));
            if (TotemConfig.god_totem_has_slowness.get()) {
                player.addEffect(
                        new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, TotemConfig.god_totem_effect_duration.get() * 20,
                                TotemConfig.god_totem_slowness_level.get() - 1, true, true));
            }
        }

        NBTHelper.setBoolean(stack, "IsNotMagnetable", true);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isFireResistant() {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip,
                                TooltipFlag flagIn) {

        TextComponent text = new TextComponent("\u00A7d" + "Right click to be " + "\u00A76" + "Invulnerable"
                + "\u00A7d" + " for " + TotemConfig.god_totem_effect_duration.get() + " seconds.");
        tooltip.add(text);

        super.appendHoverText(stack, worldIn, tooltip, flagIn);

    }
}
