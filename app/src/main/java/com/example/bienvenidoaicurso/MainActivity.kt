package com.example.bienvenidoaicurso

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DashboardCreativoApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun DashboardCreativoApp() {
    var selectedTab by remember { mutableIntStateOf(0) }
    var sliderValue by remember { mutableFloatStateOf(0.5f) }
    var switchState by remember { mutableStateOf(true) }
    var textFieldValue by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var radioSelected by remember { mutableStateOf("Opción 1") }
    var checkboxState by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tecsup App Store", fontWeight = FontWeight.Bold) },
                actions = {
                    TextButton(onClick = { showDialog = true }) {
                        Text("Info", fontWeight = FontWeight.Bold)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_auto_awesome_24),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    label = { Text("Inicio") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_auto_awesome_24),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    label = { Text("Catálogo") }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Text("+", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Búsqueda & Filtros", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = textFieldValue,
                            onValueChange = { textFieldValue = it },
                            label = { Text("Buscar curso...") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            FilterChip(selected = true, onClick = {}, label = { Text("Móviles") })
                            FilterChip(selected = false, onClick = {}, label = { Text("Jetpack") })
                            AssistChip(
                                onClick = {},
                                label = { Text("Populares") }
                            )
                        }
                    }
                }
            }

            item {
                Text("Categorías (LazyRow)", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))

                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(5) { index ->
                        Card(modifier = Modifier.size(width = 130.dp, height = 90.dp)) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_auto_awesome_24),
                                    contentDescription = "Icono",
                                    modifier = Modifier.size(32.dp)
                                )
                                Text("Curso #$index", fontSize = 12.sp)
                            }
                        }
                    }
                }
            }

            item {
                HorizontalDivider(thickness = 1.dp)
                Text("Ajustes y Parámetros", fontSize = 18.sp, fontWeight = FontWeight.Bold)

                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Nivel de Novedades:")
                        Slider(value = sliderValue, onValueChange = { sliderValue = it })
                        LinearProgressIndicator(
                            progress = { sliderValue },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Switch(checked = switchState, onCheckedChange = { switchState = it })
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Notificar")
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(checked = checkboxState, onCheckedChange = { checkboxState = it })
                                Text("Activo")
                            }
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = radioSelected == "Opción 1",
                                onClick = { radioSelected = "Opción 1" }
                            )
                            Text("Modo Claro")
                            Spacer(modifier = Modifier.width(8.dp))
                            RadioButton(
                                selected = radioSelected == "Opción 2",
                                onClick = { radioSelected = "Opción 2" }
                            )
                            Text("Modo Oscuro")
                        }
                    }
                }
            }

            item {
                Text("Cursos Destacados (Grid)", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            item {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.height(160.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(4) { i ->
                        Surface(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = MaterialTheme.shapes.small
                        ) {
                            Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
                                Text("Item Grid $i")
                            }
                        }
                    }
                }
            }

            item {
                Snackbar(
                    action = {
                        TextButton(onClick = {}) { Text("OK", color = Color.Yellow) }
                    }
                ) {
                    Text("¡Bienvenido a la vista de componentes!")
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Información del Sistema") },
            text = { Text("Esta vista utiliza Scaffold, LazyColumn, LazyRow, Grid, Cards y múltiples controles interactivos.") },
            confirmButton = {
                Button(onClick = { showDialog = false }) { Text("Entendido") }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDashboardCreativo() {
    DashboardCreativoApp()
}