package eu.wiremc.event

import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import java.util.function.Consumer

/**
 *
 * this doc was created on 21.05.2022
 * This class belongs to the wiremc-environment project
 *
 * @author Generix030
 *
 */

interface EventRegistry {

    fun<T: Event> subscribe(clazz: Class<T>, action: Consumer<T>): EventRegistry

    fun<T: Event> subscribe(priority: EventPriority, clazz: Class<T>, action: Consumer<T>): EventRegistry

}