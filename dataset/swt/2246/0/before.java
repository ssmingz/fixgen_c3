class Accessible {
  Accessible(Control control) {
    this.control = control;
    axuielementref = OS.AXUIElementCreateWithHIObjectAndIdentifier(control.handle, 0);
  }
}
