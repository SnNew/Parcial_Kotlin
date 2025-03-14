fun main() {
    val sistema = SistemaReservas()
    var ejecutando = true

    while (ejecutando) {
        println("\n    SISTEMA DE RESERVAS DEL HOTEL HAZBIN   ")
        println("1. Registrar una reserva")
        println("2. Cancelar una reserva")
        println("3. Mostrar todas las reservas activas")
        println("4. Salir")
        print("Seleccione una opción: ")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                println("\n     REGISTRO DE RESERVA    ")
                print("ID del huésped: ")
                val idHuesped = readLine()?.toIntOrNull() ?: 0
                print("Nombre del huésped: ")
                val nombreHuesped = readLine() ?: ""
                print("Email del huésped: ")
                val emailHuesped = readLine() ?: ""
                print("Número de habitación: ")
                val numeroHabitacion = readLine()?.toIntOrNull() ?: 0

                println("\nSeleccione el tipo de habitación:")
                println("1. Habitacion Jefe Maestro (Precio por noche: \$250,000 COP)")
                println("2. Habitacion Legendaria (Precio por noche: \$180,000 COP)")
                println("3. Habitacion Settsi (Precio por noche: \$350,000 COP)")
                print("Opción: ")
                val opcionTipo = readLine()?.toIntOrNull()

                val tipoHabitacion = when (opcionTipo) {
                    1 -> "HabitacionJefeMaestro"
                    2 -> "HabitacionLegendaria"
                    3 -> "HabitacionSettsi"
                    else -> {
                        println("Opción de habitación inválida.")
                        continue
                    }
                }

                print("Cantidad de noches: ")
                val cantidadNoches = readLine()?.toIntOrNull() ?: 0

                sistema.registrarReserva(
                    idHuesped,
                    nombreHuesped,
                    emailHuesped,
                    numeroHabitacion,
                    tipoHabitacion,
                    cantidadNoches
                )
            }
            2 -> {
                print("\nIngrese el ID de la reserva a cancelar: ")
                val idReserva = readLine()?.toIntOrNull() ?: 0
                sistema.cancelarReserva(idReserva)
            }
            3 -> sistema.mostrarReservasActivas()
            4 -> {
                println("Gracias por usar el sistema de reservas legendario. ¡Hasta pronto viajero, que la fuerza te acompañe!")
                ejecutando = false
            }
            else -> println("Opción inválida. Por favor, ingrese un valor valido >:c")
        }
    }
}