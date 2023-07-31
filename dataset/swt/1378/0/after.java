class TextLayout {
  public TextLayout(Device device) {
    super(device);
    wrapWidth = ascent = descent = -1;
    lineSpacing = 0;
    orientation = SWT.LEFT_TO_RIGHT;
    XFontStruct fontStruct = getFontHeigth(this.device.getSystemFont());
    defaultAscent = fontStruct.ascent;
    defaultDescent = fontStruct.descent;
    styles = new StyleItem[2];
    styles[0] = new StyleItem();
    styles[1] = new StyleItem();
    text = "";
    init();
  }
}
