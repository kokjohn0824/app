function loadImageFileAsURL() {
  let filesSelected = document.getElementById("inputFileToLoad").files;
  if (filesSelected.length > 0) {
    let fileToLoad = filesSelected[0];

    let fileReader = new FileReader();

    fileReader.onload = function (fileLoadedEvent) {
      fileDataURL = fileLoadedEvent.target.result;
    };

    fileReader.readAsDataURL(fileToLoad);
  }
}
const imag = () => {
  var inputFileToLoad = $("#inputFileToLoad");
  inputFileToLoad.change(function () {
    readURL(this);
  });
  function readURL(input) {
    if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function (e) {
      $("#preview_img").attr("src", e.target.result);
    };
    reader.readAsDataURL(input.files[0]);
  }
  }
  }

//將json陣列轉為 table


//文章管理function
const articleshow = () => {
  console.log("article in");
  document.querySelector("#table").style.opacity = 0;
  setTimeout(() => {
    getdata(showAllurl, (result) => {
      //make a copy of data
      [...initialTableData] = [...result];
      jsonToHTMLbyQforarticle("#table", result);
      document.querySelector("#table").style.opacity = 1;
      
      imag();
      let editorcontent;
      
      //修改文章搜尋
      $("button[name='editarticle']").click((e) => {
              $(".modal-content").html(`<div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel">文章修改</h5>
              <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
              ></button>
            </div>
            <form id="myForm">
              <div class="modal-body">
              <div class="mb-3">
              <label for="recipient-title" class="col-form-label"
              >文章標題:</label
              >
              <input
              name="title"
              type="text"
                    class="form-control"
                    id="article-title"
                    value=""
                    required
                  />
                </div>
                <div class="mb-3">
                文章種類:<select id="type">
                <option>減重知識</option>
                <option>運動教學</option>
                    <option>飲食營養</option>
                    <option>心得分享</option>
                  </select>
                  </div>
                  <div id="textareacontainer" class="mb-3"></div>
                  <div class="mb-3">
                  <label for="inputFileToLoad" class="col-form-label"
                  >文章圖片:</label
                  >
                  <input
                  id="inputFileToLoad"
                  type="file"
                  name="file"
                  onchange="loadImageFileAsURL()"
                  class="file-upload-default"
                  />
                  </div>
                  <div class="mb-3">
                  <img
                  id="preview_img"
                  src="#"
                  style="
                  height: 300px;
                  width: 300px;
                  border-radius: 60px 60px 60px 60px;
                  margin-left: 30px;
                  "
                  />
                  <input id="article_id" name="article_id" value="" hidden />
                  </div>
                  </div>
                  <div class="modal-footer">
                  <button
                  type="button"
                  class="btn btn-secondary"
                  data-bs-dismiss="modal"
                  >
                  Close
                  </button>
                  <button id="editar" type="submit" class="btn btn-primary">
                  Send
                  </button>
                  </div>
                  </form>`);
                  const textareacontainer = document.getElementById("textareacontainer");
                  textareacontainer.innerHTML = "";
                  textareacontainer.innerHTML = `<label for="message-editor" class="col-form-label"
            >文章內文:</label
          ><textarea id="editor"></textarea>`;
        getdata(
          "http://localhost:8082/article/edit?article_id=" +
            $(e.target).attr("id"),
          (result) => {
            console.log(result);
            $("#article-title").attr("value", result.標題);

            ClassicEditor.create(document.querySelector("#editor"), {
              extraPlugins: [MyCustomUploadAdapterPlugin],
            })
              .then((editor) => {
                editorcontent = editor;
                editor.setData(result.內文);
              })
              .catch((error) => {
                console.log(error);
              });
            $("#preview_img").attr(
              "src",
              `/public/showArticleImage/${result.ID}`
            );
            $("#article_id").attr("value", result.ID);
          }
        );

        //修改文章
      $("#editar").click((e) => {
        e.preventDefault();
        imag();
        var datas = new FormData();
        datas.append("article_id", $("#article_id").val());
        datas.append("title", $("#article-title").val());
        datas.append("text", editorcontent.getData());
        console.log($("#article_id").val());
        datas.append("type", $("#type option:selected").val());
        datas.append("file", $("#inputFileToLoad")[0].files[0]);
        $(".modal-content").html(""); 
        postdatas(
          "http://localhost:8082/admin/api/article/edit",
          datas,
          (result) => {
            if (result == true) {
              articleshow();
              alert("更新成功");
            }
          }
        );
      });
      });
      

      //刪除文章
      $(".btn-icon-delete").click((e) => {
        if (confirm("確定刪除嗎?") == true) {
          getdata(
            "http://localhost:8082/admin/api/article/delete?article_id=" +
              $(e.target).attr("id"),
            (result) => {
              if (result == true) {
                alert("已刪除");
                articleshow();
              } else {
                alert("文章有人收藏不能刪");
              }
            }
          );
        }
      });
    });
  }, 300);
};

///////文章管理//////////////////
const jsonToHTMLbyQforarticle = (querySelector, json) => {
  if (json.length == 0) {
    document.querySelector(querySelector).innerHTML = "";
    return;
  }
  //define table head
  let title = `<thead><tr>${Object.keys(json[0])
    .map((el) => `<th><strong>${el}</strong></th>`)
    .join("")}<th>圖片</th><th>操作</th></tr></thead>`;
  // define table body
  let trs = json.map(
    (el) =>
      `${Object.values(el)
        .map((td, index) => {
          if (index == 2) {
            td = el.內文.replace(/<[^>]*>/g, "");
            td = td.substring(0, 20) + "...";
            return `<td class="data">${td}</td>`;
          } else if (index == 5) {
            return `<td class="data">${td}</td><td><img style="width:120px; height:70px" src="/public/showArticleImage/${el.ID}"></td>`;
          } else {
            return `<td class="data">${td}</td>`;
          }
        })
        .join("")}<td><button id="${el.ID}"
            name="editarticle"
            type="button"
            data-bs-toggle="modal"
            data-bs-target="#exampleModal"
            data-bs-whatever="@getbootstrap" class="save btn-icon-edit"></button>
          <button id="${el.ID}" class="delete btn-icon-delete"></button></td>`
  );
  let tbody = `<tbody>${trs.map((el) => `<tr>${el}</tr>`).join("")}</tbody>`;
  let table = `<table class="table table-hover">${title}${tbody}</table>`;
  document.querySelector(querySelector).innerHTML = table;
};

