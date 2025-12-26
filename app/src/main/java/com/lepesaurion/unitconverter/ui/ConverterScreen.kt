package com.lepesaurion.unitconverter.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lepesaurion.unitconverter.domain.ConvertionType
import com.lepesaurion.unitconverter.domain.Converter
import com.lepesaurion.unitconverter.ui.components.ConvertionDropdown

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConverterScreen() {

    var inputValue by remember { mutableStateOf("") }
    var selectedConvertion by remember {
        mutableStateOf(ConvertionType.METERS_TO_KILOMETERS)
    }
    var result by remember { mutableStateOf<Double?>(null) }

    val resultToShow = (result ?: 0.0).toString()

    val labelInput = when (selectedConvertion) {
        ConvertionType.METERS_TO_KILOMETERS -> "Medida en metros"
        ConvertionType.KILOMETERS_TO_METERS -> "Medida en kilometros"
        ConvertionType.KILOGRAMS_TO_GRAMS -> "Peso en kilogramos"
        ConvertionType.GRAMS_TOKILOGRAMS -> "Peso en gramos"
        ConvertionType.CELSIUS_TO_FAHRENHEIT -> "Temperatura en Grados Celsius"
        ConvertionType.FAHRENHEIT_TO_CELSIUS -> "Temperatura en Fahrenheit"
    }

    val labelOutput = when (selectedConvertion) {
        ConvertionType.METERS_TO_KILOMETERS -> "Resultado en kilometros"
        ConvertionType.KILOMETERS_TO_METERS -> "Resultado en metros"
        ConvertionType.KILOGRAMS_TO_GRAMS -> "Resultado en gramos"
        ConvertionType.GRAMS_TOKILOGRAMS -> "Resultado en kilogramos"
        ConvertionType.CELSIUS_TO_FAHRENHEIT -> "Resultado en fahrenheit"
        ConvertionType.FAHRENHEIT_TO_CELSIUS -> "Resultado en celsius"
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Conversor de unidades") }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = inputValue,
                onValueChange = { nuevo ->
                    inputValue = nuevo

                    val numero = nuevo.toDoubleOrNull()

                    result = numero?.let {
                        Converter.convert(numero, selectedConvertion)
                    }
                },
                label = { Text(labelInput) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            ConvertionDropdown(
                selected = selectedConvertion,
                onSelected = { selectedConvertion = it },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = resultToShow,
                onValueChange = {},
                label = { Text(labelOutput) },
                readOnly = true,
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun ConverterScreenPreview() {
    ConverterScreen()
}