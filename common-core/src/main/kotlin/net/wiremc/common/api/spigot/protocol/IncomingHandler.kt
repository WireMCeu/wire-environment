package net.wiremc.common.api.spigot.protocol

/**
 *
 * this doc was created on 23.05.2022
 * This class belongs to the wiremc-workspace project
 *
 * @author Generix030
 *
 */

interface IncomingHandler<T> {

    fun handle(msg: T)

}