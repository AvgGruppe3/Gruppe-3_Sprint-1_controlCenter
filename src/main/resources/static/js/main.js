'use strict';

const singleFileUploadError = document.querySelector('#singleFileUploadError');


function doGet() {
    const url  = "http://localhost:8081/temp";
    const xhr  = new XMLHttpRequest()
    xhr.open('GET', url, true)
    xhr.onload = function () {
        const temperature = xhr.responseText;
        if (xhr.readyState === 4 && xhr.status === 200) {
            singleFileUploadError.innerHTML = temperature;
            if(temperature >30){
                singleFileUploadError.style.background = '#910713';
            }else if(temperature >25){
                singleFileUploadError.style.background = '#e69c25';
            } else{
                singleFileUploadError.style.background = '#25e62b';
            }
        } else {
            console.error(xhr.responseText);
        }
    }
    xhr.send(null);

    setTimeout(doGet, 5000);
}
doGet();