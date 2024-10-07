import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.eventeasy.R
import com.example.eventeasy.data.services.ApiClient
import com.example.eventeasy.data.services.ApiResponse
import com.example.eventeasy.data.services.ApiService
import com.example.eventeasy.data.services.SignUpRequest
import com.example.eventeasy.viewmodel.AuthViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun SignUpScreen(navController: NavController, authViewModel: AuthViewModel = viewModel()) {
    var fullName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    val context = navController.context
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 28.dp)
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(id = R.string.sign_up),
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF120D26),
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text(stringResource(id = R.string.full_name)) },
            textStyle = MaterialTheme.typography.bodyMedium,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Full Name Icon"
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text(stringResource(id = R.string.last_name)) },
            textStyle = MaterialTheme.typography.bodyMedium,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Last Name Icon"
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(id = R.string.sign_in_email)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            textStyle = MaterialTheme.typography.bodyMedium,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email Icon"
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(id = R.string.password)) },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        imageVector = if (passwordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle Password Visibility"
                    )
                }
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password Icon"
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            textStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text(stringResource(id = R.string.confirm_password)) },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        imageVector = if (passwordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle Password Visibility"
                    )
                }
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Confirm Password Icon"
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            textStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(38.dp))

        Button(
            onClick = {
                // Verificar que los campos no estén vacíos antes de enviar
                if (fullName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                    coroutineScope.launch {
                        val apiClient = ApiClient.getClient("es").create(ApiService::class.java)
                        val signUpRequest = SignUpRequest(
                            nombre = fullName,
                            apellido = lastName,
                            correo = email,
                            contrasena = password
                        )

                        apiClient.signUp(signUpRequest).enqueue(object : Callback<ApiResponse> {
                            override fun onResponse(
                                call: Call<ApiResponse>,
                                response: Response<ApiResponse>
                            ) {
                                if (response.isSuccessful) {
                                    val apiResponse = response.body()
                                    if (apiResponse?.status == "success") {
                                        // Mostrar un Toast con el mensaje de éxito
                                        Toast.makeText(context, apiResponse.message, Toast.LENGTH_SHORT).show()
                                    } else {
                                        // Mostrar un Toast con el mensaje de error
                                        Toast.makeText(context, apiResponse?.message ?: "Unknown error", Toast.LENGTH_SHORT).show()
                                    }
                                } else {
                                    // Error con la respuesta HTTP
                                    Toast.makeText(context, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                                // Manejar los errores de red o del servidor
                                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                            }
                        })
                    }
                } else {
                    Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth().height(56.dp).padding(horizontal = 30.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5669FF))
        ) {
            Text(stringResource(id = R.string.sign_up), color = Color.White, style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(28.dp))

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(text = stringResource(id = R.string.or_label), style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = { /* Google Sign-In */ },
            modifier = Modifier.fillMaxWidth().height(56.dp).padding(horizontal = 30.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "Google Icon",
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(id = R.string.sign_in_google), style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(23.dp))

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                Text(text = stringResource(id = R.string.have_account), color = Color(0xFF120D26))
                TextButton(onClick = {
                    navController.navigate("signin")
                }) {
                    Text(text = stringResource(id = R.string.sign_in_button), color = Color(0xFF5669FF))
                }
            }
        }
    }
}
