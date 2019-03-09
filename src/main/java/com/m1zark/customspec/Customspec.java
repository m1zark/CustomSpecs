package com.m1zark.customspec;

import com.m1zark.customspec.Specs.*;
import com.m1zark.customspec.listeners.PixelmonListeners;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonSpec;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.user.UserStorageService;

import java.util.Optional;

@Plugin(id = "customspec", name = "CustomSpec", version = "1.3", description = "Adds in new pokespecs", authors = "m1zark")
public class Customspec {
    private static Customspec instance;

    @Listener public void onServerStart(GameInitializationEvent e){
        instance = this;

        PokemonSpec.extraSpecTypes.add(new TextureSpec(null));
        PokemonSpec.extraSpecTypes.add(new NicknameSpec(null));
        PokemonSpec.extraSpecTypes.add(new OTSpec(null));
        PokemonSpec.extraSpecTypes.add(new BreedableFlag());
        PokemonSpec.extraSpecTypes.add(new UncatchableFlag());
        PokemonSpec.extraSpecTypes.add(new MinimumIVPercentage(0));
        PokemonSpec.extraSpecTypes.add(new NumMaxIVs(0));

        if(Sponge.getPluginManager().isLoaded("entity-particles")) {
            PokemonSpec.extraSpecTypes.add(new AuraSpec(null));
        }

        Pixelmon.EVENT_BUS.register(new PixelmonListeners());
    }

    public static User getUser(String name) {
        Optional<UserStorageService> userStorage = Sponge.getServiceManager().provide(UserStorageService.class);
        Optional<User> oUser = userStorage.get().get(name);

        return oUser.orElse(null);
    }
}
