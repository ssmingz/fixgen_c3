class PlaceHold {
  boolean hasDefaultButton() {
    NSArray windows = application.windows();
    int count = windows.count();
    for (int i = 0; i < count; i++) {
      NSWindow window = new NSWindow(windows.objectAtIndex(i));
      if (window.defaultButtonCell() != null) {
        return true;
      }
    }
    return false;
  }
}
