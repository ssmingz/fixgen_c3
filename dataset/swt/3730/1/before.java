class PlaceHold {
  void windowDidResignKey(int id, int sel, int notification) {
    super.windowDidResignKey(id, sel, notification);
    sendEvent(Deactivate);
    if (isDisposed()) {
      return;
    }
    Control control = display.focusControl;
    if (((control != null) && (!control.isDisposed())) && (control.getShell() == this)) {
      display.focusControl = null;
      control.sendFocusEvent(FocusOut, false);
    }
  }
}
