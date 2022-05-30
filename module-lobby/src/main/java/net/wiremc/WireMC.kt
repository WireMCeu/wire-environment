package net.wiremc

import net.wiremc.common.api.common.context.ContextCoreConfiguration
import net.wiremc.common.api.common.modules.CommonModule
import net.wiremc.events.user.PlayerJoinListener
import org.bukkit.plugin.Plugin

/**
 *
 * this doc was created on 30.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

class WireMC: CommonModule() {

    companion object {
        private lateinit var instance: WireMC
        @JvmStatic
        fun getInstance(): WireMC {
            return this.instance
        }
    }

    override fun onHandleEnable(plugin: Plugin, configuration: ContextCoreConfiguration) {
        instance = this

        PlayerJoinListener()

    }

    override fun onHandleUnregister() {
        TODO("Not yet implemented")
    }

}