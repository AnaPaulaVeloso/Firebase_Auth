package br.edu.fatecpg

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.fatecpg.databinding.ActivityMainBinding
import br.edu.fatecpg.databinding.ActivitySegundaBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class SegundaActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySegundaBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegundaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val email = intent.getStringExtra("Email")
        binding.txvEmail.text = email

        binding.btnExcluir.setOnClickListener {
            var email = Firebase.auth.currentUser!!

            email.delete()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Usuário Deletado!", Toast.LENGTH_SHORT)
                    }
                }

            binding.btnSenha.setOnClickListener {

                
                var email = Firebase.auth.currentUser!!.toString()

                Firebase.auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "E-mail enviado!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Falha ao enviar e-mail:", Toast.LENGTH_LONG)
                                .show()
                        }
                    }

            }

        }

    }
}