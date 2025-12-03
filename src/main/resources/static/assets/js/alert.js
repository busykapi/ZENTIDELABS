

function login() {

    const id = document.querySelector('#userId');
    const pwd = document.querySelector('#pwd');


    if(id.value == "" || pwd.value == "") {
        alert("아이디 또는 비밀번호는 공백이 될 수 없습니다.");

    }else if(id.value == null){
        alert("존재하는 아이디가 아닙니다.");

    }else if(id.value != null && pwd.value == null){
        alert("비밀번호가 틀립니다.");

    }else{
         alert(id.value + "님 환영합니다!");
    }



}
