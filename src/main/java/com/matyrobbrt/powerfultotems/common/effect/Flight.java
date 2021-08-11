package com.matyrobbrt.powerfultotems.common.effect;

import javax.annotation.Nonnull;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

@SuppressWarnings("unused")
public class Flight extends Effect {

	public Flight() {
		super(EffectType.BENEFICIAL, 0x1d1c21);
	}

	/**
	@Override
	public boolean isDurationEffectTick(int duration, int amplified) {
		return true;
	}

	@Override
	public void applyEffectTick(@Nonnull LivingEntity entity, int amplifier) {

		Minecraft mc = Minecraft.getInstance();

		if (!entity.level.isClientSide) {

			if (mc.player.abilities.flying == false) {
				mc.player.abilities.flying = true;
				mc.player.onUpdateAbilities();
			}
		}

		if (entity.fallDistance > 0.0F) {
			entity.fallDistance = 0.0F;
		}

		super.applyEffectTick(entity, amplifier);
	}
	**/

}