const addarticle = (e) => {
  $(".modal-content").html(`<div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">新增文章</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <form id="myForm">
          <div class="modal-body">
            <div class="mb-3">
              <label for="article-title" class="col-form-label"
                >文章標題:</label
              >
              <input
                name="title"
                type="text"
                class="form-control"
                id="article-title"
                value=""
                required
              />
            </div>
            <div class="mb-3">
              <div id="textareacontainer" class="mb-3"></div>
            </div>
            <div class="mb-3">
              文章分類:<select id="type">
              <option>減重知識</option>
              <option>運動教學</option>
              <option>飲食營養</option>
              <option>心得分享</option>
              </select>
            </div>
            <div class="mb-3">
              <label for="inputFileToLoad" class="col-form-label"
                >文章封面:</label
              >
              <input
                id="inputFileToLoad"
                type="file"
                name="file"
                onchange="loadImageFileAsURL()"
                placeholder="請選擇圖片"
                class="file-upload-default"
              />
            </div>
            <div class="mb-3">
              <img
                id="preview_img"
                src=""
                style="
                  height: 300px;
                  width: 300px;
                  border-radius: 60px 60px 60px 60px;
                  margin-left: 30px;
                "
              />
              <input id="onsale" name="onsale" value="" hidden />
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              Close
            </button>
            <button id="add" type="submit" class="btn btn-primary">
              Send
            </button>
          </div>
        </form>`)
        imag();
        const textareacontainer = document.getElementById("textareacontainer");
        textareacontainer.innerHTML = "";
        textareacontainer.innerHTML = `<label for="message-editor" class="col-form-label"
            >文章內文:</label
          ><textarea id="editor"></textarea>`;

          ClassicEditor.create(document.querySelector("#editor"), {
            extraPlugins: [MyCustomUploadAdapterPlugin],
          })
            .then((editor) => {
              editorcontent = editor;
            })
            .catch((error) => {
              console.log(error);
            });

            $("#add").click((e) => {
              e.preventDefault();
              var datas= new FormData();
              datas.append("title", $("#article-title").val());
              datas.append("text", editorcontent.getData());
              datas.append("type", $("#type option:selected").val());
              datas.append("file", $("#inputFileToLoad")[0].files[0]);
              $(".modal-content").html(""); 

              postdatas(
                "http://localhost:8082/admin/api/addApiArticle/",
                datas,
                (result) => {
                  if (result == true) {
                    articleshow();
                    alert("新增成功");
                  }else{
                    articleshow();
                    alert("新增失敗")
                  }
                }
              );

            })

}

// 模糊搜尋觸發


searchinput.addEventListener("change", () => {

  switch(searchinput.dataset.searchtype){
    case "articlesearch":
      articlesearch();
      break;
    case "productsearch":
      productsearch();
      break;
    case "membersearch":
      membersearch();
      break;
    case "videosearch":
      videosearch();
      break;
    case "ordersearch":
      ordersearch();
      break;
  }
  
});


const articlesearch = () => {
  if (!document.getElementById("searchinput").value) {
    jsonToHTMLbyQforarticle("#table", initialTableData);
    return;
  }

  let result = selectMatchItem(
    tableData,
    document.getElementById("searchinput").value
  );
  jsonToHTMLbyQforarticle("#table", result ? result : []);
}

const productsearch = () => {
  if (!document.getElementById("searchinput").value) {
    jsonToHTMLbyProduct("#table", initialTableData);
    return;
  }

  let result = selectMatchItem(
    tableData,
    document.getElementById("searchinput").value
  );
  jsonToHTMLbyProduct("#table", result ? result : []);
}

const membersearch = () => {
  if (!document.getElementById("searchinput").value) {
    jsonToHTMLbyMember("#table", initialTableData);
    return;
  }

  let result = selectMatchItem(
    tableData,
    document.getElementById("searchinput").value
  );
  jsonToHTMLbyMember("#table", result ? result : []);
}

const videosearch = () => {
  if (!document.getElementById("searchinput").value) {
    jsonToHTMLbyVideo("#table", initialTableData);
    return;
  }

  let result = selectMatchItem(
    tableData,
    document.getElementById("searchinput").value
  );
  jsonToHTMLbyVideo("#table", result ? result : []);
}

const ordersearch = () =>{
  if (!document.getElementById("searchinput").value) {
    jsonToHTMLbyOrder("#table", initialTableData);
    return;
  }

  let result = selectMatchItem(
    tableData,
    document.getElementById("searchinput").value
  );
  jsonToHTMLbyOrder("#table", result ? result : []);
}
//按鈕觸發呈現table
showtable.addEventListener("click", (e) => {
  console.log("in");
  console.log(showtable.dataset.tabletype);
  switch (showtable.dataset.tabletype) {
    case "article":
      console.log("in");
      articleshow();
      break;
    case "product":
      productshow();
      break;
    case "member":
      usershow();
      break;
    case "video":
      videoshow()
      break;
    case "order":
      ordershow();
      break;
  }
});

addTypeAll.addEventListener("click", (e) => {
  console.log("in");
  console.log(addTypeAll.dataset.addtype);
  switch(addTypeAll.dataset.addtype){
    case "addarticle":
      addarticle();
      break;
    case "addvideo":
      addvideo();
      break;
    case "addproduct":
      addproduct();
      break;
  }
})



