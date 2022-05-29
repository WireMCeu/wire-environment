package net.wiremc.common.api.bootstrap

import net.wiremc.common.api.CoreAPI
import net.wiremc.common.api.core.CorebaseImpl
import org.bukkit.plugin.java.JavaPlugin

/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

class Bootstrap: JavaPlugin() {

    override fun onEnable() {
        CorebaseImpl(this)
    }

    override fun onDisable() {

    }

}