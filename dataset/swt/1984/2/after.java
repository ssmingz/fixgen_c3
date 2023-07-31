class PlaceHold {
  public boolean sleep() {
    checkDevice();
    if (getMessageCount() != 0) {
      return true;
    }
    sendSleepEvent();
    try {
      addPool();
      allowTimers = runAsyncMessages = false;
      NSRunLoop.currentRunLoop().runMode(NSDefaultRunLoopMode, NSDate.distantFuture());
      allowTimers = runAsyncMessages = true;
    } finally {
      removePool();
    }
    sendWakeupEvent();
    return true;
  }
}
