package com.example.timerush.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.timerush.viewmodel.AuthViewModel

@Composable
fun LoginScreen(viewModel: AuthViewModel) {

    var name by remember { mutableStateOf("") }
    OutlinedTextField(
        value = name,
        onValueChange = { name = it },
        label = { Text("Name") },
        modifier = Modifier.fillMaxWidth()
    )

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text("TimeRush", style = MaterialTheme.typography.headlineLarge)

        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { viewModel.login(name,email, password) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        Spacer(Modifier.height(8.dp))

        TextButton(
            onClick = { viewModel.signup(name, email, password) }
        ) {
            Text("New user? Sign up")
        }

        viewModel.errorMessage.value?.let {
            Spacer(Modifier.height(8.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }
    }
}
