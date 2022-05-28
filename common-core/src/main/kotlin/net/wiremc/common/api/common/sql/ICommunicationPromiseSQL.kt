package net.wiremc.common.api.common.sql

/**
 *
 * this doc was created on 28.05.2022
 * This class belongs to the wire-environment project
 *
 * @author Generix030
 *
 */

interface ICommunicationPromiseSQL<T> {

    fun get(): T

    fun future(): T

    fun fromHashed(): T

}