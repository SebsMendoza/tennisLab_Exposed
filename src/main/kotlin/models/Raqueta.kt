package models

import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import models.usuario.Cliente
import java.util.*

data class Raqueta(
    val id: Long,
    @Expose val uuid: UUID = UUID.randomUUID(),
    @Expose val marca: String,
    @Expose val modelo: String,
    @Expose val cliente: Cliente
) {
    override fun toString(): String {
        return GsonBuilder().setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .create().toJson(this)
    }
}