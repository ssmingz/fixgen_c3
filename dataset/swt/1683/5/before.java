class PlaceHold {
  public Shell getActiveShell() {
    checkDevice();
    NSArray windows = application.windows();
    int count = windows.count();
    for (int i = 0; i < count; i++) {
      NSWindow win = new NSWindow(windows.objectAtIndex(i));
      if (win.isKeyWindow()) {
        Widget widget = getWidget(win.contentView());
        if (widget instanceof Shell) {
          return ((Shell) (widget));
        }
      }
    }
    return null;
  }
}