///////商品管理//////////////////
// 按鈕觸發呈現table 商品管理部分
// 將json陣列轉為 table 商品管理部分
const jsonToHTMLbyProduct = (querySelector, json) => {
    if (json.length == 0) {
      document.querySelector(querySelector).innerHTML = "";
      return;
    }
    //define table head
    let title = `<thead><tr>${Object.keys(json[0])
      .map((el) => `<th><strong>${el}</strong></th>`)
      .join("")}<th>商品圖</th><th>操作</th></tr></thead>`;
    // define table body
    let trs = json.map(
      (el) =>
        `${Object.values(el)
          .map((td, index) => {
            if (index == 4) {
              if (td == 1) {
                return `<td class="data"><span id="${td}" name="${el.product_id}" style='color:green'>販售中</span></td>`;
              } else {
                return `<td class="data"><span id="${td}" name="${el.product_id}" style='color:red'>下架中</span></td>`;
              }
            } else if (index == 7) {
              return `<td><class="date">${td}</td><td><img style="width:50px; height:50px" src="/admin/downloadImage/${el.product_id}"></td>`;
            } else {
              return `<td class="data">${td}</td>`;
            }
          })
          .join("")}<td><button id="${el.product_id}"
            name="checkboxone"
            type="button"
            data-bs-toggle="modal"
            data-bs-target="#exampleModal"
            data-bs-whatever="@getbootstrap" class="save btn-icon-edit"></button>
          <button id="${el.product_id}"
            name="deletebox" class="delete btn-icon-delete"></button><button name="onsale" id="${
              el.product_id
            }" class="btn btn-primary">上下架</button></td>`
    );
    let tbody = `<tbody>${trs
      .map((el) => `<tr>${el}</tr>`)
      .join("")}</tbody>`;
    let table = `<table class="table table-hover">${title}${tbody}</table>`;
    document.querySelector(querySelector).innerHTML = table;
  };
const productshow = () => {
      document.querySelector("#table").style.opacity = 0;
      setTimeout(() => {
        getdata(showAllurl, (result) => {
          //make a copy of data
          [...initialTableData] = [...result];
          jsonToHTMLbyProduct("#table", result);
          document.querySelector("#table").style.opacity = 1;
          imag();
          //商品上下架
          $("button[name='onsale']").click(
            (updateOnsale = (e) => {
              var myHeaders = new Headers();
              myHeaders.append("Content-Type", "application/json");
              var id = $(e.target).attr("id");
              console.log(id);
              var onsale = $(`span[name="${id}"]`).attr("id");
              console.log(onsale);
              var requestOptions = {
                method: "POST",
                headers: myHeaders,
                redirect: "follow",
              };

              fetch(
                `http://localhost:8082/admin/api/changeOnsale/${id}/${onsale}`,
                requestOptions
              )
                .then((response) => response.json())
                .then((result) => {
                  console.log(result);
                  if (result.onsale == 1) {
                    $(`span[name="${$(e.target).attr("id")}"]`)
                      .attr("id", result.onsale)
                      .attr("style", "color:green")
                      .text("販售中");
                  } else {
                    $(`span[name="${$(e.target).attr("id")}"]`)
                      .attr("id", result.onsale)
                      .attr("style", "color:red")
                      .text("下架中");
                  }
                })
                .catch((error) => console.log("error", error));
            })
          );

          $("button[name='checkboxone']").click(
            (e) => {
              console.log("好痛苦");

              $(".modal-content").html(`<div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel">商品修改</h5>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <form id="myForm">
              <div class="modal-body">
                <div class="mb-3">
                  <label for="recipient-title" class="col-form-label"
                    >商品名稱:</label
                  >
                  <input
                    name="title"
                    type="text"
                    class="form-control"
                    id="recipient-title"
                    value=""
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="message-price" class="col-form-label"
                    >商品價格:</label
                  >
                  <input
                    name="price"
                    type="number"
                    min="0"
                    class="form-control"
                    id="recipient-price"
                    value=""
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="message-stock" class="col-form-label"
                    >商品庫存:</label
                  >
                  <input
                    name="stock"
                    type="number"
                    class="form-control"
                    id="recipient-stock"
                    value=""
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="message-text" class="col-form-label"
                    >商品簡介:</label
                  >
                  <textarea
                    name="text"
                    class="form-control"
                    id="recipient-text"
                    required
                  ></textarea>
                </div>
                <div class="mb-3">
                  商品種類:<select id="type">
                    <option>運動食品</option>
                    <option>運動用品</option>
                  </select>
                </div>
                <div class="mb-3">
                  <label for="inputFileToLoad" class="col-form-label"
                    >商品圖片:</label
                  >
                  <input
                    id="inputFileToLoad"
                    type="file"
                    name="file"
                    onchange="loadImageFileAsURL()"
                    class="file-upload-default"
                  />
                </div>
                <div class="mb-3">
                  <img
                    id="preview_img"
                    th:src="@{#}"
                    style="
                      height: 300px;
                      width: 300px;
                      border-radius: 60px 60px 60px 60px;
                      margin-left: 30px;
                    "
                  />
                  <input id="onsale" name="onsale" value="" hidden />
                  <input id="product_id" name="product_id" value="" hidden />
                </div>
              </div>
              <div class="modal-footer">
                <button
                  type="button"
                  class="btn btn-secondary"
                  data-bs-dismiss="modal"
                >
                  Close
                </button>
                <button id="send" type="submit" class="btn btn-primary">
                  Send
                </button>
              </div>
            </form>`)

              var product_id = $(e.target).attr("id");
              var requestOptions = {
                method: "GET",
                redirect: "follow",
              };
              fetch(
                "http://localhost:8082/admin/updateProduct?product_id=" +
                  product_id,
                requestOptions
              )
                .then((response) => response.json())
                .then((result) => {
                  console.log(result.product_id);
                  $("#recipient-title").attr("value", result.title);
                  $("#recipient-price").attr("value", result.price);
                  $("#recipient-stock").attr("value", result.stock);
                  $("#recipient-text").text(result.text);
                  $("#onsale").attr("value", result.onsale);
                  $("#product_id").attr("value", result.product_id);
                  $("#preview_img").attr(
                    "src",
                    "/admin/downloadImage/" + result.product_id
                  );
                })
                .catch((error) => console.log("error", error));

                $("#send").click((e) => {
                  e.preventDefault();
                  var datas = new FormData();
                  datas.append("product_id", $("#product_id").val());
                  datas.append("title", $("#recipient-title").val());
                  datas.append("price", $("#recipient-price").val());
                  datas.append("stock", $("#recipient-stock").val());
                  datas.append("text", $("#recipient-text").val());
                  datas.append("type", $("#type option:selected").text());
                  datas.append("onsale", $("input[name='onsale']").val());
                  datas.append("file", $("#inputFileToLoad")[0].files[0]);
                  
                  var requestOptions = {
                    method: "POST",
                    body: datas,
                    redirect: "follow",
                  };
                  $(".modal-content").html("");
    
                  fetch(
                    "http://localhost:8082/admin/api/updateProduct",
                    requestOptions
                  )
                    .then((response) => response.json())
                    .then((result) => {
                      // console.log(result);
                      if (result == true) {
                        productshow();
                        alert("更新成功");
                      } else {
                        productshow();
                        alert("更新失敗");
                      }
                    })
                    .catch((error) => console.log("error", error));
                })
            }
          );
          

          $("button[name='deletebox']").click(
            (deleteproduct = (e) => {
              if (confirm("確定刪除嗎?") == true) {
                var myHeaders = new Headers();
                myHeaders.append("Content-Type", "application/json");

                var requestOptions = {
                  method: "GET",
                  headers: myHeaders,
                  redirect: "follow",
                };

                fetch(
                  "http://localhost:8082/public/api/deleteProduct?product_id=" +
                    $(e.target).attr("id"),
                  requestOptions
                )
                  .then((response) => response.json())
                  .then((result) => {
                    if (result) {
                      productshow();
                    } else {
                      alert("訂單中有此商品故不可刪除");
                      productshow();
                    }
                  })
                  .catch((error) => console.log("error", error));
              }
            })
          );
        });
      }, 300);
    }

