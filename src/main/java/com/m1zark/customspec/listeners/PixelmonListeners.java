package com.m1zark.customspec.listeners;

import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import com.pixelmonmod.pixelmon.api.events.EvolveEvent;
import com.pixelmonmod.pixelmon.api.events.PokeballImpactEvent;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonSpec;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import com.pixelmonmod.pixelmon.enums.items.EnumPokeballs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class PixelmonListeners {
    private PokemonSpec UNCATCHABLE = new PokemonSpec("uncatchable");

    @SubscribeEvent
    public void onEvo(EvolveEvent.PostEvolve evolveEvent) {
        String prevName = evolveEvent.preEvo.getNickname();
        EnumSpecies preSpecies = evolveEvent.preEvo.getSpecies();
        evolveEvent.pokemon.getPokemonData().setNickname(prevName.replace(preSpecies.name, evolveEvent.pokemon.getSpecies().name));
    }

    @SubscribeEvent
    public void onPokemonCapture(CaptureEvent.StartCapture event) {
        if(UNCATCHABLE.matches(event.getPokemon())) {
            ((Player) event.player).sendMessage(Text.of(TextColors.RED, "This Pokemon is uncatchable!"));
            event.player.inventory.addItemStackToInventory(new ItemStack(event.pokeball.getType().getItem()));
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void OnPokeballImpact(PokeballImpactEvent event) {
        if(event.getEntityHit() instanceof EntityPixelmon) {
            EntityPixelmon pokemon = (EntityPixelmon) event.getEntityHit();
        }
    }
}
