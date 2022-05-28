package net.wiremc.common.api.core.profile;

import eu.thesimplecloud.api.CloudAPI;
import eu.thesimplecloud.api.player.ICloudPlayer;
import eu.thesimplecloud.api.player.impl.CloudPlayer;
import lombok.SneakyThrows;
import net.wiremc.common.api.CoreAPI;
import net.wiremc.common.api.common.LanguageType;
import net.wiremc.common.api.common.Translator;
import net.wiremc.common.api.common.database.IDatabaseEntry;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Objects;

/**
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 */

public interface CorePlayer extends Player {

    default IDatabaseEntry sqlEntry() {
        return CoreAPI.getInstance().getCorePlayerSQLSection().entry(this.getUniqueId().toString());
    }

    default CorePlayer connect(String name) {
        this.cloud().connect(Objects.requireNonNull(CloudAPI.getInstance().getCloudServiceManager().getCloudServiceByName(name)));
        return this;
    }

    default CorePlayer dispatch(Sound sound) {
        this.playSound(this.getLocation(), sound, 3, 2);
        return this;
    }

    @SneakyThrows
    default CorePlayer dispatch(String msg, boolean translation) {
        if (translation) {
            LanguageType languageType = LanguageType.valueOf(this.sqlEntry().property("lang"));
            this.sendMessage(Objects.requireNonNull(Translator.Companion.translate("de", languageType.toString(), msg)));
        } else {
            this.sendMessage(msg);
        }
        return this;
    }

    @SneakyThrows
    default CorePlayer dispatch(String msg) {
        LanguageType languageType = LanguageType.valueOf(this.sqlEntry().property("lang"));
        this.sendMessage(Objects.requireNonNull(Translator.Companion.translate("de", languageType.toString(), msg)));
        return this;
    }

    default ICloudPlayer cloud() {
        return CloudAPI.getInstance().getCloudPlayerManager().getCachedCloudPlayer(this.getUniqueId());
    }

}
