class PlaceHold {
  public void setData(String key, Object value) {
    checkDevice();
    if (key == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (key.equals(RUN_MESSAGES_IN_IDLE_KEY)) {
      Boolean data = ((Boolean) (value));
      runMessagesInIdle = (data != null) && data.booleanValue();
      return;
    }
    if (key.equals(RUN_MESSAGES_IN_MESSAGE_PROC_KEY)) {
      Boolean data = ((Boolean) (value));
      runMessagesInMessageProc = (data != null) && data.booleanValue();
      return;
    }
    if (key.equals(USE_OWNDC_KEY)) {
      Boolean data = ((Boolean) (value));
      useOwnDC = (data != null) && data.booleanValue();
      return;
    }
    if (key.equals(ACCEL_KEY_HIT)) {
      Boolean data = ((Boolean) (value));
      accelKeyHit = (data != null) && data.booleanValue();
      return;
    }
    if (value == null) {
      if (keys == null) {
        return;
      }
      int index = 0;
      while ((index < keys.length) && (!keys[index].equals(key))) {
        index++;
      }
      if (index == keys.length) {
        return;
      }
      if (keys.length == 1) {
        keys = null;
        values = null;
      } else {
        String[] newKeys = new String[keys.length - 1];
        Object[] newValues = new Object[values.length - 1];
        System.arraycopy(keys, 0, newKeys, 0, index);
        System.arraycopy(keys, index + 1, newKeys, index, newKeys.length - index);
        System.arraycopy(values, 0, newValues, 0, index);
        System.arraycopy(values, index + 1, newValues, index, newValues.length - index);
        keys = newKeys;
        values = newValues;
      }
      return;
    }
    if (keys == null) {
      keys = new String[] {key};
      values = new Object[] {value};
      return;
    }
    for (int i = 0; i < keys.length; i++) {
      if (keys[i].equals(key)) {
        values[i] = value;
        return;
      }
    }
    String[] newKeys = new String[keys.length + 1];
    Object[] newValues = new Object[values.length + 1];
    System.arraycopy(keys, 0, newKeys, 0, keys.length);
    System.arraycopy(values, 0, newValues, 0, values.length);
    newKeys[keys.length] = key;
    newValues[values.length] = value;
    keys = newKeys;
    values = newValues;
  }
}
