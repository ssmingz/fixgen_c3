class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    if ((style & SWT.ARROW) != 0) {
      int[] outMetric = new int[1];
      OS.GetThemeMetric(kThemeMetricDisclosureTriangleHeight, outMetric);
      int width = outMetric[0];
      int height = outMetric[0];
      if (wHint != SWT.DEFAULT) {
        width = wHint;
      }
      if (hHint != SWT.DEFAULT) {
        height = hHint;
      }
      return new Point(width, height);
    }
    int width = 0;
    int height = 0;
    if (isImage && (image != null)) {
      Rectangle bounds = image.getBounds();
      width = bounds.width;
      height = bounds.height;
    } else {
      int[] ptr = new int[1];
      OS.CopyControlTitleAsCFString(handle, ptr);
      if (ptr[0] != 0) {
        Point ioBounds = new Point();
        if (font == null) {
          OS.GetThemeTextDimensions(
              ptr[0], ((short) (kThemePushButtonFont)), kThemeStateActive, false, ioBounds, null);
        } else {
          int[] currentPort = new int[1];
          OS.GetPort(currentPort);
          OS.SetPortWindowPort(OS.GetControlOwner(handle));
          OS.TextFont(id);
          OS.TextFace(style);
          OS.TextSize(size);
          OS.GetThemeTextDimensions(
              ptr[0], ((short) (kThemeCurrentPortFont)), kThemeStateActive, false, ioBounds, null);
          OS.SetPort(currentPort[0]);
        }
        width = ioBounds.h;
        height = ioBounds.v;
        OS.CFRelease(ptr[0]);
      } else {
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
      }
    }
    if ((style & (SWT.CHECK | SWT.RADIO)) != 0) {
      int[] outMetric = new int[1];
      int metric =
          ((style & SWT.CHECK) != 0)
              ? OS.kThemeMetricCheckBoxWidth
              : OS.kThemeMetricRadioButtonWidth;
      OS.GetThemeMetric(metric, outMetric);
      width += outMetric[0] + 3;
      height = Math.max(outMetric[0], height);
    } else if (((style & SWT.FLAT) != 0) || ((style & SWT.TOGGLE) != 0)) {
      width += 10;
      height += 10;
    } else {
      width += 28;
      int[] outMetric = new int[1];
      OS.GetThemeMetric(kThemeMetricPushButtonHeight, outMetric);
      height = Math.max(height, outMetric[0]);
    }
    Rect inset = getInset();
    width += inset.left + inset.right;
    height += inset.top + inset.bottom;
    width = Math.max(20, width);
    int border = ((style & SWT.PUSH) != 0) ? 2 : 0;
    if (wHint != SWT.DEFAULT) {
      width = wHint + (border * 2);
    }
    if (hHint != SWT.DEFAULT) {
      height = hHint + (border * 2);
    }
    return new Point(width, height);
  }
}
