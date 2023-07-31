class PlaceHold {
  void createHandle() {
    state |= THEME_BACKGROUND;
    NSBox widget = ((NSBox) (new SWTBox().alloc()));
    if ((style & SWT.SEPARATOR) != 0) {
      NSRect rect = new NSRect();
      rect.width = DEFAULT_WIDTH;
      rect.height = DEFAULT_HEIGHT;
      widget.initWithFrame(rect);
      widget.setTitle(NSString.string());
      widget.setBorderType(NSNoBorder);
      widget.setBoxType(NSBoxCustom);
      widget.setContentViewMargins(new NSSize());
      double lineWidth = widget.borderWidth();
      if ((style & SWT.HORIZONTAL) != 0) {
        rect.height = ((int) (Math.ceil(lineWidth * 2)));
        rect.y = (DEFAULT_HEIGHT / 2) - (rect.height / 2);
      } else {
        rect.width = ((int) (Math.ceil(lineWidth * 2)));
        rect.x = (DEFAULT_WIDTH / 2) - (rect.width / 2);
      }
      NSBox separator = ((NSBox) (new SWTBox().alloc()));
      separator.initWithFrame(rect);
      separator.setBoxType(NSBoxSeparator);
      if ((style & SWT.HORIZONTAL) != 0) {
        separator.setAutoresizingMask(
            (OS.NSViewWidthSizable | OS.NSViewMinYMargin) | OS.NSViewMaxYMargin);
      } else {
        separator.setAutoresizingMask(
            (OS.NSViewHeightSizable | OS.NSViewMinXMargin) | OS.NSViewMaxXMargin);
      }
      NSView child = ((NSView) (new NSView().alloc().init()));
      separator.setContentView(child);
      child.release();
      widget.addSubview(separator);
      this.separator = separator;
    } else {
      widget.init();
      widget.setTitle(NSString.string());
      widget.setBorderType(NSNoBorder);
      widget.setBorderWidth(0);
      widget.setBoxType(NSBoxCustom);
      NSSize offsetSize = new NSSize();
      widget.setContentViewMargins(offsetSize);
      NSImageView imageWidget = ((NSImageView) (new SWTImageView().alloc()));
      imageWidget.init();
      imageWidget.setImageScaling(NSScaleNone);
      NSTextField textWidget = ((NSTextField) (new SWTTextField().alloc()));
      textWidget.init();
      textWidget.setBordered(false);
      textWidget.setEditable(false);
      textWidget.setDrawsBackground(false);
      NSTextFieldCell cell = new NSTextFieldCell(textWidget.cell());
      cell.setWraps((style & SWT.WRAP) != 0);
      widget.addSubview(imageWidget);
      widget.addSubview(textWidget);
      widget.setContentView(textWidget);
      imageView = imageWidget;
      textView = textWidget;
      _setAlignment();
    }
    view = widget;
  }
}
