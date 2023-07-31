class PlaceHold {
  void createHandle() {
    NSButton widget = ((NSButton) (new SWTButton().alloc()));
    widget.init();
    int type = OS.NSMomentaryLightButton;
    if ((style & SWT.PUSH) != 0) {
      if ((style & SWT.FLAT) != 0) {
        widget.setBezelStyle(NSShadowlessSquareBezelStyle);
      } else {
        widget.setBezelStyle(NSRoundedBezelStyle);
      }
    } else if ((style & SWT.CHECK) != 0) {
      type = OS.NSSwitchButton;
      widget.setAllowsMixedState(true);
    } else if ((style & SWT.RADIO) != 0) {
      type = OS.NSRadioButton;
    } else if ((style & SWT.TOGGLE) != 0) {
      type = OS.NSPushOnPushOffButton;
      if ((style & SWT.FLAT) != 0) {
        widget.setBezelStyle(NSShadowlessSquareBezelStyle);
      } else {
        widget.setBezelStyle(NSRoundedBezelStyle);
      }
    } else if ((style & SWT.ARROW) != 0) {
      widget.setBezelStyle(NSRegularSquareBezelStyle);
    }
    widget.setButtonType(type);
    widget.setTitle(NSString.stringWith(""));
    widget.setImagePosition(NSImageLeft);
    widget.setTarget(widget);
    widget.setAction(sel_sendSelection);
    view = widget;
    _setAlignment(style);
  }
}
