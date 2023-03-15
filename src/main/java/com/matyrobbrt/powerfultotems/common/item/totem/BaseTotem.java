package com.matyrobbrt.powerfultotems.common.item.totem;

import com.matyrobbrt.powerfultotems.core.config.TotemConfig;
import com.matyrobbrt.powerfultotems.core.helper.NBTHelper;
import com.matyrobbrt.powerfultotems.core.helper.TotemHelper;
import com.matyrobbrt.powerfultotems.core.itemgroup.TotemItemGroup;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.function.Supplier;

@ParametersAreNonnullByDefault
public abstract class BaseTotem extends Item {
    public BaseTotem(Properties pProperties) {
        super(pProperties.setNoRepair().tab(TotemItemGroup.TOTEM_ITEM_GROUP));
    }

    public abstract void use(Level world, Player player, ItemStack stack);

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        final ItemStack stack = player.getItemInHand(hand);
        if (!player.getCooldowns().isOnCooldown(this)) {
            this.use(world, player, stack);
        }
        return InteractionResultHolder.sidedSuccess(stack, world.isClientSide);
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

    public static final class Effect extends BaseTotem {

        private final Supplier<MobEffect> effectSupplier;
        private final Supplier<Integer> effectDuration;
        @Nullable
        private final Supplier<Integer> maxLevel;

        public Effect(Properties pProperties, Supplier<MobEffect> effectSupplier, Supplier<Integer> effectDuration, @Nullable Supplier<Integer> maxLevel) {
            super(pProperties);
            this.effectSupplier = effectSupplier;
            this.effectDuration = effectDuration;
            this.maxLevel = maxLevel;
        }

        @Override
        public void use(Level world, Player player, ItemStack stack) {
            if (world.isClientSide) return;
            final var effect = effectSupplier.get();

            if (maxLevel != null) {
                final var currrent = player.getEffect(effect);
                if (currrent != null && currrent.getAmplifier() < maxLevel.get() - 1) {
                    int level = currrent.getAmplifier() + 1;
                    player.addEffect(new MobEffectInstance(effect,
                            effectDuration.get() * 20, level, true, true));
                    stack.hurtAndBreak(1, player, e -> {
                    });
                } else if (currrent == null) {
                    player.addEffect(new MobEffectInstance(effect, effectDuration.get() * 20, 0, true, true));
                    stack.hurtAndBreak(1, player, e -> {
                    });
                } else {
                    player.sendMessage(new TextComponent("\u00A7b" + "You have reached the maximum level of ").append(effect.getDisplayName()).append("!"),
                            player.getUUID());
                }
            } else {
                if (!player.hasEffect(effect)) {
                    player.addEffect(new MobEffectInstance(effect,
                            effectDuration.get() * 20, NBTHelper.getInt(stack, "Core"),
                            true, true));
                    TotemHelper.coreDamageItem(stack, 1, world);
                } else {
                    player.sendMessage(new TextComponent("You already have ").append(effect.getDisplayName().copy().withStyle(ChatFormatting.AQUA))
                            .append("!"), player.getUUID());
                }
            }
        }

        @Override
        public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
            Component text = new TextComponent("\u00A7b" + "Right click to get ").append(effectSupplier.get().getDisplayName().copy().withStyle(ChatFormatting.GOLD)).append(" effect for " + effectDuration.get() + " seconds.");
            tooltip.add(text);
            super.appendHoverText(stack, worldIn, tooltip, flagIn);
        }
    }
}
