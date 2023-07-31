class PlaceHold {
  public void fillGradientRectangle(int x, int y, int width, int height, boolean vertical) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if ((width == 0) || (height == 0)) {
      return;
    }
    RGB backgroundRGB;
    RGB foregroundRGB;
    backgroundRGB = getBackground().getRGB();
    foregroundRGB = getForeground().getRGB();
    RGB fromRGB;
    RGB toRGB;
    fromRGB = foregroundRGB;
    toRGB = backgroundRGB;
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
      fromRGB = backgroundRGB;
      toRGB = foregroundRGB;
    }
    if (fromRGB.equals(toRGB)) {
      fillRectangle(x, y, width, height);
      return;
    }
    if (data.gdipGraphics != 0) {
      initGdip();
      PointF p1 = new PointF();
      PointF p2 = new PointF();
      p1.X = x;
      p1.Y = y;
      if (vertical) {
        p2.X = p1.X;
        p2.Y = p1.Y + height;
      } else {
        p2.X = p1.X + width;
        p2.Y = p1.Y;
      }
      int rgb =
          (((fromRGB.red & 0xff) << 16) | ((fromRGB.green & 0xff) << 8)) | (fromRGB.blue & 0xff);
      int fromGpColor = Gdip.Color_new((data.alpha << 24) | rgb);
      if (fromGpColor == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
      rgb = (((toRGB.red & 0xff) << 16) | ((toRGB.green & 0xff) << 8)) | (toRGB.blue & 0xff);
      int toGpColor = Gdip.Color_new((data.alpha << 24) | rgb);
      if (toGpColor == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
      int brush = Gdip.LinearGradientBrush_new(p1, p2, fromGpColor, toGpColor);
      Gdip.Graphics_FillRectangle(data.gdipGraphics, brush, x, y, width, height);
      Gdip.LinearGradientBrush_delete(brush);
      Gdip.Color_delete(fromGpColor);
      Gdip.Color_delete(toGpColor);
      return;
    }
    int rop2 = 0;
    if (OS.IsWinCE) {
      rop2 = OS.SetROP2(handle, R2_COPYPEN);
      OS.SetROP2(handle, rop2);
    } else {
      rop2 = OS.GetROP2(handle);
    }
    if ((OS.IsWinNT && (rop2 != OS.R2_XORPEN))
        && (OS.GetDeviceCaps(handle, TECHNOLOGY) != OS.DT_RASPRINTER)) {
      final int hHeap = OS.GetProcessHeap();
      final int pMesh =
          OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, GRADIENT_RECT.sizeof + (TRIVERTEX.sizeof * 2));
      if (pMesh == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
      final int pVertex = pMesh + GRADIENT_RECT.sizeof;
      GRADIENT_RECT gradientRect = new GRADIENT_RECT();
      gradientRect.UpperLeft = 0;
      gradientRect.LowerRight = 1;
      OS.MoveMemory(pMesh, gradientRect, sizeof);
      TRIVERTEX trivertex = new TRIVERTEX();
      trivertex.x = x;
      trivertex.y = y;
      trivertex.Red = ((short) ((fromRGB.red << 8) | fromRGB.red));
      trivertex.Green = ((short) ((fromRGB.green << 8) | fromRGB.green));
      trivertex.Blue = ((short) ((fromRGB.blue << 8) | fromRGB.blue));
      trivertex.Alpha = -1;
      OS.MoveMemory(pVertex, trivertex, sizeof);
      trivertex.x = x + width;
      trivertex.y = y + height;
      trivertex.Red = ((short) ((toRGB.red << 8) | toRGB.red));
      trivertex.Green = ((short) ((toRGB.green << 8) | toRGB.green));
      trivertex.Blue = ((short) ((toRGB.blue << 8) | toRGB.blue));
      trivertex.Alpha = -1;
      OS.MoveMemory(pVertex + TRIVERTEX.sizeof, trivertex, sizeof);
      boolean success =
          OS.GradientFill(
              handle,
              pVertex,
              2,
              pMesh,
              1,
              vertical ? OS.GRADIENT_FILL_RECT_V : OS.GRADIENT_FILL_RECT_H);
      OS.HeapFree(hHeap, 0, pMesh);
      if (success) {
        return;
      }
    }
    final int depth = OS.GetDeviceCaps(handle, BITSPIXEL);
    final int bitResolution = (depth >= 24) ? 8 : depth >= 15 ? 5 : 0;
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
        bitResolution,
        bitResolution,
        bitResolution);
  }
}
