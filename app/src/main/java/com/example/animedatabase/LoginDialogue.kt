package com.example.animedatabase

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun LoginDialogue(
    onDismiss: () -> Unit, // When the dialog is dismissed
    onLogin: (String, String) -> Unit // On successful login
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val context = LocalContext.current

    Dialog(onDismissRequest = { onDismiss() }) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(
                text = "Login",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Password") },
                visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // Here, you can handle login logic (validate input)
                    if (email.value.isNotEmpty() && password.value.isNotEmpty()) {
                        onLogin(email.value, password.value)
                    } else {
                        // Provide feedback if necessary
                        Toast.makeText(context, "Please enter both email and password.", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = {
                    // Handle guest mode login or dismiss the dialog
                    onDismiss()
                }
            ) {
                Text("Continue as Guest")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginDialogue() {
    LoginDialogue(onDismiss = {}, onLogin = { email, password -> })
}