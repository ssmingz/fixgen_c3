class PlaceHold {
  boolean forceFocus(int focusHandle) {
    if (socketHandle != 0) {
      gtk_widget_set_can_focus(focusHandle, true);
    }
    boolean result = super.forceFocus(focusHandle);
    if (socketHandle != 0) {
      gtk_widget_set_can_focus(focusHandle, false);
    }
    return result;
  }
}
