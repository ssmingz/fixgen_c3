class PlaceHold {
  Control getFocusControl(int window) {
    if (focusControl != null) {
      return focusControl;
    }
    int[] theControl = new int[1];
    OS.GetKeyboardFocus(window, theControl);
    if (theControl[0] == 0) {
      return null;
    }
    do {
      Widget widget = getWidget(theControl[0]);
      if ((widget != null) && (widget instanceof Control)) {
        Control control = ((Control) (widget));
        return control.isEnabled() ? control : null;
      }
      OS.GetSuperControl(theControl[0], theControl);
    } while (theControl[0] != 0);
    return null;
  }
}
