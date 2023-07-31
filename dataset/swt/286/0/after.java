class PlaceHold {
  void createHandle() {
    if ((style & SWT.READ_ONLY) != 0) {
      if ((style & ((SWT.BORDER | SWT.H_SCROLL) | SWT.V_SCROLL)) == 0) {
        state |= THEME_BACKGROUND;
      }
    }
    if ((style & SWT.SINGLE) != 0) {
      NSTextField widget;
      if ((style & SWT.PASSWORD) != 0) {
        widget = ((NSTextField) (new SWTSecureTextField().alloc()));
      } else if ((style & SWT.SEARCH) != 0) {
        widget = ((NSTextField) (new SWTSearchField().alloc()));
      } else {
        widget = ((NSTextField) (new SWTTextField().alloc()));
      }
      widget.init();
      widget.setSelectable(true);
      widget.setEditable((style & SWT.READ_ONLY) == 0);
      if ((style & SWT.BORDER) == 0) {
        widget.setFocusRingType(NSFocusRingTypeNone);
        widget.setBordered(false);
      }
      if ((style & SWT.SEARCH) != 0) {
        NSSearchFieldCell cell = new NSSearchFieldCell(((NSSearchField) (widget)).cell());
        cell.setSearchButtonCell(null);
      }
      int align = OS.NSLeftTextAlignment;
      if ((style & SWT.CENTER) != 0) {
        align = OS.NSCenterTextAlignment;
      }
      if ((style & SWT.RIGHT) != 0) {
        align = OS.NSRightTextAlignment;
      }
      widget.setAlignment(align);
      view = widget;
    } else {
      NSScrollView scrollWidget = ((NSScrollView) (new SWTScrollView().alloc()));
      scrollWidget.init();
      scrollWidget.setHasVerticalScroller((style & SWT.VERTICAL) != 0);
      scrollWidget.setHasHorizontalScroller((style & SWT.HORIZONTAL) != 0);
      scrollWidget.setAutoresizesSubviews(true);
      if ((style & SWT.BORDER) != 0) {
        scrollWidget.setBorderType(NSBezelBorder);
      }
      NSTextView widget = ((NSTextView) (new SWTTextView().alloc()));
      widget.init();
      widget.setEditable((style & SWT.READ_ONLY) == 0);
      NSSize size = new NSSize();
      size.width = size.height = Float.MAX_VALUE;
      widget.setMaxSize(size);
      widget.setAutoresizingMask(OS.NSViewWidthSizable | OS.NSViewHeightSizable);
      if ((style & SWT.WRAP) == 0) {
        NSTextContainer textContainer = widget.textContainer();
        widget.setHorizontallyResizable(true);
        textContainer.setWidthTracksTextView(false);
        NSSize csize = new NSSize();
        csize.width = csize.height = Float.MAX_VALUE;
        textContainer.setContainerSize(csize);
      }
      int align = OS.NSLeftTextAlignment;
      if ((style & SWT.CENTER) != 0) {
        align = OS.NSCenterTextAlignment;
      }
      if ((style & SWT.RIGHT) != 0) {
        align = OS.NSRightTextAlignment;
      }
      widget.setAlignment(align);
      widget.setRichText(false);
      widget.setDelegate(widget);
      widget.setFont(display.getSystemFont().handle);
      view = widget;
      scrollView = scrollWidget;
    }
  }
}
