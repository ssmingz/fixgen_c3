class PlaceHold {
  int getOperationFromKeyState() {
    NSEvent currEvent = NSApplication.sharedApplication().currentEvent();
    long modifiers = currEvent.modifierFlags();
    boolean option = (modifiers & OS.NSAlternateKeyMask) == OS.NSAlternateKeyMask;
    boolean control = (modifiers & OS.NSControlKeyMask) == OS.NSControlKeyMask;
    if (control && option) {
      return DND.DROP_DEFAULT;
    }
    if (control) {
      return DND.DROP_LINK;
    }
    if (option) {
      return DND.DROP_COPY;
    }
    return DND.DROP_DEFAULT;
  }
}
