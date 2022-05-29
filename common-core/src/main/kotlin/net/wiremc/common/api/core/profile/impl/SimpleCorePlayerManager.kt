package net.wiremc.common.api.core.profile.impl

import net.wiremc.common.api.CoreAPI
import net.wiremc.common.api.common.sql.DatabaseEntry
import net.wiremc.common.api.core.profile.CorePlayer
import net.wiremc.common.api.core.profile.CorePlayerManager
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer
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

    private val registry: MutableMap<String, CorePlayer> = mutableMapOf()

    override fun getEntry(player: Player): DatabaseEntry? {
        return this.registry[player.uniqueId.toString()]!!.sqlEntry()
    }

    override fun register(player: Player): CorePlayer {
        val core: CorePlayer = CorePlayerImpl(player)
        CoreAPI
            .getInstance()
            .getCorePlayerUnit()
            .newEntry(player.uniqueId.toString())
        this.registry[player.uniqueId.toString()] = core
        return core
    }

    override fun getCorePlayer(player: Player): CorePlayer {
        return this.registry[player.uniqueId.toString()]!!
    }

}