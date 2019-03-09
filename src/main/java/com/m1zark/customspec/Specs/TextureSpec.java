package com.m1zark.customspec.Specs;

import com.pixelmonmod.pixelmon.api.pokemon.ISpecType;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.SpecValue;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelmonmod.pixelmon.storage.NbtKeys;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Arrays;
import java.util.List;

public class TextureSpec extends SpecValue<String> implements ISpecType {
    public TextureSpec(String value) {
        super("texture", value);
    }

    public List<String> getKeys() {
        return Arrays.asList("texture");
    }

    @Override public Class<? extends SpecValue<?>> getSpecClass() {
        return this.getClass();
    }

    @Override public Class<String> getValueClass() {
        return String.class;
    }

    @Override public TextureSpec parse(String arg) {
        return new TextureSpec(arg);
    }

    @Override public String toParameterForm(SpecValue<?> value) {
        return value.key + ":" + value.value.toString();
    }

    @Override public SpecValue<String> clone() {
        return new TextureSpec(this.value);
    }

    @Override public TextureSpec readFromNBT(NBTTagCompound nbt) {
        return parse(nbt.getString(NbtKeys.CUSTOM_TEXTURE));
    }

    @Override public void writeToNBT(NBTTagCompound nbt, SpecValue<?> value) {
        nbt.setString(NbtKeys.CUSTOM_TEXTURE, value.value.toString());
    }

    @Override public boolean matches(EntityPixelmon pixelmon) {
        return this.matches(pixelmon.getPokemonData());
    }

    @Override public boolean matches(Pokemon pokemon) {
        return pokemon.getCustomTexture().equalsIgnoreCase(this.value);
    }

    @Override public boolean matches(NBTTagCompound nbt) {
        return nbt.getString(NbtKeys.CUSTOM_TEXTURE).equalsIgnoreCase(this.value);
    }

    @Override public void apply(EntityPixelmon pixelmon) {
        this.apply(pixelmon.getPokemonData());
    }

    @Override public void apply(Pokemon pokemon) {
        pokemon.setCustomTexture(this.value);
    }

    @Override public void apply(NBTTagCompound nbt) {
        nbt.setString(NbtKeys.CUSTOM_TEXTURE, this.value);
    }
}
