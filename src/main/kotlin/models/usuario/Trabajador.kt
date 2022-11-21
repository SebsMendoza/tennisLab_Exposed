package models.usuario

import java.util.*

class Trabajador(
    id: Long,
    uuid: UUID = UUID.randomUUID(),
    nombre: String,
    apellido: String,
    email: String,
    password: String,
    val perfil: Perfil = Perfil.ENCORDADOR
) : Usuario(id, uuid, nombre, apellido, email, password) {
    override fun toString(): String {
        return "Trabajador(uuid=$uuid, nombre='$nombre', apellido='$apellido', email='$email', password='$password')"
    }
}