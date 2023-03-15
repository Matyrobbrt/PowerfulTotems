package com.matyrobbrt.powerfultotems.core.init;

import com.matyrobbrt.powerfultotems.PowerfulTotems;
import com.matyrobbrt.powerfultotems.common.item.totem.BaseTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.FireworkTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.GluttonyTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.GodTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.ImmunityTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.IronGolemTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.LightningTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.MagnetTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.StrengthTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.TeleportTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.TotemOfDying;
import com.matyrobbrt.powerfultotems.common.item.totem.VoidTotem;
import com.matyrobbrt.powerfultotems.common.item.totem.WaterTotem;
import com.matyrobbrt.powerfultotems.core.config.TotemConfig;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TotemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            PowerfulTotems.MOD_ID);

    public static final RegistryObject<BaseTotem.Effect> HEALTH_TOTEM = ITEMS.register("health_totem", () -> new BaseTotem.Effect(
            new Item.Properties().rarity(Rarity.EPIC).durability(8),
            () -> MobEffects.HEALTH_BOOST,
            TotemConfig.health_totem_effect_duration,
            TotemConfig.health_totem_max_effect_level
    ));
    public static final RegistryObject<BaseTotem.Effect> NIGHT_VISION_TOTEM = ITEMS.register("night_vision_totem", () -> new BaseTotem.Effect(
            new Item.Properties().rarity(Rarity.EPIC).durability(8),
            () -> MobEffects.NIGHT_VISION,
            TotemConfig.night_vision_totem_effect_duration,
            null
    ));
    public static final RegistryObject<BaseTotem.Effect> FALL_TOTEM = ITEMS.register("fall_totem", () -> new BaseTotem.Effect(
            new Item.Properties().rarity(Rarity.EPIC).durability(8),
            PotionInit.NEGATE_FALL_DAMAGE::get,
            TotemConfig.fall_totem_effect_duration,
            null
    ));
    public static final RegistryObject<BaseTotem.Effect> SPEED_TOTEM = ITEMS.register("speed_totem", () -> new BaseTotem.Effect(
            new Item.Properties().rarity(Rarity.EPIC).durability(8),
            () -> MobEffects.MOVEMENT_SPEED,
            TotemConfig.speed_totem_effect_duration,
            TotemConfig.speed_totem_max_effect_level
    ));
    public static final RegistryObject<BaseTotem.Effect> WATER_BREATHING_TOTEM = ITEMS.register("water_breathing_totem", () -> new BaseTotem.Effect(
            new Item.Properties().rarity(Rarity.EPIC).durability(8),
            () -> MobEffects.WATER_BREATHING,
            TotemConfig.water_breathing_totem_effect_duration,
            null
    ));
    public static final RegistryObject<BaseTotem.Effect> FLIGHT_TOTEM = ITEMS.register("flight_totem", () -> new BaseTotem.Effect(
            new Item.Properties().rarity(Rarity.EPIC).durability(4),
            PotionInit.FLIGHT::get,
            TotemConfig.flight_totem_effect_duration,
            null
    ));
    public static final RegistryObject<GodTotem> GOD_TOTEM = ITEMS.register("god_totem", GodTotem::new);
    public static final RegistryObject<BaseTotem.Effect> JUMP_TOTEM = ITEMS.register("jump_totem", () -> new BaseTotem.Effect(
            new Item.Properties().rarity(Rarity.EPIC).durability(8),
            () -> MobEffects.JUMP,
            TotemConfig.jump_totem_effect_duration,
            TotemConfig.jump_totem_max_effect_level
    ));
    public static final RegistryObject<GluttonyTotem> GLUTTONY_TOTEM = ITEMS.register("gluttony_totem",
            GluttonyTotem::new);
    public static final RegistryObject<BaseTotem.Effect> FIRE_RESISTANCE_TOTEM = ITEMS
            .register("fire_resistance_totem", () -> new BaseTotem.Effect(
                    new Item.Properties().rarity(Rarity.EPIC).durability(8),
                    () -> MobEffects.FIRE_RESISTANCE,
                    TotemConfig.fire_resistance_effect_duration,
                    null
            ));
    public static final RegistryObject<StrengthTotem> STRENGTH_TOTEM = ITEMS.register("strength_totem",
            StrengthTotem::new);
    public static final RegistryObject<FireworkTotem> FIREWORK_TOTEM = ITEMS.register("firework_totem",
            FireworkTotem::new);
    public static final RegistryObject<MagnetTotem> MAGNET_TOTEM = ITEMS.register("magnet_totem", MagnetTotem::new);
    public static final RegistryObject<ImmunityTotem> IMMUNITY_TOTEM = ITEMS.register("immunity_totem",
            ImmunityTotem::new);
    public static final RegistryObject<BaseTotem.Effect> REGENERATION_TOTEM = ITEMS.register("regeneration_totem", () -> new BaseTotem.Effect(
            new Item.Properties().rarity(Rarity.EPIC).durability(6),
            () -> MobEffects.REGENERATION,
            TotemConfig.regeneration_totem_effect_duration,
            null
    ));
    public static final RegistryObject<BaseTotem.Effect> BEACON_TOTEM = ITEMS.register("beacon_totem", () -> new BaseTotem.Effect(
            new Item.Properties().rarity(Rarity.EPIC).durability(4),
            PotionInit.BEACON::get,
            TotemConfig.beacon_totem_effect_duration,
            null
    ));
    public static final RegistryObject<TeleportTotem> TELEPORT_TOTEM = ITEMS.register("teleport_totem",
            TeleportTotem::new);
    public static final RegistryObject<WaterTotem> WATER_TOTEM = ITEMS.register("water_totem", WaterTotem::new);
    public static final RegistryObject<IronGolemTotem> GOLEM_TOTEM = ITEMS.register("golem_totem", IronGolemTotem::new);
    public static final RegistryObject<TotemOfDying> DYING_TOTEM = ITEMS.register("totem_of_dying", TotemOfDying::new);
    public static final RegistryObject<VoidTotem> VOID_TOTEM = ITEMS.register("void_totem", VoidTotem::new);
    public static final RegistryObject<LightningTotem> LIGHTNING_TOTEM = ITEMS.register("lightning_totem", LightningTotem::new);

}
