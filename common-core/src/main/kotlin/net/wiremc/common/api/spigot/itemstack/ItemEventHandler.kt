@file:Suppress("ReplaceCallWithBinaryOperator")

package net.wiremc.common.api.spigot.itemstack

import eu.wiremc.spigot.itemstack.ItemEventType
import net.wiremc.common.api.spigot.itemstack.impl.SimpleItem
import net.wiremc.common.api.CoreAPI
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEvent

/**
 *
 * this doc was created on 21.05.2022
 * This class belongs to the wiremc-environment project
 *
 * @author Generix030
 *
 */

class ItemEventHandler {

    init {
        CoreAPI.getInstance().getCoreEventRegistry().subscribe(InventoryClickEvent::class.java) {
            val event: InventoryClickEvent = it;
            try {
                for (entry in SimpleItem.registry) {
                    if (event.currentItem!!.equals(entry.key.t)) {
                        if (entry.key.k == ItemEventType.INVENTORY_CLICK) {
                            for (consumer in entry.value) {
                                consumer.accept(event)
                            }
                        }
                    }
                }
            } catch (_: java.lang.NullPointerException) {
            }
        }
        CoreAPI.getInstance().getCoreEventRegistry().subscribe(PlayerInteractEvent::class.java) {
            val event: PlayerInteractEvent = it;
            try {
                for (entry in SimpleItem.registry) {
                    if (event.item!!.equals(entry.key.t)) {
                        if (entry.key.k == ItemEventType.PLAYER_INTERACT) {
                            for (consumer in entry.value) {
                                consumer.accept(event)
                            }
                        }
                    }
                }
            } catch (_: java.lang.NullPointerException) {
            }
        }
    }

}