package net.wiremc.common.api.spigot.protocol.impl

import net.wiremc.common.api.spigot.protocol.IncomingHandler
import net.wiremc.common.api.spigot.protocol.ProtocolHandler
import io.netty.channel.Channel
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import net.minecraft.server.v1_16_R3.Packet
import net.minecraft.server.v1_16_R3.PlayerConnection
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer
import org.bukkit.entity.Player

/**
 *
 * this doc was created on 23.05.2022
 * This class belongs to the wiremc-workspace project
 *
 * @author Generix030
 *
 */

open class ProtocolHandlerImpl(private val player: Player): ProtocolHandler {

    private val craftPlayer: CraftPlayer = player as CraftPlayer;
    private val connection: PlayerConnection? = craftPlayer.handle.playerConnection
    private val networkManager = connection!!.networkManager
    private val channel: Channel = networkManager.javaClass.getField("channel").get(networkManager) as Channel;
    
    protected val handlers: MutableList<IncomingHandler<Packet<*>>> = mutableListOf()

    override fun inject() {
        channel.pipeline().addAfter("decoder", "PacketInjector", object : MessageToMessageDecoder<Packet<*>>() {
            @Throws(Exception::class)
            override fun decode(arg0: ChannelHandlerContext, packet: Packet<*>, arg2: MutableList<Any>) {
                arg2.add(packet)
                for (handler in handlers) handler.handle(packet)
            }
        })
    }

    override fun dispatchPacket(packet: Packet<*>) {
        this.connection?.sendPacket(packet)
    }

    override fun handle(handler: IncomingHandler<Packet<*>>): ProtocolHandler {
        this.handlers.add(handler)
        return this
    }

    override fun player(): Player = this.player


}