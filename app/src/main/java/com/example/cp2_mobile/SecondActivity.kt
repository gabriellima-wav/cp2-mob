package com.example.cp2_mobile

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)  // O layout que será carregado

        // Configurar o Spinner
        val spinner: Spinner = findViewById(R.id.spinner)

        // Criar o adaptador para o Spinner usando o array de strings definido no strings.xml
        ArrayAdapter.createFromResource(
            this,
            R.array.filmes_array,  // O array de strings definido no strings.xml
            android.R.layout.simple_spinner_item  // Layout simples para o Spinner
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)  // Estilo dropdown para os itens
            spinner.adapter = adapter
        }

        // Configurar o listener para capturar o item selecionado
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Pegar o item selecionado
                val selectedItem = parent.getItemAtPosition(position).toString()
                // Exibir uma mensagem Toast
                Toast.makeText(this@SecondActivity, "Selecionado: $selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Nada a fazer aqui
            }
        }

        // Configurar o botão para carregar o fragment de créditos
        val buttonCredits: Button = findViewById(R.id.button_credits)
        buttonCredits.setOnClickListener {
            if (supportFragmentManager.findFragmentByTag("CreditsFragment") == null) {
                loadFragment(CreditsFragment(), "CreditsFragment")
            }
        }
    }

    // Função para carregar o fragment de créditos
    private fun loadFragment(fragment: Fragment, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment, tag)  // Substitui o FrameLayout pelo fragment
        transaction.addToBackStack(null)  // Permite voltar ao estado anterior
        transaction.commit()
    }
}
