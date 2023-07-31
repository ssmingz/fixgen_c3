class PlaceHold {
  public Object getData(String key) {
    checkDevice();
    if (key == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (key.equals(RUN_MESSAGES_IN_IDLE_KEY)) {
      return new Boolean(runMessagesInIdle);
    }
    if (key.equals(RUN_MESSAGES_IN_MESSAGE_PROC_KEY)) {
      return new Boolean(runMessagesInMessageProc);
    }
    if (key.equals(USE_OWNDC_KEY)) {
      return new Boolean(useOwnDC);
    }
    if (keys == null) {
      return null;
    }
    for (int i = 0; i < keys.length; i++) {
      if (keys[i].equals(key)) {
        return values[i];
      }
    }
    return null;
  }
}
