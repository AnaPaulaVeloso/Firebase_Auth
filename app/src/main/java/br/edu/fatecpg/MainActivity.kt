package br.edu.fatecpg

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.fatecpg.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var editTextEmail: EditText
    private lateinit var editTextSenha: EditText
    private lateinit var btnCadastrar: Button
    private lateinit var btnLogin: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editTextEmail = binding.edtLogin
        editTextSenha = binding.edtSenha
        btnCadastrar = binding.btnCadastrar
        btnLogin = binding.btnLogin


        auth = Firebase.auth
        binding.btnLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val senha = editTextSenha.text.toString()

            auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        val intent = Intent(this, SegundaActivity::class.java)
                        intent.putExtra("Email", email)
                        startActivity(intent)
                        finish()

                        Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(this, "Falha ao entrar!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

        }
        binding.btnCadastrar.setOnClickListener {
            val email = editTextEmail.text.toString()
            val senha = editTextSenha.text.toString()
            auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        val intent = Intent(this, SegundaActivity::class.java)
                        intent.putExtra("Email", email)
                        startActivity(intent)
                        finish()

                        Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(this, "Falha ao realizar o cadastro!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            }
        }
    }




