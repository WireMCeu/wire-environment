package net.wiremc.common.api.spigot.protocol.impl

import net.wiremc.common.api.spigot.protocol.ProtocolHandler
import net.wiremc.common.api.spigot.protocol.ProtocolManager
import org.bukkit.entity.Player

/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

class ProtocolManagerImpl: ProtocolManager {

    override fun handler(player: Player): ProtocolHandler {
        return ProtocolHandlerImpl(player)
    }

}