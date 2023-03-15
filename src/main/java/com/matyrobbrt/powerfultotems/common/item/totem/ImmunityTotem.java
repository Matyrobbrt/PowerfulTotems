package com.matyrobbrt.powerfultotems.common.item.totem;

import java.util.List;

import javax.annotation.Nullable;

import com.matyrobbrt.powerfultotems.core.helper.NBTHelper;
import com.matyrobbrt.powerfultotems.core.helper.TotemHelper;
import com.matyrobbrt.powerfultotems.core.itemgroup.TotemItemGroup;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.Level;

public class ImmunityTotem extends Item {

    public ImmunityTotem() {
        super(new Item.Properties().stacksTo(1).setNoRepair().tab(TotemItemGroup.TOTEM_ITEM_GROUP).durability(128)
                .rarity(Rarity.EPIC));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, InteractionHand hand) {

        ItemStack stack = player.getItemInHand(hand);

        if (!stack.getOrCreateTag().getBoolean("Rolled")) {

            int randNumber = worldIn.random.nextInt(12) + 1;
            String immunity;
            int immunityCode;

            if (randNumber == 1) {
                immunity = "Wither";
                immunityCode = 10;
            } else if (randNumber == 2) {
                immunity = "Mining Fatigue";
                immunityCode = 11;
            } else if (randNumber == 3) {
                immunity = "Levitation";
                immunityCode = 12;
            } else if (randNumber == 4) {
                immunity = "Weakness";
                immunityCode = 13;
            } else if (randNumber == 5) {
                immunity = "Blindness";
                immunityCode = 14;
            } else if (randNumber <= 7) {
                immunity = "Slowness";
                immunityCode = 15;
            } else if (randNumber <= 9) {
                immunity = "Poison";
                immunityCode = 16;
            } else {
                immunity = "Hunger";
                immunityCode = 17;
            }

            NBTHelper.setString(stack, "ImmunityEffect", immunity);
            NBTHelper.setBoolean(stack, "Rolled", true);
            NBTHelper.setInt(stack, "CustomModelData", immunityCode);

        }
        return super.use(worldIn, player, hand);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean isSelected) {
        if (entity instanceof Player player) {
            if (NBTHelper.getString(stack, "ImmunityEffect").equals("Hunger")) {
                if (player.hasEffect(MobEffects.HUNGER)) {
                    player.removeEffect(MobEffects.HUNGER);
                    TotemHelper.coreDamageItem(stack, 1, world);
                }
            } else if (NBTHelper.getString(stack, "ImmunityEffect").equals("Poison")) {
                if (player.hasEffect(MobEffects.POISON)) {
                    player.removeEffect(MobEffects.POISON);
                    TotemHelper.coreDamageItem(stack, 1, world);
                }
            } else if (NBTHelper.getString(stack, "ImmunityEffect").equals("Slowness")) {
                if (player.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                    player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
                    TotemHelper.coreDamageItem(stack, 1, world);
                }
            } else if (NBTHelper.getString(stack, "ImmunityEffect").equals("Blindness")) {
                if (player.hasEffect(MobEffects.BLINDNESS)) {
                    player.removeEffect(MobEffects.BLINDNESS);
                    TotemHelper.coreDamageItem(stack, 1, world);
                }
            } else if (NBTHelper.getString(stack, "ImmunityEffect").equals("Weakness")) {
                if (player.hasEffect(MobEffects.WEAKNESS)) {
                    player.removeEffect(MobEffects.WEAKNESS);
                    TotemHelper.coreDamageItem(stack, 1, world);
                }
            } else if (NBTHelper.getString(stack, "ImmunityEffect").equals("Levitation")) {
                if (player.hasEffect(MobEffects.LEVITATION)) {
                    player.removeEffect(MobEffects.LEVITATION);
                    TotemHelper.coreDamageItem(stack, 1, world);
                }
            } else if (NBTHelper.getString(stack, "ImmunityEffect").equals("Mining Fatigue")) {
                if (player.hasEffect(MobEffects.DIG_SLOWDOWN)) {
                    player.removeEffect(MobEffects.DIG_SLOWDOWN);
                    TotemHelper.coreDamageItem(stack, 1, world);
                }
            } else if (NBTHelper.getString(stack, "ImmunityEffect").equals("Wither")) {
                if (player.hasEffect(MobEffects.WITHER)) {
                    player.removeEffect(MobEffects.WITHER);
                    TotemHelper.coreDamageItem(stack, 1, world);
                }
            }
        }
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return NBTHelper.getBoolean(stack, "Rolled");
    }

    @Override
    public boolean isFireResistant() {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip,
                                TooltipFlag flagIn) {

        TextComponent text;
        TotemHelper.coreTooltip(stack, worldIn, tooltip, flagIn);

        if (NBTHelper.getBoolean(stack, "Rolled")) {
            text = new TextComponent("Grands immunity to " + "\u00A76"
                    + NBTHelper.getString(stack, "ImmunityEffect") + "\u00A7f" + " effect.");
        } else {
            text = new TextComponent("Right click to roll.");
        }
        tooltip.add(text);

        super.appendHoverText(stack, worldIn, tooltip, flagIn);

    }

}
