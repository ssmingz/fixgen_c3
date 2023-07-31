class PlaceHold {
  public void drawString(String string, int x, int y, boolean isTransparent) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (string == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (data.updateClip) {
      setCGClipping();
    }
    int length = string.length();
    if (length == 0) {
      return;
    }
    OS.CGContextSaveGState(handle);
    OS.CGContextScaleCTM(handle, 1, -1);
    OS.CGContextSetFillColor(handle, data.foreground);
    if (Font.USE_ATSUI) {
      if (data.layout == 0) {
        createLayout();
      }
      if (string != data.string) {
        if (data.stringPtr != 0) {
          OS.DisposePtr(data.stringPtr);
        }
        Font font = data.font;
        int atsuiStyle = (font.atsuiStyle != 0) ? font.atsuiStyle : data.atsuiStyle;
        int ptr = OS.NewPtr(length * 2);
        OS.memcpy(ptr, string, length * 2);
        OS.ATSUSetTextPointerLocation(data.layout, ptr, 0, length, length);
        OS.ATSUSetRunStyle(data.layout, atsuiStyle, 0, length);
        data.string = string;
        data.stringPtr = ptr;
      }
      OS.ATSUDrawText(data.layout, 0, length, OS.X2Fix(x), OS.X2Fix(-(y + data.fontAscent)));
    } else {
      OS.CGContextSetTextDrawingMode(handle, kCGTextFill);
      byte[] buffer = string.getBytes();
      OS.CGContextShowTextAtPoint(handle, x, -(y + data.fontAscent), buffer, buffer.length);
    }
    OS.CGContextRestoreGState(handle);
    if ((data.control != 0) && (data.paintEvent == 0)) {
      OS.CGContextSynchronize(handle);
    }
  }
}