const addproduct = () => {
  $(".modal-content").html(`<div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">商品新增</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <form id="myForm">
          <div class="modal-body">
            <div class="mb-3">
              <label for="recipient-title" class="col-form-label"
                >商品名稱:</label
              >
              <input
                name="title"
                type="text"
                class="form-control"
                id="recipient-title"
                value=""
                required
              />
            </div>
            <div class="mb-3">
              <label for="message-price" class="col-form-label"
                >商品價格:</label
              >
              <input
                name="price"
                type="number"
                min="0"
                class="form-control"
                id="recipient-price"
                value=""
                required
              />
            </div>
            <div class="mb-3">
              <label for="message-stock" class="col-form-label"
                >商品庫存:</label
              >
              <input
                name="stock"
                type="number"
                class="form-control"
                id="recipient-stock"
                value=""
                required
              />
            </div>
            <div class="mb-3">
              <label for="message-text" class="col-form-label"
                >商品簡介:</label
              >
              <textarea
                name="text"
                class="form-control"
                id="recipient-text"
                required
              ></textarea>
            </div>
            <div class="mb-3">
              商品種類:<select id="type">
                <option>運動食品</option>
                <option>運動用品</option>
              </select>
            </div>
            <div class="mb-3">
            <label for="message-onsale" class="col-form-label"
                >販售狀態:</label
              >
              <input
                type="radio"
                name="onsale"
                id="1"
                value="1"
                required
              /><label for="1">上架</label>
              <input
                type="radio"
                name="onsale"
                id="0"
                value="0"
                checked="checked"
                required
              /><label for="0">下架</label>
            </div>
            <div class="mb-3">
              <label for="inputFileToLoad" class="col-form-label"
                >商品圖片:</label
              >
              <input
                id="inputFileToLoad"
                type="file"
                name="file"
                onchange="loadImageFileAsURL()"
                class="file-upload-default"
              />
            </div>
            <div class="mb-3">
              <img
                id="preview_img"
                src="/img/lv1.png"
                style="
                  height: 300px;
                  width: 300px;
                  border-radius: 60px 60px 60px 60px;
                  margin-left: 30px;
                "
              />
              <input id="onsale" name="onsale" value="" hidden />
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              Close
            </button>
            <button id="add" type="submit" class="btn btn-primary">
              Send
            </button>
          </div>
        </form>`)
        imag();
        $("#add").on("click",
          (e) => {
            e.preventDefault();
            var datas = new FormData();
            datas.append("title", $("#recipient-title").val());
            datas.append("price", $("#recipient-price").val());
            datas.append("stock", $("#recipient-stock").val());
            datas.append("text", $("#recipient-text").val());
            datas.append("type", $("#type option:selected").text());
            datas.append("onsale", $("input[name='onsale']").val());
            datas.append("file", $("#inputFileToLoad")[0].files[0]);
            
            var requestOptions = {
              method: "POST",
              body: datas,
              redirect: "follow",
            };
            $(".modal-content").html("");
            fetch(
              "http://localhost:8082/admin/api/addProduct",
              requestOptions
            )
              .then((response) => response.json())
              .then((result) => {
                // console.log(result);
                if (result == true) {
                  productshow();
                  alert("新增成功");
                } else {
                  productshow();
                  alert("新增失敗");
                }
              })
              .catch((error) => console.log("error", error));
          })
}


