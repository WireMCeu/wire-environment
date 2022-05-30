package net.wiremc.events.user

import net.wiremc.WireMC
import net.wiremc.common.api.CoreAPI
import net.wiremc.common.api.common.LanguageType
import net.wiremc.common.api.core.profile.CorePlayer
import net.wiremc.common.api.spigot.inventory.InventoryBuilder
import net.wiremc.common.api.spigot.itemstack.Item
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.Inventory

/**
 *
 * this doc was created on 30.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

class PlayerJoinListener {

    private val spawn = Location(Bukkit.getWorld("world"),0.5, 68.0, 0.5, 0.0F, (-3.0).toFloat())

    init {
        WireMC.getInstance().getEventRegistry().subscribe(PlayerJoinEvent::class.java) {
            val player: Player = it.player
            val corePlayer: CorePlayer = CoreAPI
                .getInstance()
                .getCorePlayerManager()
                .getCorePlayer(player)
            player.teleport(this.spawn)
            player.health = 20.0
            player.foodLevel = 20
            player.level = 2022
            corePlayer.dispatch("Willkommen auf §6WireMC")
            if (corePlayer.sqlEntry().firstInsert()) {
                val inventory: Inventory = InventoryBuilder.create().name("Sprache").icon(Material.BOOK).build(corePlayer)
                for ((i, value) in LanguageType.values().withIndex()) {
                    inventory.setItem(i+9, Item.insert().url(value.url).name(corePlayer.output("§8◯ §e" + value.name.toLowerCase(), false)).build())
                }
                player.openInventory(inventory)
                corePlayer.dispatch(Sound.BLOCK_ENDER_CHEST_OPEN)
            }
        }
    }

}