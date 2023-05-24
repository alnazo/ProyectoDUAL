let switcher = document.getElementById('switch-style');

let style = localStorage.getItem('style');
if (style == null) {
  setTheme('light');
} else {
  setTheme(style);
}

switcher.addEventListener('click', function(){
    if(style == 'light'){
        setTheme('dark');
    } else {
        setTheme('light');
    }
});

function setTheme(theme) {
  if (theme == 'light') {
    document.getElementById('switcher-id').href = '/css/light.css';
    switcher.innerText = "☀️";
    localStorage.setItem('style', 'light');
    style = localStorage.getItem('style');
  } else if (theme == 'dark') {
    document.getElementById('switcher-id').href = '/css/dark.css';
    switcher.innerText = "🌙";
    localStorage.setItem('style', 'dark');
    style = localStorage.getItem('style');
  }
}

$( function() {
    $("#nacimiento").datepicker({
        todayBtn: true,
        todayHighlight: true,
        clearBtn: true,
        language: "es",
   });

});



