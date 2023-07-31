class PlaceHold {
  public Object getData(String key) {
    checkDevice();
    if (key == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (key.equals(DISPATCH_EVENT_KEY)) {
      return dispatchEvents;
    }
    if (key.equals(GET_MODAL_DIALOG)) {
      return modalDialog;
    }
    if (key.equals(GET_DIRECTION_PROC_KEY)) {
      return new LONG(setDirectionProc);
    }
    if (key.equals(GET_EMISSION_PROC_KEY)) {
      return new LONG(emissionProc);
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
