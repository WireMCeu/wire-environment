package net.wiremc.common.api.core.profile.impl

import net.wiremc.common.api.core.profile.CorePlayer
import org.bukkit.entity.Player

/**
 *
 * this doc was created on 29.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

class CorePlayerImpl(private val player: Player): CorePlayer {
    override fun craftPlayer(): Player {
        return this.player
    }
}