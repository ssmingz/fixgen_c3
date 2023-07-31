class PlaceHold {
  void mouseDown(int event) {
    NSEvent nsEvent = new NSEvent(event);
    int mask = nsEvent.modifierFlags() & OS.NSDeviceIndependentModifierFlagsMask;
    if (mask == OS.NSControlKeyMask) {
      showMenu();
    } else {
      highlight = true;
      view.setNeedsDisplay(true);
      int clickCount = nsEvent.clickCount();
      postEvent(clickCount == 2 ? SWT.DefaultSelection : SWT.Selection);
    }
  }
}
