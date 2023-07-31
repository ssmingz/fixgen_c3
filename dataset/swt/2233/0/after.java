class PlaceHold {
  @Override
  public Rectangle getClientArea() {
    checkWidget();
    NSRect rect;
    if (window != null) {
      if (!fixResize()) {
        rect = window.contentView().frame();
      } else {
        rect = window.frame();
      }
    } else {
      rect = topView().frame();
    }
    int width = ((int) (rect.width));
    int height = ((int) (rect.height));
    if (scrollView != null) {
      NSSize size = new NSSize();
      size.width = width;
      size.height = height;
      size =
          NSScrollView.contentSizeForFrameSize(
              size, (style & SWT.H_SCROLL) != 0, (style & SWT.V_SCROLL) != 0, NSNoBorder);
      width = ((int) (size.width));
      height = ((int) (size.height));
    }
    return new Rectangle(0, 0, width, height);
  }
}
