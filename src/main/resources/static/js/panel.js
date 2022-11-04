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

      ima;
      let editorcontent;
      const textareacontainer = document.getElementById("textareacontainer");

      //修改文章搜尋
      $("button[name='editarticle']").click((e) => {
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
      });
      //修改文章
      $("#editar").click((e) => {
        e.preventDefault();
        var datas = new FormData();
        datas.append("article_id", $("#article_id").val());
        datas.append("title", $("#article-title").val());
        datas.append("text", editorcontent.getData());
        console.log($("#article_id").val());
        datas.append("type", $("#type option:selected").val());
        datas.append("file", $("#inputFileToLoad")[0].files[0]);
        postdatas(
          "http://localhost:8082/admin/api/article/edit",
          datas,
          (result) => {
            if (result == true) {
              article();
              alert("更新成功");
            }
          }
        );
      });

      //刪除文章
      $(".btn-icon-delete").click((e) => {
        if (confirm("確定刪除嗎?") == true) {
          getdata(
            "http://localhost:8082/admin/api/article/delete?article_id=" +
              $(e.target).attr("id"),
            (result) => {
              if (result == true) {
                article();
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

//按鈕觸發呈現table
showtable.addEventListener("click", (e) => {
  console.log("in");
  console.log(showtable.dataset.tabletype);
  switch (showtable.dataset.tabletype) {
    case "article":
      console.log("in");
      articleshow();
  }
});

///////文章管理//////////////////

///////商品管理//////////////////
//按鈕觸發呈現table 商品管理部分
//將json陣列轉為 table 商品管理部分
// const jsonToHTMLbyQ = (querySelector, json) => {
//     if (json.length == 0) {
//       document.querySelector(querySelector).innerHTML = "";
//       return;
//     }
//     //define table head
//     let title = `<thead><tr>${Object.keys(json[0])
//       .map((el) => `<th><strong>${el}</strong></th>`)
//       .join("")}<th>商品圖</th><th>操作</th></tr></thead>`;
//     // define table body
//     let trs = json.map(
//       (el) =>
//         `${Object.values(el)
//           .map((td, index) => {
//             if (index == 4) {
//               if (td == 1) {
//                 return `<td class="data"><span id="${td}" name="${el.product_id}" style='color:green'>販售中</span></td>`;
//               } else {
//                 return `<td class="data"><span id="${td}" name="${el.product_id}" style='color:red'>下架中</span></td>`;
//               }
//             } else if (index == 7) {
//               return `<td><class="date">${td}</td><td><img style="width:50px; height:50px" src="/admin/downloadImage/${el.product_id}"></td>`;
//             } else {
//               return `<td class="data">${td}</td>`;
//             }
//           })
//           .join("")}<td><button id="${el.product_id}"
//             name="checkboxone"
//             type="button"
//             data-bs-toggle="modal"
//             data-bs-target="#exampleModal"
//             data-bs-whatever="@getbootstrap" class="save btn-icon-edit"></button>
//           <button id="${el.product_id}"
//             name="deletebox" class="delete btn-icon-delete"></button><button name="onsale" id="${
//               el.product_id
//             }" class="btn btn-primary">上下架</button></td>`
//     );
//     let tbody = `<tbody>${trs
//       .map((el) => `<tr>${el}</tr>`)
//       .join("")}</tbody>`;
//     let table = `<table class="table table-hover">${title}${tbody}</table>`;
//     document.querySelector(querySelector).innerHTML = table;
//   };
//   document.getElementById("showtable").addEventListener(
//     "click",
//     (product = () => {
//       document.querySelector("#table").style.opacity = 0;
//       setTimeout(() => {
//         getdata(showAllurl, (result) => {
//           //make a copy of data
//           [...initialTableData] = [...result];
//           jsonToHTMLbyQ("#table", result);
//           document.querySelector("#table").style.opacity = 1;
//           //商品圖片預覽
//           var inputFileToLoad = $("#inputFileToLoad");
//           inputFileToLoad.change(function () {
//             readURL(this);
//           });
//           function readURL(input) {
//             if (input.files && input.files[0]) {
//               var reader = new FileReader();
//               reader.onload = function (e) {
//                 $("#preview_img").attr("src", e.target.result);
//               };
//               reader.readAsDataURL(input.files[0]);
//             }
//           }
//           //商品上下架
//           $("button[name='onsale']").click(
//             (updateOnsale = (e) => {
//               var myHeaders = new Headers();
//               myHeaders.append("Content-Type", "application/json");
//               var id = $(e.target).attr("id");
//               console.log(id);
//               var onsale = $(`span[name="${id}"]`).attr("id");
//               console.log(onsale);
//               var requestOptions = {
//                 method: "POST",
//                 headers: myHeaders,
//                 redirect: "follow",
//               };

//               fetch(
//                 `http://localhost:8082/admin/api/changeOnsale/${id}/${onsale}`,
//                 requestOptions
//               )
//                 .then((response) => response.json())
//                 .then((result) => {
//                   console.log(result);
//                   if (result.onsale == 1) {
//                     $(`span[name="${$(e.target).attr("id")}"]`)
//                       .attr("id", result.onsale)
//                       .attr("style", "color:green")
//                       .text("販售中");
//                   } else {
//                     $(`span[name="${$(e.target).attr("id")}"]`)
//                       .attr("id", result.onsale)
//                       .attr("style", "color:red")
//                       .text("下架中");
//                   }
//                 })
//                 .catch((error) => console.log("error", error));
//             })
//           );

//           $("button[name='checkboxone']").click(
//             (updateQuery = (e) => {
//               console.log("好痛苦");
//               var product_id = $(e.target).attr("id");
//               var requestOptions = {
//                 method: "GET",
//                 redirect: "follow",
//               };
//               fetch(
//                 "http://localhost:8082/admin/updateProduct?product_id=" +
//                   product_id,
//                 requestOptions
//               )
//                 .then((response) => response.json())
//                 .then((result) => {
//                   console.log(result.product_id);
//                   $("#recipient-title").attr("value", result.title);
//                   $("#recipient-price").attr("value", result.price);
//                   $("#recipient-stock").attr("value", result.stock);
//                   $("#recipient-text").text(result.text);
//                   $("#onsale").attr("value", result.onsale);
//                   $("#product_id").attr("value", result.product_id);
//                   $("#preview_img").attr(
//                     "src",
//                     "/admin/downloadImage/" + result.product_id
//                   );
//                 })
//                 .catch((error) => console.log("error", error));
//             })
//           );

//           $("#send").click(
//             (update = (e) => {
//               e.preventDefault();
//               var myHeaders = new Headers();
//               var datas = new FormData();
//           myHeaders.append("Content-Type", "multipart/form-data");
//               datas.append("product_id", $("#product_id").val());
//               datas.append("title", $("#recipient-title").val());
//               datas.append("price", $("#recipient-price").val());
//               datas.append("stock", $("#recipient-stock").val());
//               datas.append("text", $("#recipient-text").val());
//               datas.append("type", $("#type option:selected").text());
//               datas.append("onsale", $("input[name='onsale']").val());
//               datas.append("file", $("#inputFileToLoad")[0].files[0]);

//               var requestOptions = {
//                 method: "POST",
//                 headers: myHeaders,
//                 body: datas,
//                 redirect: "follow",
//               };

//               fetch(
//                 "http://localhost:8082/admin/api/updateProduct",
//                 requestOptions
//               )
//                 .then((response) => response.json())
//                 .then((result) => {
//                   // console.log(result);
//                   if (result == true) {
//                     alert("更新成功");
//                     product();
//                   } else {
//                     alert("更新失敗");
//                     product();
//                   }
//                 })
//                 .catch((error) => console.log("error", error));
//             })
//           );

//           $("button[name='deletebox']").click(
//             (deleteproduct = (e) => {
//               if (confirm("確定刪除嗎?") == true) {
//                 var myHeaders = new Headers();
//                 myHeaders.append("Content-Type", "application/json");

//                 var requestOptions = {
//                   method: "GET",
//                   headers: myHeaders,
//                   redirect: "follow",
//                 };

//                 fetch(
//                   "http://localhost:8082/public/api/deleteProduct?product_id=" +
//                     $(e.target).attr("id"),
//                   requestOptions
//                 )
//                   .then((response) => response.json())
//                   .then((result) => {
//                     if (result) {
//                       product();
//                     } else {
//                       alert("訂單中有此商品故不可刪除");
//                       product();
//                     }
//                   })
//                   .catch((error) => console.log("error", error));
//               }
//             })
//           );
//         });
//       }, 300);
//     })
//   );
///////商品管理//////////////////

///////會員管理//////////////////
//將json陣列轉為 table
// const jsonToHTMLbyQ = (querySelector, json) => {
//     if (json.length == 0) {
//       document.querySelector(querySelector).innerHTML = "";
//       return;
//     }
//     //define table head
//     let title = `<thead><tr>${Object.keys(json[0])
//       .map((el) => `<th><strong>${el}</strong></th>`)
//       .join("")}<th>操作</th></tr></thead>`;
//     // define table body
//     let trs = json.map(
//       (el) =>
//         `${Object.values(el)
//           .map((td, index) => {
//             if (index == 4) {
//               if (td == false) {
//                 return `<td class="data"><input id="${el.users_id}" name="locked" value="${td}" hidden /><span id="${el.users_id}" name="locked" style="color:green">合法帳戶</span></td>`;
//               } else {
//                 return `<td class="data"><input id="${el.users_id}" name="locked" value="${td}" hidden /><span id="${el.users_id}" name="locked" style="color:red">停權帳戶</span></td>`;
//               }
//             } else {
//               return `<td class="data">${td}</td>`;
//             }
//           })
//           .join("")}<td><button id="${el.users_id}" name="editMember"
//             type="button"
//             data-bs-toggle="modal"
//             data-bs-target="#exampleModal"
//             data-bs-whatever="@getbootstrap" class="save btn-icon-edit"></button>
//           </td><td><button id="${
//             el.users_id
//           }" name="locked" class="btn btn-success">復權</button></td>`
//     );
//     let tbody = `<tbody>${trs
//       .map((el) => `<tr>${el}</tr>`)
//       .join("")}</tbody>`;
//     let table = `<table class="table table-hover">${title}${tbody}</table>`;
//     document.querySelector(querySelector).innerHTML = table;
//   };

//   //按鈕觸發呈現table
//   document.getElementById("showtable").addEventListener(
//     "click",
//     (user = () => {
//       document.querySelector("#table").style.opacity = 0;
//       setTimeout(() => {
//         getdata(showAllurl, (result) => {
//           //make a copy of data
//           [...initialTableData] = [...result];
//           jsonToHTMLbyQ("#table", result);
//           $(`button[id="${$("#userID").val()}"]`).hide();
//           $("input[name='locked']").each((index, ele) => {
//             var id = $(ele).attr("id");
//             if ($(ele).val() == "false") {
//               $(`button[id="${id}"][name="locked"]`)
//                 .attr("class", "btn btn-danger")
//                 .text("停權");
//             } else if ($(ele).val() == "true") {
//               $(`button[id="${id}"][name="locked"]`)
//                 .attr("class", "btn btn-success")
//                 .text("復權");
//             }
//           });

//           $("button[name='locked']").click((e) => {
//             var locked;
//             var users_id = $(e.target).attr("id");
//             var input = $(`input[id='${users_id}']`).val();
//             if (input == "false") {
//               locked = false;
//             } else if (input == "true") {
//               locked = true;
//             }
//             var jsn = { users_id: users_id, locked: locked };
//             postdata(
//               "http://localhost:8082/admin/api/updateLocked/",
//               jsn,
//               (result) => {
//                 console.log(result);
//                 if (result == true) {
//                   $(e.target).attr("class", "btn btn-success").text("復權");
//                   $(`input[id='${users_id}']`).attr("value", "true");
//                   $(`span[id="${users_id}"]`)
//                     .attr("style", "color:red")
//                     .text("停權帳戶");
//                 } else {
//                   $(e.target).attr("class", "btn btn-danger").text("停權");
//                   $(`input[id='${users_id}']`).attr("value", "false");
//                   $(`span[id="${users_id}"]`)
//                     .attr("style", "color:green")
//                     .text("合法帳戶");
//                 }
//               }
//             );
//           });

//           $("button[name='editMember']").click((e) => {
//             ima;
//             getdata(
//               `http://localhost:8082/admin/api/queryUpdateMember/${$(
//                 e.target
//               ).attr("id")}`,
//               (result) => {
//                 $("#recipient-nickname").val(result.nickname);
//                 if (result.gender == 1) {
//                   $("#recipient-man").attr("checked", true);
//                 } else {
//                   $("#recipient-female").attr("checked", true);
//                 }
//                 $("#recipient-age").val(result.age);
//                 $("#recipient-height").val(result.height);
//                 $("#recipient-weight").val(result.weight);
//                 $("#recipient-bodyFat").val(result.bodyFat);
//                 $("#recipient-visceralFat").val(result.visceralFat);
//                 $("#recipient-muscleMass").val(result.muscleMass);
//                 if (result.becomeVIP == 1) {
//                   $("#recipient-on").attr("checked", true);
//                 } else {
//                   $("#recipient-off").attr("checked", true);
//                 }
//                 $("#preview_img").attr(
//                   "src",
//                   `/public/showMemberImage/${result.member_id}`
//                 );
//                 $("#member_id").val(result.member_id);
//               }
//             );
//           });

//           $("#editmember").click((e) => {
//             e.preventDefault();
//             var datas = new FormData();
//             datas.append(
//               "becomeVIP",
//               $("input[name='becomeVIP']:checked").val() == 1 ? 1 : 0
//             );
//             datas.append("member_id", $("#member_id").val());
//             postdatas(
//               `http://localhost:8082/admin/api/updateVipById/`,
//               datas,
//               (result) => {
//                 if (result == true) {
//                   user();
//                 }
//               }
//             );
//           });
//         });
//         document.querySelector("#table").style.opacity = 1;
//       }),
//         300;
//     })
//   );
///////會員管理//////////////////
