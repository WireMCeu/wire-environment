package net.wiremc.common.api.spigot.itemstack.impl

import org.bukkit.Material

/**
 *
 * this doc was created on 21.05.2022
 * This class belongs to the wiremc-environment project
 *
 * @author Generix030
 *
 */

class ItemData(private val item: SimpleItem) {

    var amount: Int = 1

    var name: String = "item"

    var data: String = "item"

    var playerName: String? = null

    var url: String? = null

    var material: Material = Material.DIRT

    var unbreakable: Boolean = true

    val enchantments: MutableMap<String, Int> = mutableMapOf()

    var lores: Array<String> = emptyArray()

}