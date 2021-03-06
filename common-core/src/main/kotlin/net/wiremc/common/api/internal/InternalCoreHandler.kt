package net.wiremc.common.api.internal

import net.minecraft.server.v1_16_R3.Items.it
import net.wiremc.common.api.CoreAPI
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.weather.WeatherChangeEvent

/**
 *
 * this doc was created on 29.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

final class InternalCoreHandler(coreAPI: CoreAPI) {

    init {
        coreAPI.getCoreEventRegistry().subscribe(EventPriority.LOWEST ,PlayerJoinEvent::class.java) {
            it.joinMessage = null
            coreAPI.getCorePlayerManager().register(player = it.player)
        }
        coreAPI.getCoreEventRegistry().subscribe(WeatherChangeEvent::class.java) {
            it.isCancelled = false
        }
    }

}