package net.wiremc.common.api.spigot.itemstack.impl

import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import net.wiremc.common.api.spigot.itemstack.Item
import eu.wiremc.spigot.itemstack.ItemEventType
import net.wiremc.common.api.CoreAPI
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.Event
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.SkullMeta
import java.lang.reflect.Field
import java.util.*
import java.util.function.Consumer

/**
 *
 * this doc was created on 21.05.2022
 * This class belongs to the wiremc-environment project
 *
 * @author Generix030
 *
 */

class SimpleItem: Item {

    companion object {

        @JvmStatic
        var registry: MutableMap<DefaultItemFunction<Item, ItemEventType>, MutableList<Consumer<Event>>> = mutableMapOf()

    }

    private var itemData: ItemData = ItemData(this)

    private var uid: UUID = UUID.randomUUID()

    override fun build(): ItemStack {
        var stack: ItemStack = ItemStack(this.itemData.material, this.itemData.amount)
        val meta: ItemMeta = stack.itemMeta!!
        meta.setDisplayName(this.itemData.name)
        for (s in this.itemData.lores) meta.lore!!.add(s)
        for (entry in this.itemData.enchantments) meta.enchants[Enchantment.getByName(entry.key)] = entry.value
        if (this.itemData.playerName != null || this.itemData.url != null) {
            stack = ItemStack(Material.LEGACY_SKULL_ITEM, 1, 3.toShort())
            val itemMeta: SkullMeta = stack.itemMeta as SkullMeta
            if (itemData.url != null) {
                val profile = GameProfile(UUID.randomUUID(), null)
                val encodedData: ByteArray = Base64
                    .getEncoder()
                    .encode(String
                        .format("{textures:{SKIN:{url:\"%s\"}}}", this
                            .itemData
                            .url)
                        .toByteArray())
                profile.properties.put("textures", Property("textures", String(encodedData)))
                var profileField: Field? = null
                try {
                    profileField = itemMeta.javaClass.getDeclaredField("profile")
                    profileField.isAccessible = true
                    profileField[itemMeta] = profile
                } catch (e1: Exception) {
                    e1.printStackTrace()
                }
            } else {
                itemMeta.owner = this.itemData.playerName
            }
        }
        stack.itemMeta = meta
        return stack
    }

    override fun amount(i: Int): Item {
        this.itemData.amount = i
        return this
    }

    override fun unbreakable(b: Boolean): Item {
        this.itemData.unbreakable = b
        return this
    }

    override fun url(s: String): Item {
        this.itemData.url = s
        return this
    }

    override fun name(s: String): Item {
        this.itemData.name = s
        return this
    }

    override fun data(d: String): Item {
        this.itemData.data = d
        return this
    }

    override fun enchantment(enchantment: Enchantment, i: Int): Item {
        this.itemData.enchantments[enchantment.name] = i
        return this
    }

    override fun handle(type: ItemEventType, action: Consumer<Event>): Item {
        val f: DefaultItemFunction<Item, ItemEventType> = DefaultItemFunction(this, type)
        if (!registry.containsKey(f)) {
            registry[f] = mutableListOf()
            registry[f]?.add(action)
        } else {
            registry[f]?.add(action)
        }
        return this
    }

    override fun player(name: String): Item {
        this.itemData.playerName = name
        return this
    }

    override fun serialize(): String {
        return CoreAPI.getInstance().getGson().toJson(this.itemData)
    }

    override fun lore(s: String): Item {
        val mList = this.itemData.lores.toMutableList()
        mList.add(s)
        this.itemData.lores = mList.toTypedArray()
        return this
    }

    override fun deserialize(d: String): Item {
        this.itemData = CoreAPI.getInstance().getGson().fromJson(d, ItemData::class.java)
        return this
    }

    override fun uuid(): UUID {
        return this.uid
    }

    override fun toItem(): org.bukkit.entity.Item {
        TODO("Not yet implemented")
    }
}