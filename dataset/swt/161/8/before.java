class PlaceHold {
  void checkFocus() {
    Control oldControl = currentFocusControl;
    Control newControl = getFocusControl();
    if (oldControl != newControl) {
      if ((oldControl != null) && (!oldControl.isDisposed())) {
        oldControl.sendFocusEvent(FocusOut);
      }
      currentFocusControl = newControl;
      if ((newControl != null) && (!newControl.isDisposed())) {
        newControl.sendFocusEvent(FocusIn);
      }
    }
  }
}
