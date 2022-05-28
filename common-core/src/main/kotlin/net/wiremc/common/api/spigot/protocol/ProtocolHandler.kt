package net.wiremc.common.api.spigot.protocol

import net.minecraft.server.v1_16_R3.Packet
import net.wiremc.common.api.spigot.protocol.impl.ProtocolHandlerImpl
import org.bukkit.entity.Player

/**
 *
 * this doc was created on 23.05.2022
 * This class belongs to the wiremc-workspace project
 *
 * @author Generix030
 *
 */

interface ProtocolHandler {

    companion object {
        @JvmStatic
        fun handler(player: Player): ProtocolHandler {
            return ProtocolHandlerImpl(player)
        }

    }

    fun inject()

    fun dispatchPacket(packet: Packet<*>)

    fun handle(handler: IncomingHandler<Packet<*>>): ProtocolHandler

    fun player(): Player

}