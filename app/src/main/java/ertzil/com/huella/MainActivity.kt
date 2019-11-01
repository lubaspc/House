package ertzil.com.huella

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var ref = FirebaseDatabase.getInstance().getReference("House")
    lateinit var key:String
    var house = House()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = getSharedPreferences("PrimerInicio", Context.MODE_PRIVATE)
        key = prefs.getString("key", "")
        ref.child(key).addValueEventListener( object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(data: DataSnapshot) {
                house = data.getValue(House::class.java)!!
                cambioBaño(house!!.Bano)
                cambioCosina(house!!.Cosina)
                cambioDormitorio(house!!.Dormitorio)
                cambioSala(house!!.Sala)
                cambioTemp(house!!.temperatura)
            }

        })

        Sala.setOnClickListener {
           house.Sala = !house.Sala
            ref.child(key).child("Sala").setValue(house.Sala)
        }

        Dormitorio.setOnClickListener {
            house.Dormitorio = !house.Dormitorio
            ref.child(key).child("Dormitorio").setValue(house.Dormitorio)
        }

        Cosina.setOnClickListener {
            house.Cosina = !house.Cosina
            ref.child(key).child("Cosina").setValue(house.Cosina)
        }

        Bano.setOnClickListener {
            house.Bano= !house.Bano
            ref.child(key).child("Bano").setValue(house.Bano)
        }

        butAll.setOnClickListener {
            cambio(true)
        }
        butAny.setOnClickListener {
            cambio(false)
        }
    }

    fun cambioBaño(b:Boolean){
        if(b){
            Bano.setImageResource(R.drawable.bano_active)
        }else{
            Bano.setImageResource(R.drawable.bano_disabled)
        }
    }

    fun cambioCosina(b:Boolean){
        if(b){
            Cosina.setImageResource(R.drawable.cocina_active)
        }else{
            Cosina.setImageResource(R.drawable.cocina_disabled)
        }
    }

    fun cambioSala(b:Boolean){
        if(b){
            Sala.setImageResource(R.drawable.sala_active)
        }else{
            Sala.setImageResource(R.drawable.sala_disabled)
        }
    }

    fun cambioDormitorio(b:Boolean){
        if(b){
            Dormitorio.setImageResource(R.drawable.dormitorio_active)
        }else{
            Dormitorio.setImageResource(R.drawable.dormitorio_disabled)
        }
    }

    fun cambioTemp(temp:Double){
        inp_temp.text=temp.toString()
        if(temp<15){
            img_tmp.setImageResource(R.drawable.temp_baja_ico)
        }else if (temp>=15 && temp<25){
            img_tmp.setImageResource(R.drawable.temp_normal_ico)
        }else{
            img_tmp.setImageResource(R.drawable.temp_alta_ico)
        }
    }

    fun cambio(bol:Boolean){
        house.Sala=bol
        house.Cosina=bol
        house.Dormitorio=bol
        house.Bano=bol
        ref.child(key).child("Sala").setValue(house.Sala)
        ref.child(key).child("Dormitorio").setValue(house.Dormitorio)
        ref.child(key).child("Cosina").setValue(house.Cosina)
        ref.child(key).child("Bano").setValue(house.Bano)
    }


}
