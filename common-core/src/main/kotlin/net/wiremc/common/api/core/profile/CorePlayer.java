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
        return "§8⋆ " + ColorType.valueOf(this
                .sqlEntry()
                .receive("mColor")
                .fromHashed()) + "§lWire" + ColorType
                .valueOf(this
                        .sqlEntry()
                        .receive("sColor")
                .fromHashed()) + "§lMC §8┃ §7";
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
            this.craftPlayer().sendMessage(this.prefix() + this.output(msg));
        } else {
            this.craftPlayer().sendMessage(this.prefix() + this.output(msg, true));
        }
        return this;
    }

    @SneakyThrows
    default CorePlayer dispatch(String msg) {
        this.craftPlayer().sendMessage(this.prefix() + this.output(msg));
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

    @SneakyThrows
    default String output(String msg) {
        return Translator
                .Companion
                .translate(
                        "de",
                        this.sqlEntry()
                                .receive("lang")
                                .fromHashed(), msg.replace("&", "§")
                                .replace("§e", ColorType.valueOf(this
                                                .sqlEntry()
                                                .receive("mColor")
                                                .fromHashed())
                                        .getColor())
                                .replace("§6", ColorType.valueOf(this
                                                .sqlEntry()
                                                .receive("sColor")
                                                .fromHashed())
                                        .getColor()));
    }

    @SneakyThrows
    default String output(String msg, boolean ignoreTranslation) {
        if (!ignoreTranslation) {
            return Translator
                    .Companion
                    .translate(
                            "de",
                            this.sqlEntry()
                                    .receive("lang")
                                    .fromHashed(), msg.replace("&", "§")
                    .replace("§e", ColorType.valueOf(this
                                    .sqlEntry()
                                    .receive("mColor")
                                    .fromHashed())
                            .getColor())
                    .replace("§6", ColorType.valueOf(this
                                    .sqlEntry()
                                    .receive("sColor")
                                    .fromHashed())
                            .getColor()));
        }
        return  msg
                .replace("&", "§")
                .replace("§e", ColorType.valueOf(this
                        .sqlEntry()
                        .receive("mColor")
                        .fromHashed())
                        .getColor())
                .replace("§6", ColorType.valueOf(this
                        .sqlEntry()
                        .receive("sColor")
                        .fromHashed())
                        .getColor());
    }

}
