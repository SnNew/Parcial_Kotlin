class SistemaReservas {
    private val reservas = mutableListOf<Reserva>()
    private var contadorReservas = 1

    private val tiposHabitacion = mapOf(
        "HabitacionJefeMaestro" to 250000.0,
        "HabitacionLegendaria" to 180000.0,
        "HabitacionSettsi" to 350000.0
    )

    fun registrarReserva(
        idHuesped: Int,
        nombreHuesped: String,
        emailHuesped: String,
        numeroHabitacion: Int,
        tipoHabitacion: String,
        cantidadNoches: Int
    ): Boolean {
        if (reservas.any { it.numeroHabitacion == numeroHabitacion }) {
            println("Error: La habitación $numeroHabitacion ya está reservada.")
            return false
        }

        val precioPorNoche = tiposHabitacion[tipoHabitacion]
        if (precioPorNoche == null) {
            println("Error: Tipo de habitación inválido.")
            return false
        }

        val nuevaReserva = Reserva(
            idReserva = contadorReservas++,
            idHuesped = idHuesped,
            nombreHuesped = nombreHuesped,
            emailHuesped = emailHuesped,
            numeroHabitacion = numeroHabitacion,
            tipoHabitacion = tipoHabitacion,
            precioPorNoche = precioPorNoche,
            cantidadNoches = cantidadNoches
        )

        reservas.add(nuevaReserva)
        println("Reserva registrada con éxito. ID de reserva: ${nuevaReserva.idReserva}")
        return true
    }

    fun cancelarReserva(idReserva: Int): Boolean {
        val reservaEncontrada = reservas.find { it.idReserva == idReserva }

        if (reservaEncontrada != null) {
            reservas.remove(reservaEncontrada)
            println("Reserva con ID $idReserva cancelada exitosamente.")
            return true
        } else {
            println("Error: No se encontró una reserva con ID $idReserva.")
            return false
        }
    }

    fun mostrarReservasActivas() {
        if (reservas.isEmpty()) {
            println("No hay reservas activas en el sistema.")
            return
        }

        println("\nRESERVAS ACTIVAS ")
        println("ID | Huésped | Habitación | Tipo de Habitación       | Noches | Precio/Noche (COP) | Total (COP)")

        reservas.forEach { reserva ->
            val montoTotal = reserva.calcularMontoTotal()
            println("${reserva.idReserva} | ${reserva.nombreHuesped} | ${reserva.numeroHabitacion} | ${reserva.tipoHabitacion} | ${reserva.cantidadNoches} | ${reserva.precioPorNoche} | $montoTotal")
        }

        val totalSistema = reservas.sumOf { it.calcularMontoTotal() }
        println("Total general del sistema: $totalSistema COP")
    }
}