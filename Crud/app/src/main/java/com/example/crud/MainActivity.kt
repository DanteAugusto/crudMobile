package com.example.crud

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.crud.databinding.ActivityMainBinding
import com.example.crud.ui.theme.CrudTheme
import com.example.crud.Utilizador
class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private var pos = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            CrudTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                    binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
                    setContentView(binding.root)
                    val listaUtilizador = ArrayList<Utilizador>()
                    listaUtilizador.add(Utilizador("user","pass"))
                    listaUtilizador.add(Utilizador("admin","pw123"))
                    listaUtilizador.add(Utilizador("aaa","bbb"))
                    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaUtilizador)
                    binding.listViewUtilizadores.adapter = adapter
                    binding.listViewUtilizadores.setOnItemClickListener { _, _, position, _ ->
                        binding.editUsername.setText(listaUtilizador.get(position).username)
                        binding.editPassword.setText(listaUtilizador.get(position).password)
                        pos = position
                    }
                    binding.buttonInserir.setOnClickListener {
                        val username = binding.editUsername.text.toString().trim()
                        val password = binding.editPassword.text.toString().trim()

                        if(!username.isEmpty() && !password.isEmpty()){
                            listaUtilizador.add(Utilizador(username, password))
                            adapter.notifyDataSetChanged()
                            binding.editUsername.setText("")
                            binding.editPassword.setText("")
                            pos = -1
                        }
                    }
                    binding.buttonEditar.setOnClickListener {
                        if(pos != -1){
                            val username = binding.editUsername.text.toString().trim()
                            val password = binding.editPassword.text.toString().trim()

                            if(!username.isEmpty() && !password.isEmpty()) {
                                listaUtilizador.get(pos).username = username
                                listaUtilizador.get(pos).password =password
                                pos = -1
                                adapter.notifyDataSetChanged()
                                binding.editUsername.setText("")
                                binding.editPassword.setText("")
                            }
                        }
                    }
                    binding.buttonEliminar.setOnClickListener {
                        if(pos != -1){
                            listaUtilizador.removeAt(pos)
                            pos = -1
                            adapter.notifyDataSetChanged()
                            binding.editUsername.setText("")
                            binding.editPassword.setText("")
                        }
                    }
                    binding.buttonLimpar.setOnClickListener {
                        listaUtilizador.clear()
                        adapter.notifyDataSetChanged()
                        binding.editUsername.setText("")
                        binding.editPassword.setText("")
                        pos = -1
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CrudTheme {
        Greeting("Android")
    }
}
