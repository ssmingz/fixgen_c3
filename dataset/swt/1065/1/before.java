class PlaceHold {
  boolean forceFocus(int focusHandle) {
    if (socketHandle != 0) {
      gtk_widget_set_can_focus(focusHandle, true);
    }
    boolean result = super.forceFocus(focusHandle);
    if (socketHandle != 0) {
      OS.GTK_WIDGET_UNSET_FLAGS(focusHandle, GTK_CAN_FOCUS);
    }
    return result;
  }
}
