package com.matyrobbrt.powerfultotems.common.effect;

import javax.annotation.Nonnull;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;

public class Beacon extends Effect {

	public Beacon() {
		super(EffectType.BENEFICIAL, 0xa3bdff);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplified) {
		return true;
	}
	
	@Override
	public void applyEffectTick(@Nonnull LivingEntity entity, int amplifier) {
		
		entity.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 100));
		entity.addEffect(new EffectInstance(Effects.DIG_SPEED, 100));
		entity.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 100));
		entity.addEffect(new EffectInstance(Effects.JUMP, 100));
		entity.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 100));
		
		entity.addEffect(new EffectInstance(Effects.REGENERATION, 100));
		
		super.applyEffectTick(entity, amplifier);
	}
	
}
