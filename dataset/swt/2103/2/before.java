class PlaceHold {
  public boolean sleep() {
    checkDevice();
    allowTimers = false;
    boolean result = OS.ReceiveNextEvent(0, null, kEventDurationForever, false, null) == OS.noErr;
    allowTimers = true;
    return result;
  }
}
