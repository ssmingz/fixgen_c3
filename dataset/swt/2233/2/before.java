class PlaceHold {
  void windowDidResize(long id, long sel, long notification) {
    if (((window.collectionBehavior() & OS.NSWindowCollectionBehaviorFullScreenPrimary) == 0)
        && fullScreen) {
      window.setFrame(fullScreenFrame, true);
      NSRect contentViewFrame = new NSRect();
      contentViewFrame.width = fullScreenFrame.width;
      contentViewFrame.height = fullScreenFrame.height;
      window.contentView().setFrame(contentViewFrame);
    }
    if (fixResize()) {
      NSRect rect = window.frame();
      rect.x = rect.y = 0;
      double scaleFactor = window.userSpaceScaleFactor();
      rect.width /= scaleFactor;
      rect.height /= scaleFactor;
      window.contentView().setFrame(rect);
    }
    resized = true;
    sendEvent(Resize);
    if (isDisposed()) {
      return;
    }
    if (layout != null) {
      markLayout(false, false);
      updateLayout(false);
    }
  }
}
