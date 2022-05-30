package net.wiremc.common.api.spigot.inventory

import net.wiremc.common.api.core.profile.CorePlayer
import net.wiremc.common.api.spigot.itemstack.Item
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

/**
 *
 * this doc was created on 30.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

class InventoryBuilderImpl: InventoryBuilder {

    companion object {
        private val item: ItemStack = Item.insert().name(" ").material(Material.GRAY_STAINED_GLASS_PANE).build()
    }

    private var size: Int = 45
    private var icon: Material? = null
    private var placeholder: Boolean = true
    private var title: String = " "

    override fun name(str: String): InventoryBuilder {
        this.title = str
        return this
    }

    override fun size(i: Int): InventoryBuilder {
        this.size = i
        return this
    }

    override fun placeholder(): InventoryBuilder {
        this.placeholder = false
        return this
    }

    override fun icon(material: Material): InventoryBuilder {
        this.icon = material
        return this
    }

    override fun build(): Inventory {
        val inventory: Inventory = Bukkit.createInventory(null, this.size, this.title)
        if (this.placeholder) {
            for (x in 0..8) {
                inventory.setItem(x, item)
            }
            for (x in inventory.size - 1 downTo inventory.size - 9) {
                inventory.setItem(x, item)
            }
        }
        if (this.icon != null) {
            inventory.setItem(4, Item.insert().name(" ").material(this.icon!!).build())
        }
        return inventory
    }

    override fun build(corePlayer: CorePlayer): Inventory {
        val inventory: Inventory = Bukkit.createInventory(null, this.size, corePlayer
            .output("§8§l» §e§l" + this.title))
        if (this.placeholder) {
            for (x in 0..8) {
                inventory.setItem(x, item)
            }
            for (x in inventory.size - 1 downTo inventory.size - 9) {
                inventory.setItem(x, item)
            }
        }
        if (this.icon != null) {
            inventory.setItem(4, Item.insert().name(" ").material(this.icon!!).build())
        }
        return inventory
    }

    override fun build(corePlayer: CorePlayer, ignoreTranslation: Boolean): Inventory {
        val inventory: Inventory = Bukkit.createInventory(null, this.size, corePlayer
            .output("§8§l» §e§l" + this.title, ignoreTranslation))
        if (this.placeholder) {
            for (x in 0..8) {
                inventory.setItem(x, item)
            }
            for (x in inventory.size - 1 downTo inventory.size - 9) {
                inventory.setItem(x, item)
            }
        }
        if (this.icon != null) {
            inventory.setItem(4, Item.insert().name(" ").material(this.icon!!).build())
        }
        return inventory
    }

}