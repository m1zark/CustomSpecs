package com.m1zark.customspec.Specs;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.SpecFlag;
import com.pixelmonmod.pixelmon.api.pokemon.SpecValue;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import java.util.Arrays;
import java.util.List;

import com.pixelmonmod.pixelmon.storage.NbtKeys;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;

public class UncatchableFlag extends SpecFlag {
    public UncatchableFlag() {
        super("uncatchable");
    }

    public SpecValue<Boolean> instantiate() {
        return new UncatchableFlag();
    }

    public List<String> getKeys() {
        return Arrays.asList("uncatchable");
    }

    @Override public void writeToNBT(NBTTagCompound nbt, SpecValue<?> value) {
        nbt.setBoolean("Uncatchable", true);
        nbt.setString(NbtKeys.NICKNAME, nbt.getString(NbtKeys.NICKNAME) + TextFormatting.getValueByName("Red") + " (Uncatchable)");
    }

    @Override public boolean matches(EntityPixelmon pokemon) {
        return pokemon.getPokemonData().getPersistentData().hasKey("Uncatchable");
    }

    @Override public boolean matches(Pokemon pokemon) {
        return pokemon.getPersistentData().hasKey("Uncatchable");
    }

    @Override public boolean matches(NBTTagCompound nbt) {
        return nbt.hasKey("Uncatchable");
    }

    @Override public void apply(EntityPixelmon pokemon) {
        pokemon.getPokemonData().getPersistentData().setBoolean("Uncatchable", true);
        pokemon.getPokemonData().setNickname(pokemon.getDisplayName().getFormattedText() + TextFormatting.getValueByName("Red") + " (Uncatchable)");
    }

    @Override public void apply(Pokemon pokemon) {
        pokemon.getPersistentData().setBoolean("Uncatchable", true);
        pokemon.setNickname(pokemon.getDisplayName() + TextFormatting.getValueByName("Red") + " (Uncatchable)");
    }

    @Override public void apply(NBTTagCompound nbt) {
        nbt.setBoolean("Uncatchable", true);
        nbt.setString(NbtKeys.NICKNAME, nbt.getString(NbtKeys.NICKNAME) + TextFormatting.getValueByName("Red") + " (Uncatchable)");
    }
}
