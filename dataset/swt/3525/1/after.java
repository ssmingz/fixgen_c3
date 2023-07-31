class PlaceHold {
  public void fillGradientRectangle(int x, int y, int width, int height, boolean vertical) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if ((width == 0) || (height == 0)) {
      return;
    }
    try {
      if (!focus(true, null)) {
        return;
      }
      int fromColor;
      int toColor;
      fromColor = data.foreground;
      toColor = data.background;
      boolean swapColors = false;
      if (width < 0) {
        x += width;
        width = -width;
        if (!vertical) {
          swapColors = true;
        }
      }
      if (height < 0) {
        y += height;
        height = -height;
        if (vertical) {
          swapColors = true;
        }
      }
      if (swapColors) {
        final int t = fromColor;
        fromColor = toColor;
        toColor = t;
      }
      if (fromColor == toColor) {
        MacUtil.RGBForeColor(data.foreground);
        fRect.set(x, y, width, height);
        OS.PaintRect(fRect.getData());
        return;
      }
      int depth = getCurrentScreenDepth();
      final boolean directColor = depth > 8;
      RGB fromRGB = Color.carbon_new(data.device, fromColor, false).getRGB();
      RGB toRGB = Color.carbon_new(data.device, toColor, false).getRGB();
      final int redBits;
      final int greenBits;
      final int blueBits;
      if (directColor) {
        redBits = getChannelWidth(0xff0000);
        greenBits = getChannelWidth(0xff00);
        blueBits = getChannelWidth(0xff);
      } else {
        redBits = greenBits = blueBits = 0;
      }
      ImageData.fillGradientRectangle(
          this,
          data.device,
          x,
          y,
          width,
          height,
          vertical,
          fromRGB,
          toRGB,
          redBits,
          greenBits,
          blueBits);
    } finally {
      unfocus(true);
    }
  }
}
