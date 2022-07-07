package com.example.proyecto.model
object MarkerMap {
    data class Mark(
        val nombre: String,
        val latitud: Double,
        val longitud: Double,
        val distrito: String,
        val GLP: Double,
        val gas90: Double,
        val gas85: Double,
        val direccion: String
    )

    private val marks: MutableList<Mark> = mutableListOf(
        Mark(
            "Primax", -16.42572, -71.53295,
            "AREQUIPA", 12.2,15.0,12.0 ,"Av. Perú 37, José Luis Bustamante y Rivero 04009"
        ),
        Mark(
            "Repsol", -16.424914437361867, -71.52419638903005,
            "AREQUIPA", 12.2,15.0,12.0 , "Av. Dolores 154, José Luis Bustamante y Rivero 04002"
        ),
        Mark(
            "Petro Peru", -16.41021013963542, -71.53301146575326,
            "AREQUIPA", 12.2,15.0,12.0 , "HFQ8+VRC, Arequipa 04001"
        ),
        Mark(
            "PETROLL SUR II", -16.4273, -71.4967,
            "AREQUIPA", 12.2,15.0,12.0 ,"AVENIDA AV. KENNEDY 2101"
        ),
        Mark(
            "Estación De Servicio PRIMAX",
            -16.413872019780776, -71.543351154688,
            "AREQUIPA",
            12.2,15.0,12.0 ,
            "Vía Rápida Venezuela 95, Arequipa 04001"
        ),
    )

    fun getListIterator(): MutableListIterator<Mark> {
        return marks.listIterator()
    }

    fun getMark(nom: String): Mark {
        marks.forEach() {
            if (nom == it.nombre) {
                return it
            }
        }
        return marks[0];
    }
}