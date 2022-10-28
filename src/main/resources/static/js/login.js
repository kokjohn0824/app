// 設定常數
const emailRegex = /^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\.[a-z]{2,3}$/;
const accountRegex = /^\w+$/;
const passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,30}$/;
const url = "http://localhost:8082/public/api/";
const invalidAccountNameSet = new Set(["root", "admin", "管理員"]);
//抓取元素
const registerForm = document.querySelector("#registerForm");
const submitBtn = document.querySelector("#submitBtn");
const accountInput = document.querySelector("#accountInput");
const emailInput = document.querySelector("#emailInput");
const passwordInput = document.querySelector("#passwordInput");
const repeatPwdInput = document.querySelector("#repeatPwdInput");
const accountHelpBlock = document.querySelector("#accountHelpBlock");
const emailHelpBlock = document.querySelector("#emailHelpBlock");
const passwordHelpBlock = document.querySelector("#passwordHelpBlock");
const repeatPwdHelpBlock = document.querySelector("#repeatPwdHelpBlock");
const loginTab = document.getElementById("loginTab");
const registerTab = document.getElementById("registerTab");
const loginContent = document.getElementById("loginContent");
const registerContent = document.getElementById("registerContent");
const tabcontents = document.getElementsByClassName("tabcontent");
const mainCard = document.getElementById("mainCard");
const confirmEmailinput = document.getElementById("confirmEmailinput");
const confirmEmailbtn = document.getElementById("confirmEmailbtn");
const ResetPwdEmailinput = document.getElementById("ResetPwdEmailinput");

// 設定資料
let flags = {
  accountChecked: false,
  emailChecked: false,
  passwordChecked: false,
  repeatPwdChecked: false,
};

//form 表單清除預設送出行為

//設定 handler function
const handleSyncPassword = function (e) {
  if (!passwordInput) {
    repeatPwdInput.classList.remove("is-invalid");
    flags["repeatPwdChecked"] = false;
    repeatPwdHelpBlock.innerText = "";
    return;
  }

  if (e.target.value !== passwordInput.value) {
    repeatPwdHelpBlock.innerText = "您沒有對上您的密碼";
    repeatPwdInput.classList.add("is-invalid");
    flags["repeatPwdChecked"] = false;
    return;
  }
  flags["repeatPwdChecked"] = true;
  repeatPwdInput.classList.remove("is-invalid");
  repeatPwdHelpBlock.innerText = "OK!";
};

let myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

//TODO:帳號確認ajax請求
accountInput.addEventListener("blur", (e) => {
  let accountInput = e.target.value;
  if (!accountInput) {
    accountHelpBlock.innerText = "";
    e.target.classList.remove("is-invalid");
    flags["accountChecked"] = false;
    return;
  }
  //account Regex test
  if (!accountRegex.test(accountInput)) {
    accountHelpBlock.innerText = "帳號中不得有特殊字元";
    e.target.classList.add("is-invalid");
    flags["accountChecked"] = false;
    return;
  }

  //invalid accountname check
  if (invalidAccountNameSet.has(accountInput)) {
    accountHelpBlock.innerText = "無效的帳號名稱";
    e.target.classList.add("is-invalid");
    flags["accountChecked"] = false;
    return;
  }

  flags["accountChecked"] = true;
  e.target.classList.remove("is-invalid");
  accountHelpBlock.innerText = "OK!";
});

emailInput.addEventListener("blur", (e) => {
  let emailInput = e.target.value;
  if (!emailInput) {
    e.target.classList.remove("is-invalid");
    flags["emailChecked"] = false;
    emailHelpBlock.innerText = "";
    return;
  }
  if (!emailRegex.test(emailInput)) {
    flags["emailChecked"] = false;
    e.target.classList.add("is-invalid");
    emailHelpBlock.innerText = "Email格式不符";
    return;
  }
  let requestOptions = {
    method: "POST",
    headers: myHeaders,
    body: JSON.stringify({ email: emailInput }),
    redirect: "follow",
  };
  fetch(`${url}registration/checkEmail`, requestOptions)
    .then((response) => response.json())
    .then((result) => {
      if (result.emailExists) {
        e.target.classList.add("is-invalid");
        flags["emailChecked"] = false;
        emailHelpBlock.innerText = "Email帳號已存在！";
      } else {
        flags["emailChecked"] = true;
        e.target.classList.remove("is-invalid");
        emailHelpBlock.innerText = "Email帳號可使用";
      }
    })
    .catch((error) => console.error("error", error));
});

