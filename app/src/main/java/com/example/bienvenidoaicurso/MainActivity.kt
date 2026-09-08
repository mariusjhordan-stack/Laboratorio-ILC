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
            ExploracionComponentesApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ExploracionComponentesApp() {
    var selectedTab by remember { mutableIntStateOf(0) }
    var sliderValue by remember { mutableFloatStateOf(0.5f) }
    var switchState by remember { mutableStateOf(true) }
    var textFieldValue by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }
    var radioSelected by remember { mutableStateOf("Opción 1") }
    var checkboxState by remember { mutableStateOf(true) }
    var showDropdown by remember { mutableStateOf(false) }
    var showInputChip by remember { mutableStateOf(true) }

    // Scaffold: Estructura base de pantalla
    Scaffold(
        // TopAppBar: Barra superior
        topBar = {
            TopAppBar(
                title = { Text("Tecsup App Store", fontWeight = FontWeight.Bold) },
                actions = {
                    // IconButton: Botón de icono interactivo
                    IconButton(onClick = { showBottomSheet = true }) {
                        // BadgedBox & Badge: Indicador numérico de notificación
                        BadgedBox(badge = { Badge { Text("3") } }) {
                            Image(
                                painter = painterResource(id = R.drawable.baseline_auto_awesome_24),
                                contentDescription = "Notificaciones",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                    TextButton(onClick = { showDialog = true }) {
                        Text("Info", fontWeight = FontWeight.Bold)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        // NavigationBar (BottomNavigation): Barra de navegación inferior
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
        // FloatingActionButton (FAB): Botón flotante
        floatingActionButton = {
            FloatingActionButton(onClick = { showBottomSheet = true }) {
                Text("+", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        }
    ) { paddingValues ->

        // LazyColumn: Lista vertical de alto rendimiento
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Título principal
            item {
                Text(
                    text = "EXPLORACIÓN DE COMPONENTES (33)",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            }

            // Surface, OutlinedTextField, FlowRow, FilterChip, AssistChip, InputChip
            item {
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("OutlinedTextField & Chips (Filter, Assist, Input)", fontWeight = FontWeight.Bold)
                        Text("Entrada de texto y selección de filtros dinámicos.")
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
                            AssistChip(onClick = {}, label = { Text("Populares") })

                            // InputChip: Etiqueta interactiva eliminable
                            if (showInputChip) {
                                InputChip(
                                    selected = true,
                                    onClick = { showInputChip = false },
                                    label = { Text("Filtro Activo") },
                                    trailingIcon = {
                                        Image(
                                            painter = painterResource(id = R.drawable.baseline_auto_awesome_24),
                                            contentDescription = "Cerrar",
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }

            // ListItem e Image
            item {
                HorizontalDivider(thickness = 1.dp)
                Text("ListItem e Iconos Locales", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text("ListItem: Fila estructurada con icono/recurso gráfico.")
                Spacer(modifier = Modifier.height(4.dp))

                Card {
                    ListItem(
                        headlineContent = { Text("Curso de Android Jetpack Compose") },
                        supportingContent = { Text("Aprende a crear interfaces modernas en Kotlin") },
                        leadingContent = {
                            Image(
                                painter = painterResource(id = R.drawable.baseline_auto_awesome_24),
                                contentDescription = "Icono Local",
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        trailingContent = { Text("Gratis", color = MaterialTheme.colorScheme.primary) }
                    )
                }
            }

            // LazyRow, Card e Image
            item {
                Text("LazyRow, Card e Image", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text("LazyRow: Carrusel horizontal. Card: Tarjeta. Image: Gráficos locales.")
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

            // Controles Interactivos y VerticalDivider
            item {
                HorizontalDivider(thickness = 1.dp)
                Text("Controles Interactivos & VerticalDivider", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text("Slider, LinearProgressIndicator, Switch, Checkbox, RadioButton y VerticalDivider.")

                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Slider & LinearProgressIndicator:")
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
                                Text("Switch")
                            }

                            // VerticalDivider: Línea divisora vertical entre elementos
                            VerticalDivider(modifier = Modifier.height(24.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(checked = checkboxState, onCheckedChange = { checkboxState = it })
                                Text("Checkbox")
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

            // LazyVerticalGrid y Box
            item {
                Text("LazyVerticalGrid & Box", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text("Disposición en rejilla bidimensional y contenedor Box.")
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

            // Tipos de Botones
            item {
                HorizontalDivider(thickness = 1.dp)
                Text("Tipos de Botones (Buttons)", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text("Button, ElevatedButton, OutlinedButton y TextButton.")
                Spacer(modifier = Modifier.height(8.dp))

                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = {}) { Text("Button") }
                    ElevatedButton(onClick = {}) { Text("Elevated") }
                    OutlinedButton(onClick = {}) { Text("Outlined") }
                    TextButton(onClick = {}) { Text("TextButton") }
                }
            }

            // Indicadores, Menús y ModalBottomSheet
            item {
                HorizontalDivider(thickness = 1.dp)
                Text("Indicadores, Menús y Panel Desplegable", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text("CircularProgressIndicator, DropdownMenu y ModalBottomSheet.")
                Spacer(modifier = Modifier.height(8.dp))

                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("CircularProgress:", fontSize = 12.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                                CircularProgressIndicator(modifier = Modifier.size(32.dp))
                            }

                            Box {
                                Button(onClick = { showDropdown = true }) {
                                    Text("DropdownMenu")
                                }
                                DropdownMenu(
                                    expanded = showDropdown,
                                    onDismissRequest = { showDropdown = false }
                                ) {
                                    DropdownMenuItem(
                                        text = { Text("Opción 1 - Guardar") },
                                        onClick = { showDropdown = false }
                                    )
                                    DropdownMenuItem(
                                        text = { Text("Opción 2 - Compartir") },
                                        onClick = { showDropdown = false }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = { showBottomSheet = true },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Abrir ModalBottomSheet (Panel Inferior)")
                        }
                    }
                }
            }

            // Snackbar
            item {
                HorizontalDivider(thickness = 1.dp)
                Text("Snackbar", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Snackbar(
                    action = {
                        TextButton(onClick = {}) { Text("OK", color = Color.Yellow) }
                    }
                ) {
                    Text("¡Exploración completa con 33 componentes!")
                }
            }
        }
    }

    // ModalBottomSheet: Panel inferior deslizante
    if (showBottomSheet) {
        ModalBottomSheet(onDismissRequest = { showBottomSheet = false }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("ModalBottomSheet Desplegado", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Este panel se desliza desde la parte inferior para mostrar acciones adicionales.")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { showBottomSheet = false }) {
                    Text("Entendido / Cerrar")
                }
            }
        }
    }

    // AlertDialog: Ventana emergente flotante
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("AlertDialog") },
            text = { Text("Este componente interrumpe la pantalla para mostrar información importante.") },
            confirmButton = {
                Button(onClick = { showDialog = false }) { Text("Cerrar") }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExploracionComponentes() {
    ExploracionComponentesApp()
}
