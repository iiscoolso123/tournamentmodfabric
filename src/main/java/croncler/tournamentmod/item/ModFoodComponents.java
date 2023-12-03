package croncler.tournamentmod.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent LIME_FEET = new FoodComponent.Builder().hunger(3).saturationModifier(0.25f).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA,200), 1).build();
    public static final FoodComponent FLESH = new FoodComponent.Builder().hunger(10).saturationModifier(0.25f).build();
}
