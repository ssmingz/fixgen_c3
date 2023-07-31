class PlaceHold {
  public boolean sleep() {
    checkDevice();
    if (getMessageCount() != 0) {
      return true;
    }
    try {
      addPool();
      allowTimers = runAsyncMessages = false;
      NSRunLoop.currentRunLoop().runMode(NSDefaultRunLoopMode, NSDate.distantFuture());
      allowTimers = runAsyncMessages = true;
    } finally {
      removePool();
    }
    return true;
  }
}
