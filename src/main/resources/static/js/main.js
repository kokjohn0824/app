var shared = new SharedWorker("/js/worker.js", "chat");
shared.port.start(); // this will trigger the on connect event on the webworker
// this will also start the worker IF this is the first call!

// recieve message from worker
shared.port.addEventListener("message", (message) => {
  console.log(message);
});

// send a mesasge to the worker
document.querySelector("#sharedbtn").addEventListener("click", () => {
  console.log("fire");
  shared.port.postMessage(["I have a nice message for all"]);
});
