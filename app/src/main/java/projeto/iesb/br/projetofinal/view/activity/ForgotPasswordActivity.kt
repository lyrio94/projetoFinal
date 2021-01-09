package projeto.iesb.br.projetofinal.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import projeto.iesb.br.projetofinal.R
import kotlinx.android.synthetic.main.activity_forgot_password.*
import projeto.iesb.br.projetofinal.domain.ForgotPassword
import projeto.iesb.br.projetofinal.domain.LoginResult
import projeto.iesb.br.projetofinal.viewmodel.ForgotPasswordViewModel

class ForgotPasswordActivity : AppCompatActivity() {
    lateinit var viewmodel: ForgotPasswordViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        bt_ForgotPassword.setOnClickListener { resgatarSenha() }


        viewmodel = ForgotPasswordViewModel(application)
        viewmodel.resultadoParaTela.observe(this) { resultado ->
            processarresgatarSenha(resultado)
        }
    }

    fun processarresgatarSenha(res: LoginResult){
        if(res.error != null) {
            Toast.makeText(this, res.error, Toast.LENGTH_LONG).show()
            return
        }
        Toast.makeText(this, res.result, Toast.LENGTH_LONG).show()
        val intentLogin = Intent(this, LoginActivity::class.java)
        startActivity(intentLogin)
        finish()
    }

   fun resgatarSenha(){
        val email = etEmailAddress.text.toString();

        val data = ForgotPassword(email)
        viewmodel.resgatarSenha(data)


    }
}