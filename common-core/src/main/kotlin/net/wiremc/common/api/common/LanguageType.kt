package net.wiremc.common.api.common

/**
 *
 * this doc was created on 26.05.2022
 * This class belongs to the wiremc-workspace project
 *
 * @author Generix030
 *
 */

enum class LanguageType(private val s: String, val url: String) {

    ENGLISH("en", "http://textures.minecraft.net/texture/bee5c850afbb7d8843265a146211ac9c615f733dcc5a8e2190e5c247dea32"),
    GERMAN("de", "http://textures.minecraft.net/texture/5e7899b4806858697e283f084d9173fe487886453774626b24bd8cfecc77b3f"),
    CHINESE("zh", "http://textures.minecraft.net/texture/7f9bc035cdc80f1ab5e1198f29f3ad3fdd2b42d9a69aeb64de990681800b98dc"),
    FRENCH("fr", "http://textures.minecraft.net/texture/6903349fa45bdd87126d9cd3c6c0abba7dbd6f56fb8d78701873a1e7c8ee33cf"),
    ITALIAN("it", "http://textures.minecraft.net/texture/85ce89223fa42fe06ad65d8d44ca412ae899c831309d68924dfe0d142fdbeea4"),
    RUSSIAN("ru", "http://textures.minecraft.net/texture/16eafef980d6117dabe8982ac4b4509887e2c4621f6a8fe5c9b735a83d775ad"),
    UKRAINIAN("uk", "http://textures.minecraft.net/texture/28b9f52e36aa5c7caaa1e7f26ea97e28f635e8eac9aef74cec97f465f5a6b51"),
    SPANISH("es", "http://textures.minecraft.net/texture/32bd4521983309e0ad76c1ee29874287957ec3d96f8d889324da8c887e485ea8");

    override fun toString(): String {
        return this.s
    }

}