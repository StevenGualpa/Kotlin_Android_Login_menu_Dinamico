package com.geek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity2 : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    lateinit var navigationView:NavigationView
    var drawerLayout: DrawerLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this);

        LLenarMenuNav();
    }

    fun LLenarMenuNav(){

        var menufinal = navigationView.menu
        val usuario = navigationView.getHeaderView(0).findViewById<TextView>(R.id.txtUsuario)
        val bundle = this.getIntent().getExtras()
        usuario.text=bundle?.getString("user")
        if (bundle != null) {

            var r :ArrayList<String> = bundle.getStringArrayList("items") as ArrayList<String>
            var i=0

            while (i<r.size)
            {
                menufinal.add(r[i].toString())
                menufinal.getItem(i).setIcon(R.drawable.menu_icono)
                i++
            }
        }

    }

    fun PresentaFragment(parm: Fragment){
        val frag = supportFragmentManager.beginTransaction()
        frag.replace(R.id.content_frame,parm)
        frag.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                drawerLayout = findViewById<DrawerLayout>(R.id.layutprincipal)
                drawerLayout?.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.toString()){
           "Inicio"->{
               PresentaFragment(fragmentinicio())
               MensajeLargo("Escogio Inicio")
           }
            "Camara"->{
                PresentaFragment(fragmentExample())
                MensajeLargo("Escogio Camara")
            }
            "Microfono"->{
                PresentaFragment(fragmentExample())
                MensajeLargo("Escogio Microfono")
            }
            "Materias"->{
                PresentaFragment(fragmentExample())
                MensajeLargo("Escogio Materias")
            }
            "Cursos"->{
                PresentaFragment(fragmentExample())
                MensajeLargo("Escogio Cursos")
            }
            "Salir"->{
                PresentaFragment(fragmentExample())
                MensajeLargo("Escogio Salir")
            }
            else ->{
                PresentaFragment(fragmentExample())
                MensajeLargo(item.toString())
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.layutprincipal)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun MensajeLargo(Mensaje: String)
    {
        Toast.makeText(this, Mensaje.toString(), Toast.LENGTH_LONG).show()

    }
}