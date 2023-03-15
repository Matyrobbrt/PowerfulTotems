package com.matyrobbrt.powerfultotems.common.item.totem;

import java.util.List;

import javax.annotation.Nullable;

import com.matyrobbrt.powerfultotems.core.helper.NBTHelper;
import com.matyrobbrt.powerfultotems.core.helper.TotemHelper;
import com.matyrobbrt.powerfultotems.core.itemgroup.TotemItemGroup;

import net.minecraft.core.Registry;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.entity.Entity;
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

public class VoidTotem extends Item {

    public VoidTotem() {
        super(new Item.Properties().stacksTo(1).setNoRepair().tab(TotemItemGroup.TOTEM_ITEM_GROUP).durability(2048)
                .rarity(Rarity.RARE));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player player, InteractionHand hand) {

        ItemStack stack = player.getItemInHand(hand);

        if (!player.isShiftKeyDown()) {
            player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 0.5F,
                    NBTHelper.getBoolean(stack, "Enabled") ? 0.5F : 1.0F);

            NBTHelper.flipBoolean(stack, "Enabled");
            NBTHelper.flipBoolean(stack, "IsNotMagnetable");
        } else if (!player.getOffhandItem().isEmpty()) {
            ItemStack voidStack = player.getOffhandItem();
            NBTHelper.setString(stack, "VoidItem", Registry.ITEM.getKey(voidStack.getItem()).toString());
        }

        return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean isSelected) {
        if (entity instanceof Player player && NBTHelper.getBoolean(stack, "Enabled")) {
            String voidItem = NBTHelper.getString(stack, "VoidItem");

            // Inventory
            for (int i = 0; i <= Inventory.INVENTORY_SIZE; ++i) {
                final ItemStack inv = player.getInventory().getItem(i);
                if (Registry.ITEM.getKey(inv.getItem()).toString().equals(voidItem)) {
                    final int count = inv.getCount();
                    inv.shrink(1);
                    TotemHelper.coreDamageItem(stack, count, world);
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
        TotemHelper.enabledInfo(stack, worldIn, tooltip, flagIn);
        if (!NBTHelper.getString(stack, "VoidItem").isBlank())
            tooltip.add(new TextComponent("Is voiding " + "\u00A7b" + NBTHelper.getString(stack, "VoidItem")));
        else
            tooltip.add(new TextComponent("Is not voiding anything!"));

        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

}
