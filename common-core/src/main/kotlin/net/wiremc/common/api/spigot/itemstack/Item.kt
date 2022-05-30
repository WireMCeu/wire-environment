package net.wiremc.common.api.spigot.itemstack

import eu.wiremc.spigot.itemstack.ItemEventType
import net.wiremc.common.api.spigot.itemstack.impl.SimpleItem
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.Event
import org.bukkit.inventory.ItemStack
import java.util.UUID
import java.util.function.Consumer

/**
 *
 * this doc was created on 21.05.2022
 * This class belongs to the wiremc-environment project
 *
 * @author Generix030
 *
 */

interface Item {

    companion object {
        @JvmStatic
        fun insert(): Item = SimpleItem()
    }

    fun amount(i: Int): Item

    fun unbreakable(b: Boolean): Item

    fun url(s: String): Item

    fun name(s: String): Item

    fun data(d: String): Item

    fun enchantment(enchantment: Enchantment, i: Int): Item

    fun handle(type: ItemEventType, action: Consumer<Event>): Item

    fun build(): ItemStack

    fun player(name: String): Item

    fun serialize(): String

    fun lore(s: String): Item

    fun deserialize(d: String): Item

    fun uuid(): UUID

    fun material(mat: Material): Item

    fun toItem(): org.bukkit.entity.Item

}