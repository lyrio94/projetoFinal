package projeto.iesb.br.projetofinal.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*
import projeto.iesb.br.projetofinal.R
import projeto.iesb.br.projetofinal.domain.RegisterData
import projeto.iesb.br.projetofinal.domain.RegisterResult
import projeto.iesb.br.projetofinal.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    lateinit var viewmodel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btRegister.setOnClickListener{ cadastrar() }
        btBackRegister.setOnClickListener { voltar() }

        viewmodel = RegisterViewModel(application)
        viewmodel.resultadoParaTela.observe(this) { resultadoCadastro ->
            processarResultRegister(resultadoCadastro)
        }
    }

    fun processarResultRegister(res: RegisterResult){

        if(res.error != null) {
            Toast.makeText(this, res.error, Toast.LENGTH_LONG).show()
            return
        }


    }

    fun cadastrar() {
        val email = etEmailRegistry.text.toString()
        val pass = etPasswordRegistry.text.toString()
        val confirmPass = etPasswordConfirmRegistry.text.toString()

        val dataRegister = RegisterData(email, pass, confirmPass)
        viewmodel.cadastrar(dataRegister)

        viewmodel = RegisterViewModel(application)
        viewmodel.resultadoParaTela.observe(this) { resultado ->
            processarResultCadastrar(resultado)

        }

    }

    fun processarResultCadastrar(res: RegisterResult){

        if(res.error != null) {
            Toast.makeText(this, res.error, Toast.LENGTH_LONG).show()
            return
        }
        Toast.makeText(this, res.result, Toast.LENGTH_LONG).show()
        val intentRegister = Intent(this, HomeActivity::class.java)
        startActivity(intentRegister)
        finish()

    }

    fun voltar() {
        finish()
    }
}