///////會員管理//////////////////
//將json陣列轉為 table
const jsonToHTMLbyMember = (querySelector, json) => {
    if (json.length == 0) {
      document.querySelector(querySelector).innerHTML = "";
      return;
    }
    //define table head
    let title = `<thead><tr>${Object.keys(json[0])
      .map((el) => `<th><strong>${el}</strong></th>`)
      .join("")}<th>操作</th></tr></thead>`;
    // define table body
    let trs = json.map(
      (el) =>
        `${Object.values(el)
          .map((td, index) => {
            if (index == 4) {
              if (td == false) {
                return `<td class="data"><input id="${el.users_id}" name="locked" value="${td}" hidden /><span id="${el.users_id}" name="locked" style="color:green">合法帳戶</span></td>`;
              } else {
                return `<td class="data"><input id="${el.users_id}" name="locked" value="${td}" hidden /><span id="${el.users_id}" name="locked" style="color:red">停權帳戶</span></td>`;
              }
            } else {
              return `<td class="data">${td}</td>`;
            }
          })
          .join("")}<td><button id="${el.users_id}" name="editMember"
            type="button"
            data-bs-toggle="modal"
            data-bs-target="#exampleModal"
            data-bs-whatever="@getbootstrap" class="save btn-icon-edit"></button>
          </td><td><button id="${
            el.users_id
          }" name="locked" class="btn btn-danger">停權</button></td>`
    );
    let tbody = `<tbody>${trs
      .map((el) => `<tr>${el}</tr>`)
      .join("")}</tbody>`;
    let table = `<table class="table table-hover">${title}${tbody}</table>`;
    document.querySelector(querySelector).innerHTML = table;
  };

  
  //按鈕觸發呈現table
  const usershow = () => {
    document.querySelector("#table").style.opacity = 0;
    setTimeout(() => {
      getdata(showAllurl, (result) => {
        //make a copy of data
        [...initialTableData] = [...result];
        jsonToHTMLbyMember("#table", result);
        document.querySelector("#table").style.opacity = 1;
        $(`button[id="${$("#userID").val()}"]`).hide();
        $(".modal-content").html(`<div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">會員資料</h5>
        <button
          type="button"
          class="btn-close"
          data-bs-dismiss="modal"
          aria-label="Close"
        ></button>
      </div>
      <form id="myForm">
        <div class="modal-body">
          <div class="mb-3">
            <label for="recipient-nickname" class="col-form-label"
              >暱稱:</label
            >
            <input
              name="nickname"
              type="text"
              class="form-control"
              id="recipient-nickname"
              value=""
              required
            />
          </div>
          <div class="mb-3">
            <label for="recipient-gender" class="col-form-label"
              >性別:</label
            ><label for="recipient-man" class="col-form-label">男:</label
            ><input
              name="gender"
              id="recipient-man"
              type="radio"
              value="1"
            />
            <label for="recipient-female" class="col-form-label">女:</label
            ><input
              name="gender"
              id="recipient-female"
              type="radio"
              value="0"
            />
          </div>
          <div class="mb-3">
            <label for="recipient-age" class="col-form-label">年齡:</label
            ><input
              id="recipient-age"
              name="age"
              type="number"
              min="0"
              class="form-control"
            />
          </div>
          <div class="mb-3">
            <label for="recipient-height" class="col-form-label"
              >身高:</label
            ><input
              id="recipient-height"
              name="height"
              type="number"
              step="0.1"
              min="0"
              class="form-control"
            />
          </div>
          <div class="mb-3">
            <label for="recipient-weight" class="col-form-label"
              >體重:</label
            ><input
              id="recipient-weight"
              name="weight"
              type="number"
              step="0.1"
              min="0"
              class="form-control"
            />
          </div>
          <div class="mb-3">
            <label for="recipient-bodyFat" class="col-form-label"
              >體脂肪:</label
            ><input
              id="recipient-bodyFat"
              name="bodyFat"
              type="number"
              step="0.1"
              min="0"
              class="form-control"
            />
          </div>
          <div class="mb-3">
            <label for="recipient-visceralFat" class="col-form-label"
              >內臟脂肪:</label
            ><input
              id="recipient-visceralFat"
              name="visceralFat"
              type="number"
              step="0.1"
              min="0"
              class="form-control"
            />
          </div>
          <div class="mb-3">
            <label for="recipient-muscleMass" class="col-form-label"
              >肌肉量:</label
            ><input
              id="recipient-muscleMass"
              name="muscleMass"
              type="number"
              step="0.1"
              min="0"
              class="form-control"
            />
          </div>
          <div class="mb-3">
            <label for="recipient-becomeVIP" class="col-form-label"
              >VIP:</label
            ><label for="recipient-man" class="col-form-label">是:</label
            ><input
              name="becomeVIP"
              id="recipient-on"
              type="radio"
              value="1"
            />
            <label for="recipient-becomeVIP" class="col-form-label"
              >否:</label
            ><input
              name="becomeVIP"
              id="recipient-off"
              type="radio"
              value="0"
            />
          </div>

          <div class="mb-3">
            <label for="inputFileToLoad" class="col-form-label"
              >會員圖片:</label
            >
            <input
              id="inputFileToLoad"
              type="file"
              name="file"
              onchange="loadImageFileAsURL()"
              class="file-upload-default"
            />
          </div>
          <div class="mb-3">
            <img
              id="preview_img"
              src="#"
              style="
                height: 300px;
                width: 300px;
                border-radius: 60px 60px 60px 60px;
                margin-left: 30px;
              "
            />
            <input id="member_id" name="member_id" value="" hidden />
          </div>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            Close
          </button>
          <button id="editmember" type="submit" class="btn btn-primary">
            Send
          </button>
        </div>
      </form>`)
        $("input[name='locked']").each((index, ele) => {
            var id = $(ele).attr("id");
            if ($(ele).val() == "false") {
              $(`button[id="${id}"][name="locked"]`)
                .attr("class", "btn btn-danger")
                .text("停權");
            } else if ($(ele).val() == "true") {
              $(`button[id="${id}"][name="locked"]`)
                .attr("class", "btn btn-success")
                .text("復權");
            }
          });
          
          $("button[name='locked']").click((e) => {
            var locked;
            var users_id = $(e.target).attr("id");
            var input = $(`input[id='${users_id}']`).val();
            if (input == "false") {
              locked = false;
            } else if (input == "true") {
              locked = true;
            }
            var jsn = { users_id: users_id, locked: locked };
            postdata(
              "http://localhost:8082/admin/api/updateLocked/",
              jsn,
              (result) => {
                console.log(result);
                if (result == true) {
                  $(e.target).attr("class", "btn btn-success").text("復權");
                  $(`input[id='${users_id}']`).attr("value", "true");
                  $(`span[id="${users_id}"]`)
                  .attr("style", "color:red")
                  .text("停權帳戶");
                } else {
                  $(e.target).attr("class", "btn btn-danger").text("停權");
                  $(`input[id='${users_id}']`).attr("value", "false");
                  $(`span[id="${users_id}"]`)
                  .attr("style", "color:green")
                  .text("合法帳戶");
                }
              }
              );
              
              if(locked == false){
                alert('正在發送email請稍候');
                getdatas(`http://localhost:8082/admin/api/userEmailSend/${users_id}`, (result) =>{
                  console.log(result)
                  if(result == "success"){
                    alert("email發送成功");
                  }else{
                    alert("發送失敗");
                  }``
              })
            }else if(locked == true){
              alert('正在發送email請稍候');
              getdatas(`http://localhost:8082/admin/api/userOnEmailSend/${users_id}`, (result) =>{
                console.log(result)
                if(result == "success"){
                  alert("email發送成功");
                }else{
                  alert("發送失敗");
                }
            })
            }
          });

          $("button[name='editMember']").click((e) => {

            getdata(
              `http://localhost:8082/admin/api/queryUpdateMember/${$(
                e.target
              ).attr("id")}`,
              (result) => {
                $("#recipient-nickname").val(result.nickname);
                if (result.gender == 1) {
                  $("#recipient-man").attr("checked", true);
                } else {
                  $("#recipient-female").attr("checked", true);
                }
                $("#recipient-age").val(result.age);
                $("#recipient-height").val(result.height);
                $("#recipient-weight").val(result.weight);
                $("#recipient-bodyFat").val(result.bodyFat);
                $("#recipient-visceralFat").val(result.visceralFat);
                $("#recipient-muscleMass").val(result.muscleMass);
                if (result.becomeVIP == 1) {
                  $("#recipient-on").attr("checked", true);
                } else {
                  $("#recipient-off").attr("checked", true);
                }
                $("#preview_img").attr(
                  "src",
                  `/public/showMemberImage/${result.member_id}`
                );
                $("#member_id").val(result.member_id);
              }
            );
          });

          $("#editmember").click((e) => {
            e.preventDefault();
            var datas = new FormData();
            datas.append(
              "becomeVIP",
              $("input[name='becomeVIP']:checked").val() == 1 ? 1 : 0
            );
            datas.append("member_id", $("#member_id").val());
            $(".modal-content").html("")
            postdatas(
              `http://localhost:8082/admin/api/updateVipById/`,
              datas,
              (result) => {
                if (result == true) {
                  alert('已更新');
                  usershow();
                }
              }
            );
          });
        });
      },300)
    }

