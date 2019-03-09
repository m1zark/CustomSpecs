package com.m1zark.customspec.Specs;

import com.m1zark.customspec.Customspec;
import com.pixelmonmod.pixelmon.api.pokemon.ISpecType;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.SpecValue;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelmonmod.pixelmon.storage.NbtKeys;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Arrays;
import java.util.List;

public class OTSpec extends SpecValue<String> implements ISpecType {
    public OTSpec(String value) {
        super("ot", value);
    }

    public List<String> getKeys() {
        return Arrays.asList("ot","originaltrainer");
    }

    @Override public Class<? extends SpecValue<?>> getSpecClass() {
        return this.getClass();
    }

    @Override public Class<String> getValueClass() {
        return String.class;
    }

    @Override public OTSpec parse(String arg) {
        return new OTSpec(arg);
    }

    @Override public String toParameterForm(SpecValue<?> value) {
        return value.key + ":" + value.value.toString();
    }

    @Override public SpecValue<String> clone() {
        return new TextureSpec(this.value);
    }

    @Override public OTSpec readFromNBT(NBTTagCompound nbt) {
        return parse(nbt.getString(NbtKeys.ORIGINAL_TRAINER));
    }

    @Override public void writeToNBT(NBTTagCompound nbt, SpecValue<?> value) {
        nbt.setString(NbtKeys.ORIGINAL_TRAINER, value.value.toString());
    }

    @Override public boolean matches(EntityPixelmon pixelmon) {
        return this.matches(pixelmon.getPokemonData());
    }

    @Override public boolean matches(Pokemon pokemon) {
        return pokemon.getOriginalTrainer().equalsIgnoreCase(this.value);
    }

    @Override public boolean matches(NBTTagCompound nbt) {
        return nbt.getString(NbtKeys.ORIGINAL_TRAINER).equals(this.value);
    }

    @Override public void apply(EntityPixelmon pixelmon) {
        this.apply(pixelmon.getPokemonData());
    }

    @Override public void apply(Pokemon pokemon) {
        pokemon.setOriginalTrainer(Customspec.getUser(this.value).getUniqueId(), this.value);
    }

    @Override public void apply(NBTTagCompound nbt) {
        nbt.setString(NbtKeys.ORIGINAL_TRAINER, this.value);
    }
}
