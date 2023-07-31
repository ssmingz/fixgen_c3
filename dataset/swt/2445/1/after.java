class PlaceHold {
  void destroyDisplay() {
    Runtime.getRuntime()
        .addShutdownHook(
            new Thread() {
              public void run() {
                NSApplication.sharedApplication().terminate(null);
              }
            });
    application = null;
  }
}
