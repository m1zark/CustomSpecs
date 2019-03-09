package com.m1zark.customspec.Specs;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.ISpecType;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.SpecValue;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelmonmod.pixelmon.storage.NbtKeys;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class NicknameSpec extends SpecValue<String> implements ISpecType {
    public NicknameSpec(String value) {
        super("nickname", value);
    }

    public List<String> getKeys() {
        return Arrays.asList("nickname");
    }

    @Override public Class<? extends SpecValue<?>> getSpecClass() {
        return this.getClass();
    }

    @Override public Class<String> getValueClass() {
        return String.class;
    }

    @Override public NicknameSpec parse(String arg) {
        return new NicknameSpec(arg.replace("_"," ").replace("&","\u00A7"));
    }

    @Override public String toParameterForm(SpecValue<?> value) {
        return value.key + ":" + value.value.toString();
    }

    @Override public SpecValue<String> clone() {
        return new NicknameSpec(this.value);
    }

    @Override public NicknameSpec readFromNBT(NBTTagCompound nbt) {
        return parse(nbt.getString(NbtKeys.NICKNAME));
    }

    @Override public void writeToNBT(NBTTagCompound nbt, SpecValue<?> value) {
        nbt.setString(NbtKeys.NICKNAME, value.value.toString());
    }

    public boolean matches(EntityPixelmon pokemon) {
        return pokemon.getNickname().equals(this.value);
    }

    public boolean matches(NBTTagCompound nbt) {
        return nbt.getString("Nickname").equals(this.value);
    }

    public boolean matches(Pokemon pokemon) {
        return Objects.equals(pokemon.getNickname(), this.value);
    }

    public void apply(EntityPixelmon pokemon) {
        String nick = this.value.replace("%pokemon%", pokemon.getSpecies().name);
        pokemon.getPokemonData().setNickname(nick);
    }

    public void apply(NBTTagCompound nbt) {
        Pokemon pokemon = Pixelmon.pokemonFactory.create(nbt);
        String nick = this.value.replace("%pokemon%", pokemon.getSpecies().name);
        nbt.setString(NbtKeys.NICKNAME, nick);
    }

    public void apply(Pokemon pokemon) {
        String nick = this.value.replace("%pokemon%", pokemon.getSpecies().name);
        pokemon.setNickname(nick);
    }
}