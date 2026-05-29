const selectServicio = document.querySelector("#servicioSelect");
const selectDoctor = document.querySelectorAll("#doctorSelect option");

selectServicio.addEventListener("change" , () => {
    const servicioId = selectServicio.value;

    selectDoctor.forEach(opcion => {

        if(opcion.value == 0){
            return;
        }
        if(opcion.dataset.servicio == servicioId){

            opcion.style.display = '';

        }else {

            opcion.style.display = 'none';

        }

    });
});
