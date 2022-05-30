package net.wiremc.common.api.core.profile;

import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.api.player.ICloudPlayer;
import lombok.SneakyThrows;
import net.wiremc.common.api.CoreAPI;
import net.wiremc.common.api.common.LanguageType;
import net.wiremc.common.api.common.sql.DatabaseEntry;
import net.wiremc.common.api.common.utils.Translator;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Objects;

/**
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 */

public interface CorePlayer {

    default DatabaseEntry sqlEntry() {
        return CoreAPI.getInstance().getCorePlayerManager().getEntry(this.craftPlayer());
    }

    Player craftPlayer();

    default String prefix() {
        return "§8⋆ §e§lWire§6§lMC §8┃ §7";
    }

    default CorePlayer connect(String name) {
        this.cloud().connect(Objects.requireNonNull(CloudAPI.getInstance()
                .getCloudServiceManager()
                .getCloudServiceByName(name)));
        return this;
    }

    default CorePlayer dispatch(Sound sound) {
        this.craftPlayer().playSound(this.craftPlayer().getLocation(), sound, 3, 2);
        return this;
    }

    @SneakyThrows
    default CorePlayer dispatch(String msg, boolean translation) {
        if (translation) {
            LanguageType languageType = LanguageType.valueOf(this
                    .sqlEntry()
                    .receive("lang")
                    .fromHashed());
            this.craftPlayer().sendMessage(Objects.requireNonNull(Translator.Companion
                    .translate("de", languageType.toString(), msg)));
        } else {
            this.craftPlayer().sendMessage(msg);
        }
        return this;
    }

    @SneakyThrows
    default CorePlayer dispatch(String msg) {
        LanguageType languageType = LanguageType.valueOf(this
                .sqlEntry()
                .receive("lang")
                .fromHashed());
        this.craftPlayer().sendMessage(Objects.requireNonNull(Translator.Companion
                .translate("de", languageType.toString(), msg)));
        return this;
    }

    default ICloudPlayer cloud() {
        return CloudAPI
                .getInstance()
                .getCloudPlayerManager()
                .getCachedCloudPlayer(this
                        .craftPlayer()
                        .getUniqueId());
    }

}
