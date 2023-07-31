class PlaceHold {
  static NSRect convertRect(NSScreen screen, NSRect frame) {
    float scaleFactor = screen.userSpaceScaleFactor();
    frame.x /= scaleFactor;
    frame.y /= scaleFactor;
    frame.width /= scaleFactor;
    frame.height /= scaleFactor;
    return frame;
  }
}
