class PlaceHold {
  public Rectangle getClientArea() {
    checkDevice();
    NSArray screens = NSScreen.screens();
    if (screens.count() != 1) {
      return getBounds(screens);
    }
    NSScreen screen = new NSScreen(screens.objectAtIndex(0));
    NSRect frame = screen.frame();
    NSRect visibleFrame = screen.visibleFrame();
    float y = frame.height - (visibleFrame.y + visibleFrame.height);
    return new Rectangle(
        ((int) (visibleFrame.x)),
        ((int) (y)),
        ((int) (visibleFrame.width)),
        ((int) (visibleFrame.height)));
  }
}
