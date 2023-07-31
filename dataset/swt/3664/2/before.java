class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    int border = getBorderWidth();
    int width = 0;
    int height = 0;
    if ((style & SWT.SEPARATOR) != 0) {
      if ((style & SWT.HORIZONTAL) != 0) {
        width += DEFAULT_WIDTH;
        height += 3;
      } else {
        width += 3;
        height += DEFAULT_HEIGHT;
      }
    } else if (image != null) {
      Rectangle r = image.getBounds();
      width = r.width;
      height = r.height;
    } else {
      short[] bounds = new short[2];
      short[] baseLine = new short[1];
      boolean wrap = false;
      if (((style & SWT.WRAP) != 0) && (wHint != SWT.DEFAULT)) {
        wrap = true;
        bounds[1] = ((short) (wHint));
      }
      int sHandle = OS.CFStringCreateWithCharacters(removeMnemonics(text));
      GC gc = new GC(this);
      gc.installFont();
      OS.GetThemeTextDimensions(
          sHandle, kThemeCurrentPortFont, kThemeStateActive, wrap, bounds, baseLine);
      gc.dispose();
      OS.CFRelease(sHandle);
      width = bounds[1];
      height = bounds[0];
    }
    if (wHint != SWT.DEFAULT) {
      width = wHint;
    }
    if (hHint != SWT.DEFAULT) {
      height = hHint;
    }
    return new Point(width + (2 * border), height + (2 * border));
  }
}
