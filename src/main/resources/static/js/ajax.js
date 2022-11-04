const postdata = (url, requestJSON, callbackfunction) => {
  let myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");
  let registerdata = JSON.stringify(requestJSON);
  let requestOptions = {
    method: "POST",
    headers: myHeaders,
    body: registerdata,
    redirect: "follow",
  };
  fetch(url, requestOptions)
    .then((response) => response.json())
    .then((result) => {
      callbackfunction(result);
    })
    .catch((error) => console.error("error", error));
};
const getdata = (url, callbackfunction) => {
  let requestOptions = {
    method: "GET",
    redirect: "follow",
  };

  fetch(url, requestOptions)
    .then((response) => response.json())
    .then((result) => {
      tableData = result;
      callbackfunction(result);
    })
    .catch((error) => console.error("error", error));
};
console.log("ajax!");

const postdatas = (url, registerdata, callbackfunction) => {
  let myHeaders = new Headers();
  // myHeaders.append("Content-Type", "multipart/form-data");
  // let registerdata = JSON.stringify(requestJSON);
  let requestOptions = {
    method: "POST",
    headers: myHeaders,
    body: registerdata,
    redirect: "follow",
  };
  fetch(url, requestOptions)
    .then((response) => response.json())
    .then((result) => {
      callbackfunction(result);
    })
    .catch((error) => console.error("error", error));
};
