package net.wiremc.common.api.common.console

import net.wiremc.common.api.common.console.ConsoleProfile
import net.wiremc.common.api.common.console.MSG
import org.bukkit.Bukkit

/**
 *
 * this doc was created on 21.05.2022
 * This class belongs to the wiremc-workspace project
 *
 * @author Generix030
 *
 */

class SimpleConsoleProfile: ConsoleProfile {

    override fun write(msg: String, type: MSG): ConsoleProfile {
        when (type) {
            MSG.INFO -> {
                Bukkit.getConsoleSender().sendMessage("§aINFO » $msg")
            }
            MSG.ERROR -> {
                Bukkit.getConsoleSender().sendMessage("§cERROR » §c$msg")
            }
        }
        return this
    }

}