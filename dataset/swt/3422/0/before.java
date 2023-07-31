class TextLayout {
  public TextLayout(Device device) {
    if (device == null) {
      device = Device.getDevice();
    }
    if (device == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    this.device = device;
    int[] buffer = new int[1];
    OS.ATSUCreateTextLayout(buffer);
    if (buffer[0] == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    layout = buffer[0];
    setLayoutControl(kATSULineDirectionTag, kATSULeftToRightBaseDirection, 1);
    setLayoutControl(kATSULineLayoutOptionsTag, kATSLineLastNoJustification, 4);
    OS.ATSUSetHighlightingMethod(layout, 1, new ATSUUnhighlightData());
    ascent = descent = -1;
    text = "";
    styles = new StyleItem[2];
    styles[0] = new StyleItem();
    styles[1] = new StyleItem();
  }
}
