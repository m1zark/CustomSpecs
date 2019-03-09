package com.m1zark.customspec.Specs;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.SpecFlag;
import com.pixelmonmod.pixelmon.api.pokemon.SpecValue;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Arrays;
import java.util.List;

public class BreedableFlag extends SpecFlag {
    public BreedableFlag() {
        super("breedable");
    }

    @Override
    public SpecValue<Boolean> instantiate() {
        return new BreedableFlag();
    }

    @Override
    public List<String> getKeys() {
        return Arrays.asList("breedable");
    }

    @Override
    public void apply(EntityPixelmon pokemon) {
        pokemon.getPokemonData().getPersistentData().removeTag("unbreedable");
    }

    @Override
    public void apply(Pokemon pokemon) {
        pokemon.getPersistentData().removeTag("unbreedable");
    }

    @Override
    public void apply(NBTTagCompound nbt) {
        nbt.removeTag("unbreedable");
    }
}
