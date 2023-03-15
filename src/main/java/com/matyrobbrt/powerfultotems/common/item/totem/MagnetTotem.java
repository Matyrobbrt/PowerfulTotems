package com.matyrobbrt.powerfultotems.common.item.totem;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.matyrobbrt.powerfultotems.core.config.TotemConfig;
import com.matyrobbrt.powerfultotems.core.helper.NBTHelper;
import com.matyrobbrt.powerfultotems.core.helper.TotemHelper;
import com.matyrobbrt.powerfultotems.core.itemgroup.TotemItemGroup;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.Level;

public class MagnetTotem extends Item {

    public MagnetTotem() {
        super(new Item.Properties().stacksTo(1).setNoRepair().tab(TotemItemGroup.TOTEM_ITEM_GROUP).durability(2048)
                .rarity(Rarity.RARE));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, InteractionHand hand) {

        ItemStack stack = player.getItemInHand(hand);

        player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 0.5F, NBTHelper.getBoolean(stack, "Enabled") ? 0.5F : 1.0F);

        NBTHelper.flipBoolean(stack, "Enabled");
        NBTHelper.flipBoolean(stack, "IsNotMagnetable");

        return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean isSelected) {
        if (entity instanceof Player player && NBTHelper.getBoolean(stack, "Enabled")) {
            double range = TotemConfig.magnet_totem_range.get() + NBTHelper.getInt(stack, "Core") * 2.0;
            List<ItemEntity> rangeItems = world.getEntitiesOfClass(ItemEntity.class,
                    entity.getBoundingBox().inflate(range));
            for (ItemEntity item : rangeItems) {
                if (!item.isAlive() || NBTHelper.getBoolean(item.getItem(), "IsNotMagnetable"))
                    continue;

                if (item.getThrower() != null && item.getThrower().equals(entity.getUUID()) && item.hasPickUpDelay())
                    continue;

                if (!world.isClientSide()) {
                    item.setNoPickUpDelay();
                    int damage = item.getItem().getCount();
                    boolean shouldDamage = !(item.distanceTo(entity) < 1.5F);
                    item.setPos(entity.getX(), entity.getY(), entity.getZ());
                    if (shouldDamage)
                        if (!player.isCreative())
                            TotemHelper.coreDamageItem(stack, damage, world);
                }
            }

            List<ExperienceOrb> xp = world.getEntitiesOfClass(ExperienceOrb.class,
                    entity.getBoundingBox().inflate(range));
            for (ExperienceOrb orb : xp) {
                if (!world.isClientSide()) {
                    orb.setPos(entity.getX(), entity.getY(), entity.getZ());
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
        return NBTHelper.getBoolean(stack, "Enabled");
    }

    @Override
    public boolean isFireResistant() {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip,
                                TooltipFlag flagIn) {

        TotemHelper.coreTooltip(stack, worldIn, tooltip, flagIn);

        if (NBTHelper.getBoolean(stack, "Enabled")) {
            String activationInfo = "ENABLED";
            TextComponent text = new TextComponent("\u00A76" + activationInfo);
            tooltip.add(text);
        }

        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

}
