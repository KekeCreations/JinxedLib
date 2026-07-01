package com.kekecreations.jinxedlib.client.renderer.atlas;

import com.google.common.base.Suppliers;
import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import net.minecraft.client.renderer.texture.SpriteContents;
import net.minecraft.client.renderer.texture.atlas.SpriteResourceLoader;
import net.minecraft.client.renderer.texture.atlas.SpriteSource;
import net.minecraft.client.renderer.texture.atlas.SpriteSources;
import net.minecraft.client.renderer.texture.atlas.sources.LazyLoadedImage;
import net.minecraft.client.resources.metadata.animation.FrameSize;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.ARGB;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;

public record JinxedPalettedPermutations(List<SpriteSource> textures, Identifier paletteKey, Map<String, Identifier> permutations, String separator) implements SpriteSource {
    static final Logger LOGGER = LogUtils.getLogger();
    public static final String DEFAULT_SEPARATOR = "_";


    public static final MapCodec<JinxedPalettedPermutations> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return instance.group(Codec.list(SpriteSources.CODEC).fieldOf("sources").forGetter((palettedPermutations) -> {
            return palettedPermutations.textures;
        }), Identifier.CODEC.fieldOf("palette_key").forGetter((palettedPermutations) -> {
            return palettedPermutations.paletteKey;
        }), Codec.unboundedMap(Codec.STRING, Identifier.CODEC).fieldOf("permutations").forGetter((palettedPermutations) -> {
            return palettedPermutations.permutations;
        })).apply(instance, JinxedPalettedPermutations::new);
    });

    public JinxedPalettedPermutations(List<SpriteSource> p_400242_, Identifier p_469230_, Map<String, Identifier> p_400160_) {
        this(p_400242_, p_469230_, p_400160_, "_");
    }

    public void run(ResourceManager resourceManager, SpriteSource.Output output) {
        Supplier<int[]> supplier = Suppliers.memoize(() -> {
            return loadPaletteEntryFromImage(resourceManager, this.paletteKey);
        });
        Map<String, Supplier<IntUnaryOperator>> map = new HashMap();
        this.permutations.forEach((string, resourceLocationx) -> {
            map.put(string, Suppliers.memoize(() -> {
                return createPaletteMapping((int[])supplier.get(), loadPaletteEntryFromImage(resourceManager, resourceLocationx));
            }));
        });
        HashMap<Identifier, SpriteSource.DiscardableLoader> sourceOutputMap = new HashMap<>();
        Output sourceOutput = new Output() {
            @Override
            public void add(@NotNull Identifier location, @NotNull DiscardableLoader spriteSupplier) {
                SpriteSource.DiscardableLoader supplier = sourceOutputMap.put(location, spriteSupplier);
                if (supplier != null) {
                    supplier.discard();
                }
            }
            @Override
            public void removeAll(@NotNull Predicate<Identifier> predicate) {
                Iterator<Map.Entry<Identifier, DiscardableLoader>> iterator = sourceOutputMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Identifier, SpriteSource.DiscardableLoader> entry = iterator.next();
                    if (predicate.test(entry.getKey())) {
                        entry.getValue().discard();
                        iterator.remove();
                    }
                }
            }
        };
        for (Iterator<SpriteSource> it = this.textures.iterator(); it.hasNext(); ) {
            SpriteSource source = it.next();
            source.run(resourceManager, sourceOutput);
        }
        for (Iterator<Identifier> it = sourceOutputMap.keySet().iterator(); it.hasNext(); ) {
            Identifier resourceLocation = it.next();
            Identifier resourceLocation2 = TEXTURE_ID_CONVERTER.idToFile(resourceLocation);
            Optional<Resource> optional = resourceManager.getResource(resourceLocation2);
            if (optional.isEmpty()) {
                LOGGER.warn("Unable to find texture {}", resourceLocation2);
            } else {
                LazyLoadedImage lazyLoadedImage = new LazyLoadedImage(resourceLocation2, (Resource)optional.get(), map.size());
                Iterator var10 = map.entrySet().iterator();

                while(var10.hasNext()) {
                    Map.Entry<String, Supplier<IntUnaryOperator>> entry = (Map.Entry)var10.next();
                    Identifier resourceLocation3 = resourceLocation.withSuffix("_" + (String)entry.getKey());
                    output.add(resourceLocation3, new JinxedPalettedSpriteSupplier(lazyLoadedImage, (Supplier)entry.getValue(), resourceLocation3));
                }
            }
        }
    }

    private static IntUnaryOperator createPaletteMapping(int[] keys, int[] values) {
        if (values.length != keys.length) {
            LOGGER.warn("Palette mapping has different sizes: {} and {}", keys.length, values.length);
            throw new IllegalArgumentException();
        } else {
            Int2IntMap int2intmap = new Int2IntOpenHashMap(values.length);

            for(int i = 0; i < keys.length; ++i) {
                int j = keys[i];
                if (ARGB.alpha(j) != 0) {
                    int2intmap.put(ARGB.transparent(j), values[i]);
                }
            }

            return (p_359295_) -> {
                int k = ARGB.alpha(p_359295_);
                if (k == 0) {
                    return p_359295_;
                } else {
                    int l = ARGB.transparent(p_359295_);
                    int i1 = int2intmap.getOrDefault(l, ARGB.opaque(l));
                    int j1 = ARGB.alpha(i1);
                    return ARGB.color(k * j1 / 255, i1);
                }
            };
        }
    }

    private static int[] loadPaletteEntryFromImage(ResourceManager resourceManager, Identifier palette) {
        Optional<Resource> optional = resourceManager.getResource(TEXTURE_ID_CONVERTER.idToFile(palette));
        if (optional.isEmpty()) {
            LOGGER.error("Failed to load palette image {}", palette);
            throw new IllegalArgumentException();
        } else {
            try {
                int[] aint;
                try (
                        InputStream inputstream = ((Resource)optional.get()).open();
                        NativeImage nativeimage = NativeImage.read(inputstream);
                ) {
                    aint = nativeimage.getPixels();
                }

                return aint;
            } catch (Exception exception) {
                LOGGER.error("Couldn't load texture {}", palette, exception);
                throw new IllegalArgumentException();
            }
        }
    }

    public MapCodec<JinxedPalettedPermutations> codec() {
        return JinxedPalettedPermutations.CODEC;
    }

    static record JinxedPalettedSpriteSupplier(LazyLoadedImage baseImage, Supplier<IntUnaryOperator> paletteSupplier, Identifier permutationLocation) implements SpriteSource.DiscardableLoader {


        JinxedPalettedSpriteSupplier(LazyLoadedImage baseImage, Supplier<IntUnaryOperator> paletteSupplier, Identifier permutationLocation) {
            this.baseImage = baseImage;
            this.paletteSupplier = paletteSupplier;
            this.permutationLocation = permutationLocation;
        }

        @Nullable
        public SpriteContents get(SpriteResourceLoader loader) {
            SpriteContents var2;
            try {
                NativeImage nativeImage = this.baseImage.get().mappedCopy((IntUnaryOperator)this.paletteSupplier.get());
                var2 = new SpriteContents(this.permutationLocation, new FrameSize(nativeImage.getWidth(), nativeImage.getHeight()), nativeImage);
                return var2;
            } catch (IllegalArgumentException | IOException var6) {
                Exception exception = var6;
                JinxedPalettedPermutations.LOGGER.error("unable to apply palette to {}", this.permutationLocation, exception);
                var2 = null;
            } finally {
                this.baseImage.release();
            }

            return var2;
        }

        public void discard() {
            this.baseImage.release();
        }

        public LazyLoadedImage baseImage() {
            return this.baseImage;
        }

        public Supplier<IntUnaryOperator> palette() {
            return this.paletteSupplier;
        }

        public Identifier permutationLocation() {
            return this.permutationLocation;
        }
    }
}
