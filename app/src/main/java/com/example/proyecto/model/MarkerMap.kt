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
            "HOSPITAL REGIONAL HONORIO DELGADO ESPINOZA", -16.4157, -71.5331,
            "AREQUIPA", 12.2,15.0,12.0 ,"Av. Daniel Alcides Carrión Nº 505"
        ),
        Mark(
            "CENTRO DE SALUD AMPLIACIÓN PAUCARPATA", -16.4271, -71.5034,
            "AREQUIPA", 12.2,15.0,12.0 , "AVENIDA AVENIDA KENNEDY 2101"
        ),
        Mark(
            "HOSPITAL GOYENECHE", -16.4016, -71.5284,
            "AREQUIPA", 12.2,15.0,12.0 , "AREQUIPA,AV. GOYENECHE S/N"
        ),
        Mark(
            "CENTRO DE SALUD CAMPO MARTE", -16.4273, -71.4967,
            "AREQUIPA", 12.2,15.0,12.0 ,"AVENIDA AV. KENNEDY 2101"
        ),
        Mark(
            "PUESTO DE SALUD SALAVERRY",
            -16.4349,
            -71.5364,
            "AREQUIPA",
            12.2,15.0,12.0 ,
            "SOCABAYA AVENIDA AV. LAS PEÑAS S/N SALAVERRY-SOCABAYA S/N AV. LAS PEÑAS S/N SALAVERRY-"
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