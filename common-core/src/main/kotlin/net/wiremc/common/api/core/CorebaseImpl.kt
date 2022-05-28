package net.wiremc.common.api.core

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import eu.wiremc.event.EventRegistry
import eu.wiremc.event.IEventRegistryFactory
import eu.wiremc.event.impl.EventRegistryFactoryImpl
import net.wiremc.common.api.common.event.impl.EventRegistryImpl
import net.wiremc.common.api.CoreAPI
import net.wiremc.common.api.common.console.ConsoleProfile
import net.wiremc.common.api.common.console.SimpleConsoleProfile
import net.wiremc.common.api.common.database.IDatabaseInterface
import net.wiremc.common.api.common.database.impl.SimpleDatabaseInterface
import net.wiremc.common.api.common.modules.CoreModuleLoader
import net.wiremc.common.api.common.modules.CoreModuleRegistry
import net.wiremc.common.api.common.modules.impl.DefaultCoreModuleLoaderImpl
import net.wiremc.common.api.common.modules.impl.DefaultCoreModuleRegistry
import net.wiremc.common.api.common.modules.impl.DefaultRawCoreModule
import net.wiremc.common.api.spigot.protocol.ProtocolManager
import net.wiremc.common.api.spigot.protocol.impl.ProtocolManagerImpl
import org.bukkit.plugin.Plugin

/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

class CorebaseImpl(private val plugin: Plugin): CoreAPI {

    private val gson: Gson = GsonBuilder().create()
    private val eventFactory: IEventRegistryFactory = EventRegistryFactoryImpl()
    private val mainEventRegistry: EventRegistry = EventRegistryImpl()
    private val protocolManager: ProtocolManager = ProtocolManagerImpl()
    private val consoleProfile: ConsoleProfile = SimpleConsoleProfile()
    private val databaseInterface: IDatabaseInterface = SimpleDatabaseInterface("core_db")
    private val coreModuleRegistry: CoreModuleRegistry = DefaultCoreModuleRegistry()
    private val coreModuleLoader: CoreModuleLoader = DefaultCoreModuleLoaderImpl()

    init {
        this.coreModuleLoader
            .loadModules(this.coreModuleLoader
                .getRegisteredModules())
    }

    override fun getPlugin(): Plugin = this.plugin

    override fun getEventFactory(): IEventRegistryFactory = this.eventFactory

    override fun getCoreEventRegistry(): EventRegistry = this.mainEventRegistry

    override fun getGson(): Gson = this.gson

    override fun getProtocolManager(): ProtocolManager = this.protocolManager

    override fun getDatabase(): IDatabaseInterface = this.databaseInterface

    override fun getCoreConsole(): ConsoleProfile = this.consoleProfile

    override fun getCoreModuleRegistry(): CoreModuleRegistry = this.coreModuleRegistry

}