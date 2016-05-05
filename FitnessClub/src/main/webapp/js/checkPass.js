/**
 * Created by aygulmardanova on 05.05.16.
 */


function checkmail(value) {

    reg = /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/;
    if (!value.match(reg)) {
        alert("Пожалуйста, введите свой настоящий e-mail");
        document.getElementById('email').value = "";
        return false;
    }
}



