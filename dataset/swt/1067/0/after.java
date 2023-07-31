class PlaceHold {
  public int internal_new_GC(GCData data) {
    if (isDisposed()) {
      SWT.error(ERROR_DEVICE_DISPOSED);
    }
    if (screenWindow == null) {
      NSWindow window = ((NSWindow) (new NSWindow().alloc()));
      NSRect rect = new NSRect();
      window =
          window.initWithContentRect(rect, NSBorderlessWindowMask, NSBackingStoreBuffered, false);
      window.setReleasedWhenClosed(false);
      screenWindow = window;
    }
    NSGraphicsContext context = screenWindow.graphicsContext();
    if (data != null) {
      int mask = SWT.LEFT_TO_RIGHT | SWT.RIGHT_TO_LEFT;
      if ((data.style & mask) == 0) {
        data.style |= SWT.LEFT_TO_RIGHT;
      }
      data.device = this;
      data.background = getSystemColor(COLOR_WHITE).handle;
      data.foreground = getSystemColor(COLOR_BLACK).handle;
      data.font = getSystemFont();
    }
    return context.id;
  }
}
