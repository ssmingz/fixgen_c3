class PlaceHold {
  void windowDidBecomeKey(int id, int sel, int notification) {
    super.windowDidBecomeKey(id, sel, notification);
    Display display = this.display;
    display.setMenuBar(menuBar);
    sendEvent(Activate);
    Control control = Display.GetFocusControl(window);
    if (((control != null) && (!control.isDisposed())) && (control != display.focusControl)) {
      display.focusControl = control;
      control.sendFocusEvent(FocusIn, true);
    }
  }
}
