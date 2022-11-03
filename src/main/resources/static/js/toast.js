const toastContainer = document.getElementById("toastContainer");
const toastdiv = (message) => {
  let div = document.createElement("div");
  div.innerHTML = `<div id="liveToast" class="toast align-items-center" role="alert" aria-live="assertive" aria-atomic="true">
      <div class="d-flex">
        <div class="toast-body">
       ${message}
            </div>
      <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
    </div>
    </div>`;
  return div;
};

const addToastMessage = (element, message) => {
  element.addEventListener("click", (e) => {
    if (toastContainer.firstElementChild) {
      toastContainer.removeChild(toastContainer.firstElementChild);
    }
    toastContainer.append(toastdiv(message));
    let toastLiveExample = document.getElementById("liveToast");
    let toast = new bootstrap.Toast(toastLiveExample);
    toast.show();
  });
};

// 頁面載入後掃描class 並加入toast
document.querySelectorAll(".btn-addToCart").forEach((e) => {
  let productname = e.dataset.productname;
  let message = `已將${productname}加到購物車中`;
  addToastMessage(e, message);
  console.log("add");
});