///////影片管理//////////////////
//將json陣列轉為 table
const jsonToHTMLbyVideo = (querySelector, json) => {
    if (json.length == 0) {
      document.querySelector(querySelector).innerHTML = "";
      return;
    }
    //define table head
    let title = `<thead><tr>${Object.keys(json[0])
      .map((el) => `<th><strong>${el}</strong></th>`)
      .join("")}<th>影片封面</th><th>操作</th></tr></thead>`;
    // define table body
    let trs = json.map(
      (el) =>
        `${Object.values(el)
          .map((td, index) => {
            if (index == 5) {
              return `<td class="data"><video
                      style="width: 250px; border-radius: 20px"
                      controls="controls"
                      preload="auto"
                      autoplay="true"
                      loop="loop"
                      justify-content="center"
                    >
                      <source src="/public/video/${td}" type="video/mp4" />
                    </video></td><td class="data"><img style="width:120px; height:70px" src="/public/showVideoImage/${el.ID}"></td>`;
            } else {
              return `<td class="data">${td}</td>`;
            }
          })
          .join("")}<td><button id="${el.ID}" data-bs-toggle="modal"
            data-bs-target="#exampleModal"
            data-bs-whatever="@getbootstrap" class="save btn-icon-edit"></button>
          <button id="${el.ID}" class="delete btn-icon-delete"></button></td>`
    );
    let tbody = `<tbody>${trs
      .map((el) => `<tr>${el}</tr>`)
      .join("")}</tbody>`;
    let table = `<table class="table table-hover">${title}${tbody}</table>`;
    document.querySelector(querySelector).innerHTML = table;
  };

  //按鈕觸發呈現table
  const videoshow = () => {
      document.querySelector("#table").style.opacity = 0;
      setTimeout(() => {
        getdata(showAllurl, (result) => {
          //make a copy of data
          [...initialTableData] = [...result];
          jsonToHTMLbyVideo("#table", result);
          document.querySelector("#table").style.opacity = 1;

            imag();
          $(".btn-icon-delete").click((e) => {
            if (confirm("確定刪除嗎") == true) {
              getdata(
                `http://localhost:8082/admin/api/video/delete/${$(
                  e.target
                ).attr("id")}`,
                (result) => {
                  if (result == true) {
                    alert("影片中有用戶按讚故不能刪除");
                    video();
                  } else {
                    alert("已刪除");
                    video();
                  }
                }
              );
            }
          });

          $(".btn-icon-edit").click((e) => {

            $(".modal-content").html(`<div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Video修改/h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <form id="myForm">
          <div class="modal-body">
            <div class="mb-3">
              <label for="recipient-title" class="col-form-label"
                >標題:</label
              >
              <input
                name="title"
                type="text"
                class="form-control"
                id="recipient-title"
                value=""
                required
              />
            </div>

            <div class="mb-3">
              <label for="recipient-age" class="col-form-label">分類:</label
              ><select name="type">
                <option>胸肌</option>
                <option>背肌</option>
                <option>腿肌</option>
                <option>肩膀</option>
                <option>腹部</option>
                <option>手臂</option>
                <option>有氧</option>
                <option>伸展</option>
              </select>
            </div>
            <div class="mb-3">
              <label for="recipient-height" class="col-form-label"
                >主要部位:</label
              ><select name="body_parts">
                <optgroup label="胸肌">
                  <option>胸大肌</option>
                </optgroup>
                <optgroup label="背肌">
                  <option>背闊肌</option>
                  <option>中背部</option>
                  <option>下背部</option>
                </optgroup>
                <optgroup label="腿肌">
                  <option>股四頭肌</option>
                  <option>股二頭肌</option>
                  <option>臀部肌群</option>
                  <option>小腿肌群</option>
                </optgroup>
                <optgroup label="肩膀">
                  <option>斜方肌</option>
                  <option>肩部</option>
                </optgroup>
                <optgroup label="腹部">
                  <option>腹肌</option>
                  <option>髂腰肌</option>
                </optgroup>
                <optgroup label="手臂">
                  <option>肱二頭肌</option>
                  <option>肱三頭肌</option>
                  <option>前臂</option>
                </optgroup>
                <optgroup label="有氧">
                  <option>不限部位</option>
                </optgroup>
                <optgroup label="伸展">
                  <option>不限部位</option>
                </optgroup>
              </select>
            </div>
            <div class="mb-3">
              <label for="inputFileToLoad" class="col-form-label"
                >影片封面:</label
              >
              <input
                id="inputFileToLoad"
                type="file"
                name="file"
                onchange="loadImageFileAsURL()"
                class="file-upload-default"
              />
            </div>
            <div class="mb-3">
              <img
                id="preview_img"
                src="#"
                style="
                  height: 300px;
                  width: 300px;
                  border-radius: 60px 60px 60px 60px;
                  margin-left: 30px;
                "
              />
              <input id="video_id" name="video_id" value="" hidden />
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              Close
            </button>
            <button id="editvideo" type="submit" class="btn btn-primary">
              Send
            </button>
          </div>
        </form>`)
        imag();
            getdata(
              `http://localhost:8082/admin/api/queryUpdateViedo/${$(
                e.target
              ).attr("id")}`,
              (result) => {
                $("#recipient-title").val(result.標題);
                $("#preview_img").attr(
                  "src",
                  `/public/showVideoImage/${result.ID}`
                );
                $("#video_id").val(result.ID);
              }
            );

            $("#editvideo").click((e) => {
              e.preventDefault();
              var datas = new FormData();
              datas.append("video_id", $("#video_id").val());
              datas.append("title", $("#recipient-title").val());
              datas.append(
                "type",
                $("select[name='type'] option:selected").text()
              );
              datas.append(
                "body_parts",
                $("select[name='body_parts'] option:selected").text()
              );
              datas.append("file", $("#inputFileToLoad")[0].files[0]);
              $(".modal-content").html("")
              postdatas(
                "http://localhost:8082/admin/api/video/edit",
                datas,
                (result) => {
                  if (result == true) {
                    alert("已更新");
                    videoshow();
                  }
                }
              );
            });

          });

          
        });
      }, 300);
    }


    const addvideo = () => {
      $(".modal-content").html(`<div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">新增文章</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <form id="myForm">
          <div class="modal-body">
            <div class="mb-3">
              <label for="article-title" class="col-form-label"
                >影片標題:</label
              >
              <input
                name="title"
                type="text"
                class="form-control"
                id="article-title"
                value=""
                required
              />
            </div>
            <div class="mb-3">
              <div id="textareacontainer" class="mb-3"></div>
            </div>
            <div class="mb-3">
              影片分類:<select id="type">
              <option>胸肌</option>
              <option>背肌</option>
              <option>腿肌</option>
              <option>肩膀</option>
              <option>腹部</option>
              <option>手臂</option>
              <option>有氧</option>
              <option>伸展</option>
              </select>
            </div>
            <div class="mb-3">
              訓練肌群:<select id="body_parts">
                  <optgroup label="胸肌">
                    <option>胸大肌</option>
                  </optgroup>
                  <optgroup label="背肌">
                    <option>背闊肌</option>
                    <option>中背部</option>
                    <option>下背部</option>
                  </optgroup>
                  <optgroup label="腿肌">
                    <option>股四頭肌</option>
                    <option>股二頭肌</option>
                    <option>臀部肌群</option>
                    <option>小腿肌群</option>
                  </optgroup>
                  <optgroup label="肩膀">
                    <option>斜方肌</option>
                    <option>肩部</option>
                  </optgroup>
                  <optgroup label="腹部">
                    <option>腹肌</option>
                    <option>髂腰肌</option>
                  </optgroup>
                  <optgroup label="手臂">
                    <option>肱二頭肌</option>
                    <option>肱三頭肌</option>
                    <option>前臂</option>
                  </optgroup>
                  <optgroup label="有氧">
                    <option>不限部位</option>
                  </optgroup>
                  <optgroup label="伸展">
                    <option>不限部位</option>
                  </optgroup>
              </select>
            </div>
            <div class="mb-3">
              <label for="inputFileToLoad" class="col-form-label"
                >影片封面:</label
              >
              <input
                id="inputFileToLoad"
                type="file"
                name="picture"
                onchange="loadImageFileAsURL()"
                placeholder="請選擇圖片"
                class="file-upload-default"
              />
            </div>
            <div class="mb-3">
            <img
            id="preview_img"
            src=""
            style="
            height: 300px;
            width: 300px;
            border-radius: 60px 60px 60px 60px;
            margin-left: 30px;
            "
            />
            </div>
            <div class="mb-3">
              <label for="inputFileToLoad" class="col-form-label"
                >選擇影片:</label
              >
              <input id="inputFileToLoad2" type="file" name="myFiles" class="file_multi_video upload_cover"
              accept="video/*"/>
            <video autoplay muted class="video_show displayNone" style="
            width: 300px;
            border-radius: 10px 10px 10px 10px;
            margin-left: 50px;
          ">
              <source src="<?=$editBlog['video'];?>" id="video_here">
            </video>
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              Close
            </button>
            <button id="add" type="submit" class="btn btn-primary">
              Send
            </button>
          </div>
        </form>`)
        imag();

        $(document).on("change", ".file_multi_video", function (evt) {
          var $source = $('#video_here');
          $('.video_show').show();
          $source[0].src = URL.createObjectURL(this.files[0]);
          $source.parent()[0].load();
        });

        $("#add").click((e) => {
              e.preventDefault();
              var datas= new FormData();
              alert('go')
              console.log($("#type option:selected").text())
              console.log($("#inputFileToLoad2")[0].files[0])
              console.log($("#body_parts option:selected").text())
              console.log($("#inputFileToLoad")[0].files[0])
              alert('hi')
              datas.append("myFiles", $("#inputFileToLoad2")[0].files[0]);
              datas.append("picture", $("#inputFileToLoad")[0].files[0]);
              datas.append("title", $("#article-title").val());
              datas.append("type", $("#type option:selected").text());
              datas.append("body_parts", $("#body_parts option:selected").text());
              $(".modal-content").html(""); 

              postdatas(
                "http://localhost:8082/admin/api/addApiVideo",
                datas,
                (result) => {
                  if (result == true) {
                    videoshow();
                    alert("新增成功");
                  }else{
                    videoshow();
                    alert("新增失敗")
                  }
                }
              );
        })
    }

