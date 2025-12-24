package com.lepesaurion.unitconverter.domain;

/**
 * Tipos de conversion disponibles en la aplicación.
 */
enum class ConvertionType( val displayName: String) {
    METERS_TO_KILOMETERS("Metros -> Kilometros"),
    KILOMETERS_TO_METERS("Kilometros -> Metros"),
    KILOGRAMS_TO_GRAMS("Kilogramos -> Gramos"),
    GRAMS_TOKILOGRAMS("Gramos -> kilogramos"),
    CELSIUS_TO_FAHRENHEIT("Celsius -> Fahrenheit"),
    FAHRENHEIT_TO_CELSIUS("Fahrenheit -> Celsius")
}

/**
 * Logica central de conversiones.
 * No depende de Android ni de Compose.
 */
object Converter {

    /**
     * Convierte un valor según el tipo de conversión seleccionado.
     * 
     * @param value Valor numérico ingresado por el usuario
     * @param type Tipo de conversión
     * @return Resultado de conversión
     */
    fun convert(value: Double, type: ConvertionType): Double {
        return when (type) {
            ConvertionType.METERS_TO_KILOMETERS -> value / 1_000.0
            ConvertionType.KILOMETERS_TO_METERS -> value * 1_000.0
            ConvertionType.KILOGRAMS_TO_GRAMS -> value * 1_000.0
            ConvertionType.GRAMS_TOKILOGRAMS -> value / 1_000.0
            ConvertionType.CELSIUS_TO_FAHRENHEIT -> (value * 9 / 5) + 32
            ConvertionType.FAHRENHEIT_TO_CELSIUS -> (value - 32) * 5 / 9
        }
    }
}
