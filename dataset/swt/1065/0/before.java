class PlaceHold {
  boolean forceFocus(int focusHandle) {
    if (socketHandle != 0) {
      OS.GTK_WIDGET_SET_FLAGS(focusHandle, GTK_CAN_FOCUS);
    }
    boolean result = super.forceFocus(focusHandle);
    if (socketHandle != 0) {
      OS.GTK_WIDGET_UNSET_FLAGS(focusHandle, GTK_CAN_FOCUS);
    }
    return result;
  }
}
