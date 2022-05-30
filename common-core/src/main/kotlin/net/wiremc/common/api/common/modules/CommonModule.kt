@file:Suppress("MemberVisibilityCanBePrivate")

package net.wiremc.common.api.common.modules

import eu.wiremc.event.EventRegistry
import net.wiremc.common.api.CoreAPI
import net.wiremc.common.api.common.context.ContextCoreConfiguration
import net.wiremc.common.api.common.event.impl.EventRegistryImpl
import org.bukkit.plugin.Plugin

/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

abstract class CommonModule {

    protected lateinit var configuration: ContextCoreConfiguration
    protected lateinit var rawModule: RawModule
    private val eventRegistry: EventRegistry = CoreAPI.getInstance().getCoreEventRegistry()

    abstract fun onHandleEnable(plugin: Plugin, configuration: ContextCoreConfiguration)

    abstract fun onHandleUnregister()

    private fun onHandleUnregister0() {
        this.configuration.load()
    }

    fun onHandleRegister0(configuration: ContextCoreConfiguration, rawModule: RawModule) {
        this.configuration = configuration
        this.rawModule = rawModule
    }

    fun getCoreConfiguration(): ContextCoreConfiguration = this.configuration

    fun getEventRegistry(): EventRegistry = this.eventRegistry

}