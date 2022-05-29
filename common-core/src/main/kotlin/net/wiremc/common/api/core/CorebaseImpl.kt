package net.wiremc.common.api.core

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import eu.wiremc.event.EventRegistry
import eu.wiremc.event.IEventRegistryFactory
import eu.wiremc.event.impl.EventRegistryFactoryImpl
import net.wiremc.common.api.common.event.impl.EventRegistryImpl
import net.wiremc.common.api.CoreAPI
import net.wiremc.common.api.common.LanguageType
import net.wiremc.common.api.common.console.ConsoleProfile
import net.wiremc.common.api.common.console.SimpleConsoleProfile
import net.wiremc.common.api.common.sql.IDatabaseInterface
import net.wiremc.common.api.common.sql.impl.SimpleDatabaseInterface
import net.wiremc.common.api.common.modules.CoreModuleLoader
import net.wiremc.common.api.common.modules.CoreModuleRegistry
import net.wiremc.common.api.common.modules.impl.DefaultCoreModuleLoaderImpl
import net.wiremc.common.api.common.modules.impl.DefaultCoreModuleRegistry
import net.wiremc.common.api.common.sql.DatabaseUnit
import net.wiremc.common.api.core.profile.CorePlayerManager
import net.wiremc.common.api.core.profile.impl.SimpleCorePlayerManager
import net.wiremc.common.api.internal.InternalCoreHandler
import net.wiremc.common.api.spigot.protocol.ProtocolManager
import net.wiremc.common.api.spigot.protocol.impl.ProtocolManagerImpl
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

class CorebaseImpl(private val plugin: Plugin): CoreAPI {

    init {
        CoreAPI.instance(this)
    }

    private val gson: Gson = GsonBuilder().create()
    private val eventFactory: IEventRegistryFactory = EventRegistryFactoryImpl()
    private val mainEventRegistry: EventRegistry = EventRegistryImpl()
    private val protocolManager: ProtocolManager = ProtocolManagerImpl()
    private val consoleProfile: ConsoleProfile = SimpleConsoleProfile()
    private val databaseInterface: IDatabaseInterface = SimpleDatabaseInterface(this).connect("core_db")
    private val coreModuleRegistry: CoreModuleRegistry = DefaultCoreModuleRegistry()
    private val coreModuleLoader: CoreModuleLoader = DefaultCoreModuleLoaderImpl()
    private val corePlayerUnit: DatabaseUnit = DatabaseUnit.unit(coreAPI = this)
    private val corePlayerManager: CorePlayerManager = SimpleCorePlayerManager()
    private val completableFutureExecutor: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        this.corePlayerUnit
            .table("core_player")
            .defaults(listOf("").toMutableList())
            .columns()
            .dispatchUnit()
        this.corePlayerUnit.onInsertNew() {
            it.insert("lang", LanguageType.ENGLISH.name)
        }
        this.coreModuleLoader
            .loadModules(this.coreModuleLoader
                .getRegisteredModules())
        InternalCoreHandler(this)
    }

    override fun getPlugin(): Plugin = this.plugin

    override fun getEventFactory(): IEventRegistryFactory = this.eventFactory

    override fun getCoreEventRegistry(): EventRegistry = this.mainEventRegistry

    override fun getGson(): Gson = this.gson

    override fun getProtocolManager(): ProtocolManager = this.protocolManager

    override fun getDatabase(): IDatabaseInterface = this.databaseInterface

    override fun getCoreConsole(): ConsoleProfile = this.consoleProfile

    override fun getCoreModuleRegistry(): CoreModuleRegistry = this.coreModuleRegistry

    override fun getCorePlayerUnit(): DatabaseUnit = this.corePlayerUnit

    override fun getCorePlayerManager(): CorePlayerManager = this.corePlayerManager

    override fun getCompletableFutureExecutor(): ExecutorService = this.completableFutureExecutor

}