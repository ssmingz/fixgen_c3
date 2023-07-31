class PlaceHold {
  public boolean sleep() {
    checkDevice();
    if (getMessageCount() != 0) {
      return false;
    }
    allowTimers = false;
    boolean result = OS.ReceiveNextEvent(0, null, kEventDurationForever, false, null) == OS.noErr;
    allowTimers = true;
    return result;
  }
}
