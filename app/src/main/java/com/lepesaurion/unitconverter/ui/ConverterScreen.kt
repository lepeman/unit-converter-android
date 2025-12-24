package com.lepesaurion.unitconverter.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.input.KeyboardType
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

    Scaffold(
        topBar = {
            TopAppBar(
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
                onValueChange = { inputValue = it },
                label = { Text("Valor") },
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

            Button(
               onClick = {
                   val value = inputValue.toDoubleOrNull()
                   result = value?.let {
                       Converter.convert(it, selectedConvertion)
                   }
               },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Convertir")
            }

            result?.let {
                Text(
                    text = "Resultado: $it",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}