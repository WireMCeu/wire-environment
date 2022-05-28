package eu.wiremc.spigot.protocol.reflections

import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 *
 * this doc was created on 23.05.2022
 * This class belongs to the wiremc-workspace project
 *
 * @author Generix030
 *
 */

@Suppress("UNCHECKED_CAST")
abstract class ReflectionUtils {

    companion object {

        fun<T> get(_object: Any, field: String): T {
            val remoteField: Field = _object.javaClass.getDeclaredField(field)
            if (!remoteField.isAccessible) remoteField.isAccessible = true
            return remoteField.get(_object) as T
        }

        fun<T> insert(_object: Any, field: String, _in: T) {
            val remoteField: Field = _object.javaClass.getDeclaredField(field)
            if (!remoteField.isAccessible) remoteField.isAccessible = true
            remoteField.set(_object, _in) as T
        }

        fun invoke(_object: Any, field: String, vararg parameters: Any): Any {
            var remoteMethod: Method? = null
            for (method in _object.javaClass.declaredMethods) {
                if (method.name.equals(field ,ignoreCase = true)) remoteMethod = method
                break;
            }
            if (!remoteMethod!!.isAccessible) remoteMethod.isAccessible = true
            return remoteMethod.invoke(_object, parameters)
        }

    }

}