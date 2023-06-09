function getPlan(){
    var servicio = document.getElementById("servicio");
    var servicioselect = servicio.value;

    let url = 'http://localhost:8081/api/servicios/'+servicioselect+'/getS';

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            var opciones = JSON.parse(xmlhttp.responseText);
            var plans = document.getElementById("plan");

            plans.innerHTML = "";

            var opcionSeleccionar = document.createElement("option");
            opcionSeleccionar.text = "Elija una opcion...";
            plans.add(opcionSeleccionar);

            for(var i = 0; i < opciones.length; i++){
                var option = document.createElement("option");
                option.text = opciones[i].plan;
                option.value = opciones[i].plan;
                plans.add(option);
            }
            plans.removeAttribute("disabled");
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();

}