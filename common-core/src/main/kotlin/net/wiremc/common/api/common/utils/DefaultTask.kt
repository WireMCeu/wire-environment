package net.wiremc.common.api.common.utils

/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

interface DefaultTask<T> {

    fun handle(): T

}