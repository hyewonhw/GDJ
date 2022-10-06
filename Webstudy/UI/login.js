// 비밀번호 입력 없이 Sign In 버튼 누르면 경고창 띄우기
// 1. form이 서브밋 될때 
// 2. 버튼 클릭됐을때

document.getElementById('btn_signin').onclick = function(event) {
    var pw = document.getElementById('pw');
    if(pw.value == '') {
        alert('비밀번호를 입력하세요.');
        event.preventDefault();  /* 기본동작(서브밋) 막음 */ 
        return;
    }
}

// 아이디 조건안맞을때 띄울메시지
document.getElementById('id').onkeyup = function(event) {
    var id = document.getElementById('id');
    var id_msg = document.getElementById('id_msg');
    if(id.value.length == 0) {
        id_msg.textContent = '';
    }else if(id.value.length < 4) {
        id_msg.textContent = '아이디는 4자 이상입니다.';
    } else if (id.value.length >= 4) {
        id_msg.textContent = '정상적인 아이디입니다.';
    }
}

// 아이디 조건에 안맞아도 로그인 되는거 막기