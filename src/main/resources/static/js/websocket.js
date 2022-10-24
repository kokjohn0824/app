//shared

var shared = new SharedWorker("/js/worker.js", "chat");
shared.port.start(); // this will trigger the on connect event on the webworker
// this will also start the worker IF this is the first call!

// recieve message from worker
shared.port.addEventListener("message", (message) => {
  let container = $("#information")[0];
  console.log(`container: ${container.scrollTop}`);
  $("#information").append(`<tr><td>${message.data}</tr></td>`);

  //if message append, scrolls
  container.scrollTop = container.scrollHeight;
});

// 设置 STOMP 客户端
let stompClient = null;
// 设置 WebSocket 进入端点
const SOCKET_ENDPOINT = "/chat";
// 设置订阅消息的请求前缀
const SUBSCRIBE_PREFIX = "/topic";

// 设置订阅消息的请求地址
let SUBSCRIBE = "";
// 设置服务器端点，访问服务器中哪个接口
let SEND_ENDPOINT = "/socket/test";

/* 进行连接 */
const connect = function () {
  // 设置 SOCKET
  let socket = new SockJS(SOCKET_ENDPOINT);
  // 配置 STOMP 客户端
  stompClient = Stomp.over(socket);
  // STOMP 客户端连接
  stompClient.connect({}, function (frame) {
    //連接後要做的事情
    subscribeSocket();
  });
};

/* 订阅信息 */
function subscribeSocket() {
  console.info(stompClient);
  // 设置订阅地址
  SUBSCRIBE = SUBSCRIBE_PREFIX + `/qa/${$("#subscribe").val()}`;
  // 输出订阅地址
  console.info("設定訂閱地址為：" + SUBSCRIBE);
  // 执行订阅消息
  stompClient.subscribe(SUBSCRIBE, showRecieveMessage);
}

/* 断开连接 */
function disconnect() {
  stompClient.disconnect(function () {
    console.warn("disconnected!");
  });
}

//指定目標位置傳送訊息
function handleClick() {
  // 抓取發送內容
  let sendContent = $("#content").val();

  // 設定要傳送的內容
  let message = `{ "destination": "${SUBSCRIBE}", "sender": "testman", "content" : "${sendContent}"}`;
  // 发送消息
  stompClient.send(SEND_ENDPOINT, {}, message);
  //清空抓取欄位的內容
  $("#content").val("");
}

const showRecieveMessage = function (responseBody) {
  let receiveMessage = JSON.parse(responseBody.body);
  console.log(receiveMessage);
  let content = receiveMessage.content.replace("<script>", "");
  // send a mesasge to the worker
  shared.port.postMessage([content]);
};

//設定自動連線
window.onload = () => {
  connect();
};
