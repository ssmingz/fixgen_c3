class PlaceHold {
  public boolean forceFocus() {
    checkWidget();
    Decorations shell = menuShell();
    shell.setSavedFocus(this);
    if ((!isEnabled()) || (!isVisible())) {
      return false;
    }
    if (isFocusControl()) {
      return true;
    }
    shell.bringToTop(false);
    int[] features = new int[1];
    OS.GetControlFeatures(handle, features);
    if ((features[0] & OS.kControlSupportsFocus) == 0) {
      return false;
    }
    int window = OS.GetControlOwner(handle);
    return OS.SetKeyboardFocus(window, handle, ((short) (kControlFocusNextPart))) == OS.noErr;
  }
}
