console.log("hello.js loaded");

//顯示 CKEditor
ClassicEditor
    .create(document.querySelector('#editor'))
    .catch(error=>{
        console.error(error);
    });
