class TextLayout {
  public TextLayout(Device device) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    this.device = device;
    wrapWidth = ascent = descent = -1;
    lineSpacing = 0;
    orientation = SWT.LEFT_TO_RIGHT;
    XFontStruct fontStruct = getFontHeigth(device.getSystemFont());
    defaultAscent = fontStruct.ascent;
    defaultDescent = fontStruct.descent;
    styles = new StyleItem[2];
    styles[0] = new StyleItem();
    styles[1] = new StyleItem();
    text = "";
    if (device.tracking) {
      device.new_Object(this);
    }
  }
}
