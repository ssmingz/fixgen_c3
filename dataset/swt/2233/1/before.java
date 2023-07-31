class PlaceHold {
  void setZOrder() {
    if (scrollView != null) {
      scrollView.setDocumentView(view);
    }
    if (window == null) {
      return;
    }
    window.setContentView(scrollView != null ? scrollView : view);
    if (fixResize()) {
      NSRect rect = window.frame();
      rect.x = rect.y = 0;
      double scaleFactor = window.userSpaceScaleFactor();
      rect.width /= scaleFactor;
      rect.height /= scaleFactor;
      window.contentView().setFrame(rect);
    }
  }
}
