package models

import java.time.LocalDate
import java.util.UUID

data class Pedido(
    val id: Long,
    val uuid: UUID = UUID.randomUUID(),
    val estado: TipoEstado,
    val fechaEntrada: LocalDate,
    val fechaProgramada: LocalDate,
    val fechaSalida: LocalDate,
    val tareas: List<Tarea>? = null,
    val total: Double

) {
    enum class TipoEstado(val estado: String) {
        RECIBIDO("RECIBIDO"),
        PROCESANDO("PROCESANDO"),
        TERMINADO("TERMINADO");

        companion object {
            fun from(estado: String): TipoEstado {
                return when (estado.uppercase()) {
                    "RECIBIDO" -> RECIBIDO
                    "PROCESANDO" -> PROCESANDO
                    "TERMINADO" -> TERMINADO
                    else -> throw IllegalArgumentException("Estado no reconocido")
                }
            }
        }
    }

    override fun toString(): String {
        return "Pedido(uuid=$uuid, estado=$estado, fechaEntrada=$fechaEntrada, fechaProgramada=$fechaProgramada, fechaSalida=$fechaSalida, tareas=$tareas, total=$total)"
    }


}