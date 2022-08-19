package com.geek

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {


    //Controles Sesion
    var txtusuario:  TextInputLayout? = null
    var txtpassword:  TextInputLayout? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun BtnSesionAccion(view:View?)
    {

        txtusuario = findViewById(R.id.sesion_txt_usuario)
        txtpassword = findViewById(R.id.sesion_txt_password)
        val usuario :String= txtusuario!!.editText!!.text.toString()
        val password :String=txtpassword!!.editText!!.text.toString()
        ValidaUsuario(usuario,password)
    }

    fun ValidaUsuario(parm1:String, parm2:String)
    {
        val queue = Volley.newRequestQueue(this)
        val url: String = "https://my-json-server.typicode.com/StevenGualpa/api_dinamico/users"

        // Request a string response from the provided URL.
        val stringReq = StringRequest(
            Request.Method.GET, url,
            { response ->
                var strResp = response.toString()
                var str: JSONArray = JSONArray(strResp)

                //Contador
                var index=0
                var index2=0

                //Cantidad de Elementos
                var n=str.length()
                //Variables auxiliares  que usaremos
                var list_administrador = arrayListOf("Inicio", "Camara", "Microfono", "Materias","Cursos", "Salir")
                var list_estudiante = arrayListOf("Inicio","Materias", "Cursos", "Salir")
                var list_profesor = arrayListOf("Inicio", "Camara", "Microfono","Materias","Horario","Cursos", "Salir")
                var list_items = arrayListOf<String>()
                var usuario=""
                var password=""
                var rol=""
                var icon=""
                //Extraemos Elementos de eiquetas
               // var elemento: JSONObject =str.getJSONObject(index)
                //Toast.makeText(this, elemento.toString(), Toast.LENGTH_SHORT).show()

                while (index<n)
                {
                    var elemento: JSONObject =str.getJSONObject(index)


                    usuario=elemento.getString("username")
                    password=elemento.getString("password")
                    rol=elemento.getString("role")
                    icon=elemento.getString("icon")
                    if (usuario==parm1&&password==parm2) {
                        //SubJason
                        var str2: JSONArray = JSONArray(elemento.getString("items"))
                        while(index2<str2.length()){
                            var elemento2: JSONObject =str2.getJSONObject(index2)
                            list_items.add(elemento2.getString("itemname"))
                            index2++
                        }
                        index=1000
                    }
                    else
                        index++

                }
                if(index==1000){
                    MensajeLargo("Bienvenido: $parm1")
                    /*
                    if(rol=="Administrador"){
                        InvocaMenu(rol,password,list_items)
                    }
                    else if(rol=="Estudiante"){
                        InvocaMenu(rol,password,list_items)
                    }
                    else if(rol=="Docente"){
                        InvocaMenu(rol,password,list_items)
                    }

                     */
                    InvocaMenu(rol,icon,list_items)
                }
                else{
                    MensajeLargo("Error")
                }
            },
            { Log.d("API", "that didn't work") })
        queue.add(stringReq)
    }

    fun InvocaMenu(parm1: String, parm2: String, parm3: ArrayList<String>)
    {
        val intent = Intent(this, MainActivity2::class.java)
        val bundle = Bundle()
        bundle.putString("user",parm1)
        bundle.putString("icon", parm2)
        bundle.putStringArrayList("items", parm3)
        intent.putExtras(bundle)
        startActivity(intent)

    }

    fun MensajeLargo(Mensaje: String)
    {
        Toast.makeText(this, Mensaje.toString(), Toast.LENGTH_LONG).show()

    }
}