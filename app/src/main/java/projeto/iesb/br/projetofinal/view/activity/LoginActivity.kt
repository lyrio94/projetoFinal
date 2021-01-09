package projeto.iesb.br.projetofinal.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import projeto.iesb.br.projetofinal.R
import projeto.iesb.br.projetofinal.domain.LoginData
import projeto.iesb.br.projetofinal.domain.LoginResult
import projeto.iesb.br.projetofinal.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var viewmodel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btLogin.setOnClickListener { login() }
        tvForgotPassword.setOnClickListener { forgotPassword() }
        tvRegister.setOnClickListener { register() }

        viewmodel = LoginViewModel(application)
        viewmodel.resultadoParaTela.observe(this) { resultado ->
            processarResultLogin(resultado)
        }
    }


    fun processarResultLogin(res: LoginResult){
        if(res.error != null) {
            Toast.makeText(this, res.error, Toast.LENGTH_LONG).show()
            return
        }
        Toast.makeText(this, res.result, Toast.LENGTH_LONG).show()
        val intentHome = Intent(this, HomeActivity::class.java)
        startActivity(intentHome)
        finish()
    }

    fun login() {

        val email = etEmail.text.toString()
        val pass = etPassword.text.toString()

        val data = LoginData(email, pass)
        viewmodel.login(data)


    }

    private fun forgotPassword() {
        val intetForgotPassword = Intent(this, ForgotPasswordActivity::class.java);
        startActivity(intetForgotPassword);
    }

    fun register() {

        val intentRegister = Intent(this, RegisterActivity::class.java)
        startActivity(intentRegister)

    }
}