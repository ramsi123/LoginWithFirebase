package com.example.loginwithfirebase.ui.screen.home.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginwithfirebase.R
import com.example.loginwithfirebase.components.UpdateReminder
import com.example.loginwithfirebase.ui.theme.ghostWhite
import com.example.loginwithfirebase.util.Constants.MESSAGE
import com.example.loginwithfirebase.util.Constants.TIME_DAILY
import com.example.loginwithfirebase.util.Constants.TIME_FIFTEEN_DAYS
import com.example.loginwithfirebase.util.Constants.TIME_FIVE_DAYS
import com.example.loginwithfirebase.util.Constants.TIME_MONTHLY
import com.example.loginwithfirebase.util.Constants.TIME_TWO_DAYS
import com.example.loginwithfirebase.util.Constants.TITLE
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    availableMemory: Long,
    totalMemory: Long,
    storageProgressBar: Float,
    onSignOut: () -> Unit
) {
    val updateReminder = UpdateReminder()
    val context = LocalContext.current
    val checkedState = remember { mutableStateOf(false) }
    val selectedTime = remember { mutableStateOf(TIME_DAILY) }

    LaunchedEffect(key1 = checkedState.value, key2 = selectedTime.value) {
        if (checkedState.value) {
            var frequency = 1
            when (selectedTime.value) {
                TIME_DAILY -> {
                    frequency = 1
                }
                TIME_TWO_DAYS -> {
                    frequency = 2
                }
                TIME_FIVE_DAYS -> {
                    frequency = 5
                }
                TIME_FIFTEEN_DAYS -> {
                    frequency = 15
                }
                TIME_MONTHLY -> {
                    frequency = 30
                }
            }
            updateReminder.setUpdateReminder(context, TITLE, MESSAGE, frequency)
        } else {
            updateReminder.cancelUpdateReminder(context)
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(ghostWhite)
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center
        ) {
            /*Text(text = "Internal Available ${formatSize(availableMemory)}")
            Text(text = "Internal Total ${formatSize(totalMemory)}")
            Spacer(modifier = modifier.height(20.dp))
            CircularProgressBar(
                percentage = storageProgressBar,
                availableMemory = formatSize(availableMemory) ?: "0",
                totalMemory = " of ${formatSize(totalMemory)} Used",
                number = 100
            )
            Spacer(modifier = modifier.height(20.dp))
            Button(
                onClick = {
                    onSignOut()
                }
            ) {
                Text(text = "Sign Out")
            }*/
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_notifications_none_24),
                    contentDescription = "bell",
                    Modifier
                        .size(40.dp)
                        .padding(top = 10.dp)
                )
                Column() {
                    Text(
                        text = "Software Update Notification",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = "Select Frequency",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Light),
                    )

                }
                Spacer(modifier = Modifier.height(10.dp))

                Switch(
                    checked = checkedState.value, onCheckedChange = { checkedState.value = it },
                    colors = SwitchDefaults.colors(Color(0xFF2AAA8A))

                )


            }

            Column {
                Row {
                    RadioButton(
                        selected = selectedTime.value == TIME_DAILY,
                        onClick = { selectedTime.value = TIME_DAILY },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color(0xFF2AAA8A), // Color for the filled radio button
                            unselectedColor = Color(0xFF2AAA8A)
                        )
                    )
                    Text(
                        text = TIME_DAILY,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start
                        ),
                        modifier = Modifier.padding(top = 10.dp, start = 0.5.dp)
                    )

                }
                Spacer(modifier = Modifier.height(3.dp))
                Row {
                    RadioButton(
                        selected = selectedTime.value == TIME_TWO_DAYS,
                        onClick = { selectedTime.value = TIME_TWO_DAYS },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color(0xFF2AAA8A), // Color for the filled radio button
                            unselectedColor = Color(0xFF2AAA8A)

                        ),
                    )
                    Spacer(modifier = Modifier.size(1.dp))
                    Text(
                        TIME_TWO_DAYS,
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(top = 10.dp)

                    )
                }

                Spacer(modifier = Modifier.height(3.dp))

                Row {
                    RadioButton(
                        selected = selectedTime.value == TIME_FIVE_DAYS,
                        onClick = { selectedTime.value = TIME_FIVE_DAYS },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color(0xFF2AAA8A), // Color for the filled radio button
                            unselectedColor = Color(0xFF2AAA8A)

                        ),
                    )
                    Text(
                        text = TIME_FIVE_DAYS,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start
                        ),
                        modifier = Modifier.padding(top = 10.dp, start = 0.5.dp)

                    )
                }
                Spacer(modifier = Modifier.height(3.dp))

                Row {
                    RadioButton(
                        selected = selectedTime.value == TIME_FIFTEEN_DAYS,
                        onClick = { selectedTime.value = TIME_FIFTEEN_DAYS },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color(0xFF2AAA8A), // Color for the filled radio button
                            unselectedColor = Color(0xFF2AAA8A)

                        )
                    )
                    Text(
                        text = TIME_FIFTEEN_DAYS,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start
                        ),
                        modifier = Modifier.padding(top = 10.dp, start = 0.5.dp)

                    )
                }
                Spacer(modifier = Modifier.height(3.dp))

                Row {
                    RadioButton(
                        selected = selectedTime.value == TIME_MONTHLY,
                        onClick = { selectedTime.value = TIME_MONTHLY },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color(0xFF2AAA8A), // Color for the filled radio button
                            unselectedColor = Color(0xFF2AAA8A)

                        ),
                    )
                    Text(
                        text = TIME_MONTHLY,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start
                        ),
                        modifier = Modifier.padding(top = 10.dp, start = 0.5.dp)

                    )
                }
            }
        }
    }
}