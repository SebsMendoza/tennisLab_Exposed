package repositories.turno

import entities.TurnoDao
import mappers.fromTurnoDaoToTurno
import models.Turno
import mu.KotlinLogging
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.sql.transactions.transaction

private val log = KotlinLogging.logger { }

class TurnoRepositoryImpl(private val turnoDao: LongEntityClass<TurnoDao>) : TurnoRepository {
    override fun findAll(): List<Turno> = transaction{
        log.debug { "findAll()" }
        turnoDao.all().map { it.fromTurnoDaoToTurno() }
    }

    override fun findById(id: Long): Turno? = transaction {
        log.debug { "findById($id)" }
        turnoDao.findById(id)?.fromTurnoDaoToTurno()
    }

    override fun save(entity: Turno) = transaction {
        val existe = turnoDao.findById(entity.id)
        existe?.let {
            update(entity, existe)
        } ?: run {
            insert(entity)
        }
    }

    private fun insert(entity: Turno): Turno{
        log.debug { "save($entity) - creando" }
        return turnoDao.new(entity.id) {
            horario = entity.horario.horario
        }.fromTurnoDaoToTurno()
    }

    private fun update(entity: Turno, existe: TurnoDao): Turno{
        log.debug { "save($entity) - actualizando" }
        return existe.apply {
            horario = entity.horario.horario
        }.fromTurnoDaoToTurno()
    }

    override fun delete(entity: Turno): Boolean = transaction {
        val existe = turnoDao.findById(entity.id) ?: return@transaction false
        log.debug { "delete($entity) - borrando" }
        existe.delete()
        true
    }

}