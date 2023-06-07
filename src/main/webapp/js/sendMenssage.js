/* Primera carga */
const long = document.querySelector("#longitud");
let rad = (360*mensaje.value.length)/144;
let progress = `--progress: ${rad}deg;`;
let caract = 144 - mensaje.value.length;
$("#longitud").attr("data-progress",`${caract}`);
$("#longitud").attr("style",`${progress}`);
$("#progress-bar-css").attr("href", "/css/progress/f1.css");
$("#mensaje-envio").attr("disabled", true);
/* Carga introduciendo texto */
const contarLongitud = () => {
    const mensaje = document.querySelector("#mensaje");
    let rad = (360*mensaje.value.length)/144;
    let progress = `--progress: ${rad}deg;`;
    let caract = 144 - mensaje.value.length;
    $("#longitud").attr("data-progress",`${caract}`);
    $("#longitud").attr("style",`${progress}`);
    if (caract >= 144){
        $("#mensaje-envio").attr("disabled", true);
    } else if(caract >= 10){
        $("#progress-bar-css").attr("href", "/css/progress/f1.css");
        $("#mensaje-envio").attr("disabled", false);
    } else if (caract >= 0 && caract <10){
        $("#progress-bar-css").attr("href", "/css/progress/f2.css");
        $("#mensaje-envio").attr("disabled", false);
    } else {
        $("#progress-bar-css").attr("href", "/css/progress/f3.css");
        $("#mensaje-envio").attr("disabled", true);
    }
}