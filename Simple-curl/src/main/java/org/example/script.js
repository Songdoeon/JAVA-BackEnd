
window.addEventListener("DOMContentLoaded",function(){
    'use strict';
    const loginForm = document.getElementById("loginForm");

    const loginFormValidate=function(form){
        if(form['userId'].value==''){
            alert("아이디가 비어있습니다.");
            form['userId'].focus();
            return false;
        }
        if(form['userPassword'].value=''){
            alert("비밀번호가 비어있습니다.");
            form['userPassword'].focus();
            return false;
        }
    }
    loginForm.addEventListener("submit",function(event){
        event.preventDefault();
        if(loginFormValidate(event.target)==false){
            return ;
        }
        const userId = event.target['userId'].value;
        const userPassword = event.target['userPassword'].value;
        doLogin(userId,userPassword).then((user)=>{
            const loginFormContainer = document.getElementById("loginFormContainer")
            const loginSuccessContainer = document.getElementById("loginSuccessContainer")
            loginFormContainer.setAttribute("style","display:none");
            loginSuccessContainer.setAttribute("style","display:block");

            const loginUserId = document.getElementById("login-userId");
            const loginUserName = document.getElementById("login-userName");
            const loginCartId = document.getElementById("login-cartId");
            loginUserId.setAttribute("style","color:aqua")
            loginUserName.setAttribute("style","color:aqua")
            loginCartId.setAttribute("style","color:aqua")

            loginUserId.innerText=user.userId;
            loginUserName.innerText=user.userName;
            loginCartId.innerText=user.cartId;
            return user;
        }).catch(e=>{
            alert("id password를 확인해주세요!")
            throw new Error(e.message);
        }).then((user)=>{
            return getCartItems(user.userId,user.cartId);
        }).catch(e=>{
            alert("cart-api error");
            throw new Error(e.message);
        }).then((items)=>{
                const cartTable = document.getElementById("cartTable");
                const body = cartTable.getElementsByTagName("tbody")[0];
                const intl = new Intl.NumberFormat();

                for (const item of items) {
                    const tr = document.createElement("tr");
                    const td1 = document.createElement("td");
                    const td2 = document.createElement("td");
                    const td3 = document.createElement("td");
                    const td4 = document.createElement("td");
                    const td5 = document.createElement("td");
                    td1.innerText=item.productId;
                    td2.innerText=item.name;
                    td3.innerText=intl.format(item.price);
                    td4.innerText=intl.format(item.amount);
                    td5.innerText= intl.format(item.totalPrice);
                    tr.append(td1,td2,td3,td4,td5);
                    body.append(tr);
                }
        });
    });
});

function doLogin(userId,userPassword){
    //...api 로그인 로직
    const promise = new Promise((resolve,reject)=>{
        const url = "http://133.186.144.236:8100/api/users/login";
    const xhr = new XMLHttpRequest();
    const data = {
        userId :userId,
        userPassword :"1234"
    }
        console.log(data.userId);
        console.log(data.userPassword);
    const userJson = JSON.stringify(data);
    xhr.addEventListener("load",function(){
        // if(this.status===200){
        //     const user = this.response;
        //     loginSuccess(user);
        // }
        if(this.status==200){
        const user = this.response;
        //TODO#8 로그인 성공시 loginSuccess 함수호출
        resolve(user);
    }else{
        alert(`id, password 를 확인해주세요! : ${this.status} :  ${this.response.message}` );
        throw new Error(this.response);
    }
    });

    xhr.addEventListener("error",function(e){
        alert("network error");
    });

    xhr.open("POST",url);
    xhr.setRequestHeader("Content-type","application/json");
    xhr.responseType = "json";
    // const user = JSON.parse(responseText) Json을 Object로 바꾸기
    xhr.send(userJson);
    });
    return promise;
    
}

// function loginSuccess(user){
//     const loginFormContainer = document.getElementById("loginFormContainer")
//     const loginSuccessContainer = document.getElementById("loginSuccessContainer")
//     loginFormContainer.setAttribute("style","display:none");
//     loginSuccessContainer.setAttribute("style","display:block");

//     const loginUserId = document.getElementById("login-userId");
//     const loginUserName = document.getElementById("login-userName");
//     const loginCartId = document.getElementById("login-cartId");

//     loginUserId.innerText=user.userId;
//     loginUserName.innerText=user.userName;
//     loginCartId.innerText=user.cartId;
// }

function getCartItems(userId, cartId){
    const promise = new Promise((resolve,reject)=>{
        const xhr = new XMLHttpRequest();
        const url ="http://133.186.144.236:8100/api/nhnmart/shopping-cart/" + cartId;

        xhr.addEventListener("load", function(){
            if(this.status==200){
                console.log(this.response);
                const items = this.response;
                //TODO#12 cart-api가 정상적으로 호출되면
                //parameter로 전달받은 displayCartIitems() 함수 호출
                resolve(this.response)
            }else{
                console.log(this.response);
                alert("cart-api error");
                throw new Error(this.response);
            }
        });
        xhr.addEventListener("error",function(){
            alert('network error');

        });
        xhr.open("GET",url);
    xhr.setRequestHeader("content-type","application/json");
    xhr.setRequestHeader("X-USER-ID", userId);
    xhr.responseType="json";
    xhr.send('');
    });
    return promise;
}