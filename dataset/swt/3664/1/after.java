class PlaceHold {
  int processPaint(Object callData) {
    if ((style & SWT.SEPARATOR) != 0) {
      return 0;
    }
    int hndl = topHandle();
    Display display = getDisplay();
    MacRect bounds = new MacRect();
    OS.GetControlBounds(hndl, bounds.getData());
    int w = bounds.getWidth();
    int h = bounds.getHeight();
    int borderWidth = ((style & SWT.BORDER) != 0) ? 1 : 0;
    GC gc = new GC(this);
    gc.carbon_focus();
    gc.fillRectangle(0, 0, w, h);
    if (image != null) {
      Rectangle imageBounds = image.getBounds();
      gc.drawImage(image, (w - imageBounds.width) / 2, (h - imageBounds.height) / 2);
    } else {
      int sHandle = OS.CFStringCreateWithCharacters(MacUtil.removeMnemonics(text));
      boolean wrap = (style & SWT.WRAP) != 0;
      short just = 0;
      if ((style & SWT.RIGHT) != 0) {
        just = 2;
      } else if ((style & SWT.CENTER) != 0) {
        just = 1;
      }
      if (OS.IsControlEnabled(handle)) {
        OS.RGBForeColor(0x0);
      } else {
        OS.RGBForeColor(0x808080);
      }
      gc.installFont();
      bounds.set(
          bounds.getX() + borderWidth,
          bounds.getY() + borderWidth,
          w - (2 * borderWidth),
          h - (2 * borderWidth));
      OS.DrawThemeTextBox(
          sHandle, kThemeCurrentPortFont, kThemeStateActive, wrap, bounds.getData(), just, 0);
      OS.CFRelease(sHandle);
    }
    if (borderWidth > 0) {
      gc.setForeground(display.getSystemColor(COLOR_GRAY));
      gc.drawRectangle(0, 0, w - 1, h - 1);
    }
    gc.carbon_unfocus();
    gc.dispose();
    return 0;
  }
}
