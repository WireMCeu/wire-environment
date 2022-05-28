package net.wiremc.common.api.common.modules

import org.bukkit.plugin.Plugin

/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

abstract class CoreModule {

    abstract fun onHandleEnable(plugin: Plugin)

}