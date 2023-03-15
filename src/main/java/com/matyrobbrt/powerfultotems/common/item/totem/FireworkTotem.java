package com.matyrobbrt.powerfultotems.common.item.totem;

import com.matyrobbrt.powerfultotems.core.itemgroup.TotemItemGroup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;

@SuppressWarnings("unused")
public class FireworkTotem extends BaseTotem {

    public FireworkTotem() {
        super(new Item.Properties().stacksTo(1).setNoRepair().tab(TotemItemGroup.TOTEM_ITEM_GROUP).durability(256)
                .rarity(Rarity.UNCOMMON));
    }

    @Override
    public void use(Level worldIn, Player player, ItemStack stack) {
        if (!player.isShiftKeyDown()) {
            FireworkRocketEntity fireworkRocket = new FireworkRocketEntity(worldIn, rocketCreate(), player);
            fireworkRocket.shootFromRotation(player, player.xRotO, player.yRotO, 0.0F, 1.5F, 1.0F);
            worldIn.addFreshEntity(fireworkRocket);
            stack.hurtAndBreak(1, player, e -> {
            });
        } else if (player.isShiftKeyDown()) {
            for (int i = 0; i <= 35; ++i) {
                if (player.getInventory().getItem(i).getItem() == Items.FIREWORK_ROCKET) {
                    if (player.getInventory().getItem(i).getCount() >= 16 && stack.getDamageValue() >= 16) {
                        stack.setDamageValue(stack.getDamageValue() - 16);
                        player.getInventory().getItem(i).shrink(16);
                    } else if (stack.getDamageValue() != 0
                            && player.getInventory().getItem(i).getCount() >= stack.getDamageValue()) {
                        player.getInventory().getItem(i).shrink(stack.getDamageValue());
                        stack.setDamageValue(0);
                    } else if (stack.getDamageValue() != 0
                            && player.getInventory().getItem(i).getCount() <= stack.getDamageValue()) {
                        stack.setDamageValue(stack.getDamageValue() - player.getInventory().getItem(i).getCount());
                        player.getInventory().getItem(i).shrink(player.getInventory().getItem(i).getCount());
                    }
                }
            }

        }
    }

    private ItemStack rocketCreate() {
        ItemStack rocket = new ItemStack(Items.FIREWORK_ROCKET);

        rocket.getOrCreateTagElement("Fireworks");

        CompoundTag compoundnbt = rocket.getOrCreateTagElement("Fireworks");

        ItemStack itemstack1 = new ItemStack(Items.FIREWORK_STAR);

        compoundnbt.putByte("Flight", (byte) 1);

        return rocket;

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

}
