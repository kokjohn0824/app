const allPorts = [];

onconnect = function (e) {
  // the incoming port
  var port = e.ports[0];
  allPorts.push(port);

  port.addEventListener("message", function (e) {
    // get the message sent to the worker
    var message = e.data[0];
    // send the message to ALL connected worker ports!
    allPorts.forEach((port) => {
      port.postMessage(message);
    });
  });

  port.start(); // Required when using addEventListener. Otherwise called implicitly by onmessage setter.
};
