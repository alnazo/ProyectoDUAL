const {origin} = window.location;

function copyLink(tab){
    let url = origin+tab;
    navigator.clipboard.writeText(url);
    alert("El enlace ha sido copiado correctamente");
}