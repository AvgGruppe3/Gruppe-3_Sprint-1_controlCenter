'use strict';

const temp1 = document.querySelector('#temp1');
const temp2 = document.querySelector('#temp2');


function invokeGet(url, div){
    const xhr  = new XMLHttpRequest()
    xhr.open('GET', url, true)
    xhr.onload = function () {
        const temperature = xhr.responseText;
        if (xhr.readyState === 4 && xhr.status === 200) {
            div.innerHTML = temperature;
            if(temperature >35){
                div.style.background = '#910713';
            }else if(temperature >25){
                div.style.background = '#e69c25';
            } else{
                div.style.background = '#25e62b';
            }
        } else {
            console.error(xhr.responseText);
        }
    }
    xhr.send(null);
}
function doGet1() {
    const url  = "http://localhost:8081/temp1";
    invokeGet(url, temp1);
    setTimeout(doGet1, 5000);
}

function doGet2() {
    const url  = "http://localhost:8081/temp2";
    invokeGet(url, temp2);
    setTimeout(doGet2, 5000);
}

doGet1();
doGet2();