///////影片管理//////////////////




//訂單管理
    //將json陣列轉為 table
    const jsonToHTMLbyOrder = (querySelector, json) => {
      if (json.length == 0) {
        document.querySelector(querySelector).innerHTML = "";
        return;
      }
      //define table head
      let title = `<thead><tr>${Object.keys(json[0])
        .map((el, index) => {
          if (index <= 9 && index >= 0) {
            return `<th><strong>${el}</strong></th>`;
          }
        })
        .join("")}<th>操作</th></tr></thead>`;
      // define table body
      let trs = json.map(
        (el) =>
          `${Object.values(el)
            .map((td, index) => {
              if (index == 4) {
                if (td == 1) {
                  return `<td class="data">宅配</td>`;
                } else if (td == 0) {
                  return `<td class="data">超商自取</td>`;
                }
              } else if (index == 5) {
                if (td == 2) {
                  return `<td class="data">信用卡支付</td>`;
                } else if (td == 1) {
                  return `<td id="${el.order_id}" name="cash" class="data">現金支付</td>`;
                }
              } else if (index == 7) {
                if (td == 1) {
                  return `<td class="data"><span id="${el.order_id}" name="paytrue" style="color:green">已付款</span</td>`;
                } else if (td == null) {
                  return `<td class="data"><span style="color:red">尚未付款</span</td>`;
                }
              } else if (index == 10) {
                return `<td class="data"><button id="${el.order_id}" data-bs-toggle="modal"
              data-bs-target="#exampleModal"
              data-bs-whatever="@getbootstrap" class="btn btn-primary">顯示明細</button><button name="payment" id="${el.order_id}" class="btn btn-danger">
              尚未支付</button></td>`;
              } else {
                return `<td class="data">${td}</td>`;
              }
            })
            .join("")}`
      );
      let tbody = `<tbody>${trs
        .map((el) => `<tr>${el}</tr>`)
        .join("")}</tbody>`;
      let table = `<table class="table table-hover">${title}${tbody}</table>`;
      document.querySelector(querySelector).innerHTML = table;
    };

    //按鈕觸發呈現table
    const ordershow = () => {
      document.querySelector("#table").style.opacity = 0;
      setTimeout(() => {
        getdata(showAllurl, (result) => {
          //make a copy of data
          [...initialTableData] = [...result];
          jsonToHTMLbyOrder("#table", result);
          document.querySelector("#table").style.opacity = 1;

          $("td[name='cash']").each((index, ele) => {
            var id = $(ele).attr("id")
            $(`button[id="${id}"][name="payment"]`).attr("hidden",true);
          })

          $("span[name='paytrue']").each((index, ele) => {
            var id = $(ele).attr("id")
              $(`button[id="${id}"][name="payment"]`).attr("hidden",true);
          })

          $(".modal-content").html(`<div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">訂單明細</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <form>
            <div class="mb-3">
              <table class="table">
                <thead>
                  <tr>
                    <th scope="col">商品名稱</th>
                    <th scope="col">商品數量</th>
                    <th scope="col">商品金額</th>
                  </tr>
                </thead>
                <tbody id="tb2"></tbody>
              </table>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            關閉
          </button>
        </div>`)

          $(".btn-primary").click((e) => {
            getdata(
              `http://localhost:8082/public/findByFKOrderId/${$(e.target).attr(
                "id"
              )}`,
              (result) => {
                var string;
                console.log(result);
                for (let i of result) {
                  string += "<tr><td>" + i.product_name + "</td>";
                  string += "<td>" + i.count + "</td>";
                  string += "<td>" + i.total + "</td></tr>";
                }
                $("#tb2").html(string);
              }
            );
          });

          $("button[name='payment']").click((e) => {
            alert('正在發送email請稍候');
            getdatas("http://localhost:8082/admin/api/orderEmailSend/" + $(e.target).attr("id"), (result) => {
              if(result == "success"){
                alert("email發送成功");
              }else{
                alert("發送失敗");
              }
            })
          })
        });
      }, 300);
    }

    

