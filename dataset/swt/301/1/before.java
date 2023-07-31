class PlaceHold {
  void saveFocus() {
    Control control = display.getFocusControl();
    if (((control != null) && (control != this)) && (this == control.menuShell())) {
      setSavedFocus(control);
    }
  }
}
