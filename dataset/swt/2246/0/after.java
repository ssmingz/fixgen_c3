class Accessible {
  Accessible(Control control) {
    this.control = control;
    axuielementref = OS.AXUIElementCreateWithHIObjectAndIdentifier(control.handle, 0);
    OS.HIObjectSetAccessibilityIgnored(control.handle, false);
  }
}
