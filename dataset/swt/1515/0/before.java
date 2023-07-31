class PlaceHold {
  void setBounds(int x, int y, int width, int height, boolean move, boolean resize) {
    if (window == null) {
      if (move) {
        return;
      }
      if (resize) {
        NSSize frameSize = new NSSize();
        frameSize.width = width;
        frameSize.height = height;
        view.setFrameSize(frameSize);
        return;
      }
    }
    if (_getFullScreen()) {
      setFullScreen(false);
    }
    boolean sheet = window.isSheet();
    if ((sheet && move) && (!resize)) {
      return;
    }
    int screenHeight = ((int) (display.getPrimaryFrame().height));
    float scaleFactor = window.userSpaceScaleFactor();
    x *= scaleFactor;
    y *= scaleFactor;
    width *= scaleFactor;
    height *= scaleFactor;
    NSRect frame = window.frame();
    if (!move) {
      x = ((int) (frame.x));
      y = screenHeight - ((int) (frame.y + frame.height));
    }
    if (resize) {
      NSSize minSize = window.minSize();
      width = Math.max(width, ((int) (minSize.width)));
      height = Math.max(height, ((int) (minSize.height)));
    } else {
      width = ((int) (frame.width));
      height = ((int) (frame.height));
    }
    if (sheet) {
      y = screenHeight - ((int) (frame.y + frame.height));
      NSRect parentRect = parent.getShell().window.frame();
      frame.width = width;
      frame.height = height;
      frame.x = parentRect.x + ((parentRect.width - frame.width) / 2);
      frame.y = screenHeight - ((int) (y + frame.height));
      window.setFrame(frame, isVisible(), true);
    } else {
      frame.x = x;
      frame.y = screenHeight - ((int) (y + height));
      frame.width = width;
      frame.height = height;
      window.setFrame(frame, isVisible());
    }
  }
}
