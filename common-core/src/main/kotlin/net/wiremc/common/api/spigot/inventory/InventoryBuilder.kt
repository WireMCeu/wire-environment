package net.wiremc.common.api.spigot.inventory

import net.wiremc.common.api.core.profile.CorePlayer
import org.bukkit.Material
import org.bukkit.inventory.Inventory

/**
 *
 * this doc was created on 30.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

interface InventoryBuilder {

    companion object {
        fun create(): InventoryBuilder {
            return InventoryBuilderImpl()
        }
    }

    fun name(str: String): InventoryBuilder

    fun size(i: Int): InventoryBuilder

    fun placeholder(): InventoryBuilder

    fun icon(material: Material): InventoryBuilder

    fun build(): Inventory

    fun build(corePlayer: CorePlayer): Inventory

    fun build(corePlayer: CorePlayer, ignoreTranslation: Boolean): Inventory

}