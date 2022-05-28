package net.wiremc.common.api.spigot.protocol

import net.wiremc.common.api.spigot.protocol.ProtocolHandler
import org.bukkit.entity.Player

/**
 *
 * this doc was created on 23.05.2022
 * This class belongs to the wiremc-workspace project
 *
 * @author Generix030
 *
 */

interface ProtocolManager {

    fun handler(player: Player): ProtocolHandler

}