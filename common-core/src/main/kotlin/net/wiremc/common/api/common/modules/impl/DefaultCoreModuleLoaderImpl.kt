package net.wiremc.common.api.common.modules.impl

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import net.wiremc.common.api.CoreAPI
import net.wiremc.common.api.common.modules.Construction
import net.wiremc.common.api.common.modules.CoreModule
import net.wiremc.common.api.common.modules.CoreModuleLoader
import net.wiremc.common.api.common.modules.RawModule
import net.wiremc.common.api.exceptions.common.EmptyModuleException
import org.bukkit.craftbukkit.libs.org.eclipse.sisu.wire.WireModule
import java.io.File
import java.net.URL
import java.net.URLClassLoader
import java.util.*
import java.util.jar.JarFile


/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

class DefaultCoreModuleLoaderImpl: CoreModuleLoader {

    companion object {

        private val vGson: Gson = GsonBuilder().setPrettyPrinting().create()

    }

    private val dirFile: File = File("modules")

    init {
        when(this.dirFile.exists()) {
            false -> this.dirFile.mkdirs()
        }
    }

    override fun getRegisteredModules(): Collection<RawModule> {
        val listOf: MutableList<RawModule> = mutableListOf()
        for (file in this.dirFile.listFiles()!!) {
            if (file.name.endsWith(".jar")) {
                try {
                    val jarFile = JarFile(file)
                    val entry = jarFile.getEntry("construction.json")
                    val inputScanner: Scanner = Scanner(jarFile.getInputStream(entry))
                    val construction: Construction = vGson.fromJson(buildRaw(inputScanner), Construction::class.java)
                    listOf.add(DefaultRawCoreModule(construction, file))
                } catch (ex: EmptyModuleException) {
                    ex.printStackTrace()
                }
            }
        }
        return listOf
    }

    fun loadModules(collection: Collection<RawModule>) {
        for (rawModule in collection) {
            try {
                val cl = URLClassLoader(arrayOf<URL>(rawModule.source().toURL()), ClassLoader.getSystemClassLoader())
                val c = cl.loadClass(rawModule.construction().main)
                val module: CoreModule = c.getConstructor().newInstance() as CoreModule
                module.onHandleEnable(CoreAPI.getInstance().getPlugin())
                CoreAPI.getInstance().getCoreModuleRegistry().insert(rawModule, module)
            } catch (ex: EmptyModuleException) {
                ex.printStackTrace()
            }
        }
    }

    private fun buildRaw(scanner: Scanner): String {
        var l: String = ""
        while (scanner.hasNextLine()) {
            l = l.plus(scanner.nextLine() + "\n")
        }
        return l.substring(0, l.lastIndexOf("\n"))
    }

}