passwordInput.addEventListener("input", (e) => {
  let passwordInput = e.target.value;
  repeatPwdInput.addEventListener("input", (e) => {
    handleSyncPassword(e);
  });
  //確認空值
  if (!passwordInput) {
    e.target.classList.remove("is-invalid");
    flags["passwordChecked"] = false;
    passwordHelpBlock.innerText = "";
    repeatPwdInput.classList.remove("is-invalid");
    flags["repeatPwdChecked"] = false;
    repeatPwdHelpBlock.innerText = "";
    repeatPwdInput.removeEventListener("input", (e) => {
      handleSyncPassword(e);
    });
    return;
  }

  //確認合法
  if (!passwordRegex.test(passwordInput)) {
    e.target.classList.add("is-invalid");
    flags["passwordChecked"] = false;
    passwordHelpBlock.innerText = "密碼需符合規定";

    flags["repeatPwdChecked"] = false;
    repeatPwdHelpBlock.innerText = "";
    return;
  } else {
    e.target.classList.remove("is-invalid");
    flags["passwordChecked"] = true;
    passwordHelpBlock.innerText = "OK!";
  }

  //同步
  if (e.target.value !== repeatPwdInput.value) {
    repeatPwdHelpBlock.innerText = "您沒有對上您的密碼";
    repeatPwdInput.classList.add("is-invalid");
    flags["repeatPwdChecked"] = false;
    return;
  } else {
    repeatPwdHelpBlock.innerText = "OK!";
    repeatPwdInput.classList.remove("is-invalid");
    flags["repeatPwdChecked"] = true;
  }
});

// 送出註冊按鈕事件
submitBtn.addEventListener("click", (e) => {
  e.preventDefault();
  if (Object.values(flags).includes(false)) {
    return;
  }
  let registerdata = JSON.stringify({
    account: accountInput.value,
    email: emailInput.value,
    password: passwordInput.value,
  });
  let requestOptions = {
    method: "POST",
    headers: myHeaders,
    body: registerdata,
    redirect: "follow",
  };
  fetch(`${url}registration`, requestOptions)
    .then((response) => response.json())
    .then((result) => {
      if (result.error) {
        alert(result.error);
      } else {
        alert("已寄送確認信到您的信箱，請去信箱收信驗證");
      }
    })
    .catch((error) => console.error("error", error));
});

//設定 popover
var popoverTriggerList = [].slice.call(
  document.querySelectorAll('[data-bs-toggle="popover"]')
);
var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
  return new bootstrap.Popover(popoverTriggerEl);
});
8;
const showTab = function (e, action) {
  for (let item of tabcontents) {
    item.style.display = "none";
  }
  if (action === "login") {
    loginContent.style.display = "block";
    loginTab.classList.add("active");
    registerTab.classList.remove("active");
    mainCard.style.height = "500px";
  }
  if (action === "register") {
    registerContent.style.display = "block";
    registerTab.classList.add("active");
    loginTab.classList.remove("active");
    mainCard.style.height = "600px";
  }
};
//近來頁面先秀註冊頁面

//取得request.getparamter
function get(name) {
  if (
    (name = new RegExp("[?&]" + encodeURIComponent(name) + "=([^&]*)").exec(
      location.search
    ))
  )
    return decodeURIComponent(name[1]);
}
if (get("tab") === "register") {
  registerTab.click();
} else {
  loginTab.click();
}

//modal email send
confirmEmailbtn.addEventListener("click", (e) => {
  console.info(`ResetPwdEmailinput: ${ResetPwdEmailinput.value}`);
  let requestOptions = {
    method: "POST",
    headers: myHeaders,
    body: JSON.stringify({ email: ResetPwdEmailinput.value }),
    redirect: "follow",
  };

  fetch(`http://localhost:8082/public/api/user/resetpwd`, requestOptions)
    .then((response) => response.json())
    .then((result) => {
      alert(result.message);
    })
    .catch((error) => console.error("error", error));
});
