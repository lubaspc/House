package ertzil.com.huella

data class House (var id: String,
                  var Sala: Boolean,
                  var Dormitorio: Boolean,
                  var Cosina: Boolean,
                  var Bano: Boolean,
                  var temperatura: Double){

    constructor() : this("",true,true,true,true,0.0){

    }
}