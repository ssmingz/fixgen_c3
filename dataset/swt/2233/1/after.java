class PlaceHold {
  @Override
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
      window.contentView().setFrame(rect);
    }
  }
}
