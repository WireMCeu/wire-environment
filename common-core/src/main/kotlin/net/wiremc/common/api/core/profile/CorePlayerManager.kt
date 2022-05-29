package net.wiremc.common.api.core.profile

import net.wiremc.common.api.common.sql.DatabaseEntry
import org.bukkit.entity.Player

/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

interface CorePlayerManager {

    fun getEntry(player: Player): DatabaseEntry?

    fun register(player: Player): CorePlayer 

}