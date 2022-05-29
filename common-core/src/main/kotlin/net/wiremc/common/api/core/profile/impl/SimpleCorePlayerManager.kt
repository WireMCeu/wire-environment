package net.wiremc.common.api.core.profile.impl

import net.wiremc.common.api.CoreAPI
import net.wiremc.common.api.common.sql.DatabaseEntry
import net.wiremc.common.api.core.profile.CorePlayer
import net.wiremc.common.api.core.profile.CorePlayerManager
import org.bukkit.entity.Player
import java.util.Arrays

/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

class SimpleCorePlayerManager: CorePlayerManager {

    private val registry: MutableMap<String, DatabaseEntry> = mutableMapOf()

    override fun getEntry(player: Player): DatabaseEntry? {
        return this.registry[player.uniqueId.toString()]
    }

    override fun register(player: Player): CorePlayer {
        this.registry[player.uniqueId.toString()] = CoreAPI
            .getInstance()
            .getCorePlayerUnit()
            .newEntry(player.uniqueId.toString())
        return player as CorePlayer
    }

}