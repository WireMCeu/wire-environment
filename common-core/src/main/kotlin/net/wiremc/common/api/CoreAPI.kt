package net.wiremc.common.api

import com.google.gson.Gson
import eu.wiremc.event.EventRegistry
import eu.wiremc.event.IEventRegistryFactory
import net.wiremc.common.api.common.console.ConsoleProfile
import net.wiremc.common.api.common.database.IDatabaseInterface
import net.wiremc.common.api.common.modules.CoreModuleRegistry
import net.wiremc.common.api.spigot.protocol.ProtocolManager
import org.bukkit.plugin.Plugin

/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

interface CoreAPI {

    companion object {

        private lateinit var instance: CoreAPI

        @JvmStatic
        fun getInstance(): CoreAPI = instance

        fun instance(coreAPI: CoreAPI) {
            this.instance = coreAPI
        }

    }

    fun getPlugin(): Plugin

    fun getEventFactory(): IEventRegistryFactory

    fun getCoreEventRegistry(): EventRegistry

    fun getGson(): Gson

    fun getProtocolManager(): ProtocolManager

    fun getDatabase(): IDatabaseInterface

    fun getCoreConsole(): ConsoleProfile

    fun getCoreModuleRegistry(): CoreModuleRegistry

}