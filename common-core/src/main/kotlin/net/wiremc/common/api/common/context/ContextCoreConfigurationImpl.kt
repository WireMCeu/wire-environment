@file:Suppress("UNCHECKED_CAST")

package net.wiremc.common.api.common.context

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import net.wiremc.common.api.common.modules.RawModule
import org.apache.commons.io.FileUtils
import java.io.File

/**
 * this java doc created on 29.05.2022
 * this file belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

class ContextCoreConfigurationImpl(private val rawModule: RawModule): ContextCoreConfiguration {

    companion object {
        private val gson: Gson = GsonBuilder().setPrettyPrinting().create()
    }

    private val remoteFile: File = File("modules//" + rawModule.construction().name + "//configuration.json")
    private lateinit var hashedCatcher: HashMap<String, Any>

    init {
        if (!this.remoteFile.parentFile.exists()) this.remoteFile.parentFile.mkdirs()
        if (!this.remoteFile.exists()) {
            this.remoteFile.createNewFile()
        } else {
            this.hashedCatcher = gson
                .fromJson(FileUtils
                    .readFileToString(this.remoteFile),
                    HashMap::class.java) as HashMap<String, Any>
        }
    }

    override fun <T> insert(property: String, any: T): ContextCoreConfiguration {
        this.hashedCatcher[property] = any!!
        return this
    }

    override fun clear(): ContextCoreConfiguration {
        this.hashedCatcher.clear()
        return this
    }

    override fun <T> get(property: String): T {
        return this.hashedCatcher[property] as T
    }

    override fun remove(property: String): ContextCoreConfiguration {
        this.hashedCatcher.remove(property)
        return this
    }

    override fun file(): File {
        return this.remoteFile
    }

    override fun load(): ContextCoreConfiguration {
        FileUtils.write(this.remoteFile, gson
            .toJson(this.hashedCatcher))
        return this
    }

}