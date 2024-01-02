package com.senai.vsconnect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var headerIcon: ImageView
    private lateinit var headerTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navController = findNavController(R.id.nav_host_fragment)
        val navView = findViewById<NavigationView>(R.id.nav_view)

        // Configurar o ícone do hamburguer manualmente
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Configurar cabeçalho
        val headerLayout = findViewById<ConstraintLayout>(R.id.main_header_layout)
        headerIcon = headerLayout.findViewById(R.id.header_icone_hamburguer)
        headerTitle = headerLayout.findViewById(R.id.header_titulo)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            updateHeader(destination)

            if (destination.id == R.id.lista_servicos) {
                // Configurar o menu lateral apenas na tela de Serviços
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                navView.setupWithNavController(navController)
            } else {
                // Bloquear o menu lateral em outras telas
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }

    }

    private fun updateHeader(destination: NavDestination) {
        headerTitle.text = destination.label
        // Se necessário, ajuste o ícone do cabeçalho aqui
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)

        return when (item.itemId) {
            R.id.nav_editar_imagem -> {
                // Navegar para o fragment de editar imagem
                navController.navigate(R.id.action_lista_servicos_to_editar_imagem_perfil)
                true
            }

            R.id.nav_lista_servicos-> {
                // Navegar para o fragment de serviços
                navController.navigate(R.id.action_login_to_lista_servicos)
                true
            }

            R.id.nav_login -> {
                // Adicionar lógica de sair
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}
