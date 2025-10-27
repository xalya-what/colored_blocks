
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.colored.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Holder;

import net.mcreator.colored.world.features.ores.YellowOreFeature;
import net.mcreator.colored.world.features.ores.WhiteOreFeature;
import net.mcreator.colored.world.features.ores.RedOreFeature;
import net.mcreator.colored.world.features.ores.Pink1OreFeature;
import net.mcreator.colored.world.features.ores.GreenOreFeature;
import net.mcreator.colored.world.features.ores.CyanOreFeature;
import net.mcreator.colored.world.features.ores.BlackOreFeature;
import net.mcreator.colored.ColoredMod;

import java.util.function.Supplier;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

@Mod.EventBusSubscriber
public class ColoredModFeatures {
	public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, ColoredMod.MODID);
	private static final List<FeatureRegistration> FEATURE_REGISTRATIONS = new ArrayList<>();
	public static final RegistryObject<Feature<?>> GREEN_ORE = register("green_ore", GreenOreFeature::feature,
			new FeatureRegistration(GenerationStep.Decoration.UNDERGROUND_ORES, GreenOreFeature.GENERATE_BIOMES, GreenOreFeature::placedFeature));
	public static final RegistryObject<Feature<?>> RED_ORE = register("red_ore", RedOreFeature::feature,
			new FeatureRegistration(GenerationStep.Decoration.UNDERGROUND_ORES, RedOreFeature.GENERATE_BIOMES, RedOreFeature::placedFeature));
	public static final RegistryObject<Feature<?>> CYAN_ORE = register("cyan_ore", CyanOreFeature::feature,
			new FeatureRegistration(GenerationStep.Decoration.UNDERGROUND_ORES, CyanOreFeature.GENERATE_BIOMES, CyanOreFeature::placedFeature));
	public static final RegistryObject<Feature<?>> YELLOW_ORE = register("yellow_ore", YellowOreFeature::feature,
			new FeatureRegistration(GenerationStep.Decoration.UNDERGROUND_ORES, YellowOreFeature.GENERATE_BIOMES, YellowOreFeature::placedFeature));
	public static final RegistryObject<Feature<?>> PINK_1_ORE = register("pink_1_ore", Pink1OreFeature::feature,
			new FeatureRegistration(GenerationStep.Decoration.UNDERGROUND_ORES, Pink1OreFeature.GENERATE_BIOMES, Pink1OreFeature::placedFeature));
	public static final RegistryObject<Feature<?>> WHITE_ORE = register("white_ore", WhiteOreFeature::feature,
			new FeatureRegistration(GenerationStep.Decoration.UNDERGROUND_ORES, WhiteOreFeature.GENERATE_BIOMES, WhiteOreFeature::placedFeature));
	public static final RegistryObject<Feature<?>> BLACK_ORE = register("black_ore", BlackOreFeature::feature,
			new FeatureRegistration(GenerationStep.Decoration.UNDERGROUND_ORES, BlackOreFeature.GENERATE_BIOMES, BlackOreFeature::placedFeature));

	private static RegistryObject<Feature<?>> register(String registryname, Supplier<Feature<?>> feature, FeatureRegistration featureRegistration) {
		FEATURE_REGISTRATIONS.add(featureRegistration);
		return REGISTRY.register(registryname, feature);
	}

	@SubscribeEvent
	public static void addFeaturesToBiomes(BiomeLoadingEvent event) {
		for (FeatureRegistration registration : FEATURE_REGISTRATIONS) {
			if (registration.biomes() == null || registration.biomes().contains(event.getName()))
				event.getGeneration().getFeatures(registration.stage()).add(registration.placedFeature().get());
		}
	}

	private static record FeatureRegistration(GenerationStep.Decoration stage, Set<ResourceLocation> biomes,
			Supplier<Holder<PlacedFeature>> placedFeature) {
	}
}
