class PlaceHold {
  int processPaint(Object callData) {
    if ((style & SWT.SEPARATOR) != 0) {
      return OS.eventNotHandledErr;
    }
    GC gc = new GC(this);
    MacControlEvent me = ((MacControlEvent) (callData));
    Rectangle r = gc.carbon_focus(me.getDamageRegionHandle(), me.getGCContext());
    if (!r.isEmpty()) {
      Rect bounds = new Rect();
      int hndl = topHandle();
      OS.GetControlBounds(hndl, bounds);
      int w = bounds.right - bounds.left;
      int h = bounds.bottom - bounds.top;
      int borderWidth = ((style & SWT.BORDER) != 0) ? 1 : 0;
      gc.fillRectangle(0, 0, r.width, r.height);
      boolean enabled = OS.IsControlEnabled(handle);
      if (image != null) {
        Rectangle imageBounds = image.getBounds();
        Image im;
        if (enabled) {
          im = image;
        } else {
          if (disabled == null) {
            disabled = new Image(getDisplay(), image, SWT.IMAGE_DISABLE);
          }
          im = disabled;
        }
        gc.drawImage(im, (w - imageBounds.width) / 2, (h - imageBounds.height) / 2);
      } else {
        int sHandle = OS.CFStringCreateWithCharacters(MacUtil.removeMnemonics(text));
        boolean wrap = (style & SWT.WRAP) != 0;
        short just = 0;
        if ((style & SWT.RIGHT) != 0) {
          just = 2;
        } else if ((style & SWT.CENTER) != 0) {
          just = 1;
        }
        MacUtil.RGBForeColor(enabled ? 0x0 : 0x808080);
        gc.installFont();
        OS.SetRect(
            bounds,
            ((short) (borderWidth)),
            ((short) (borderWidth)),
            ((short) (w - borderWidth)),
            ((short) (h - borderWidth)));
        OS.DrawThemeTextBox(
            sHandle, ((short) (kThemeCurrentPortFont)), kThemeStateActive, wrap, bounds, just, 0);
        OS.CFRelease(sHandle);
      }
      if (borderWidth > 0) {
        gc.setForeground(getDisplay().getSystemColor(COLOR_GRAY));
        gc.drawRectangle(0, 0, w - 1, h - 1);
      }
    }
    gc.carbon_unfocus();
    gc.dispose();
    return OS.noErr;
  }
}
