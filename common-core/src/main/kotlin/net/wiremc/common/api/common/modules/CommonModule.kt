@file:Suppress("MemberVisibilityCanBePrivate")

package net.wiremc.common.api.common.modules

import net.wiremc.common.api.common.context.ContextCoreConfiguration
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

    abstract fun onHandleEnable(plugin: Plugin, configuration: ContextCoreConfiguration)

    abstract fun onHandleUnregister()

    private fun onHandleUnregister0() {
        this.configuration.load()
    }

    private fun onHandleRegister0(configuration: ContextCoreConfiguration, rawModule: RawModule) {
        this.configuration = configuration
        this.rawModule = rawModule
    }

}