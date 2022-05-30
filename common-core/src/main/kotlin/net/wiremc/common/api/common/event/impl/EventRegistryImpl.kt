package net.wiremc.common.api.common.event.impl

import eu.wiremc.event.EventRegistry
import net.wiremc.common.api.CoreAPI
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import java.util.function.Consumer

/**
 *
 * this doc was created on 21.05.2022
 * This class belongs to the wiremc-environment project
 *
 * @author Generix030
 *
 */

class EventRegistryImpl: EventRegistry {

    override fun <T : Event> subscribe(clazz: Class<T>, action: Consumer<T>): EventRegistry {
        val listener: Listener = object : Listener {}
        CoreAPI.getInstance().getPlugin().server.pluginManager.registerEvent(clazz, listener, EventPriority.NORMAL,
            { _: Listener?, e: Event ->
                @Suppress("UNCHECKED_CAST")
                if (clazz.isInstance(e)) action.accept(e as T)
            }, CoreAPI.getInstance().getPlugin(), false)
        return this
    }

    override fun <T : Event> subscribe(priority: EventPriority, clazz: Class<T>, action: Consumer<T>): EventRegistry {
        val listener: Listener = object : Listener {}
        CoreAPI.getInstance().getPlugin().server.pluginManager.registerEvent(clazz, listener, priority,
            { _: Listener?, e: Event ->
                @Suppress("UNCHECKED_CAST")
                if (clazz.isInstance(e)) action.accept(e as T)
            }, CoreAPI.getInstance().getPlugin(), false)
        return this
    }

}