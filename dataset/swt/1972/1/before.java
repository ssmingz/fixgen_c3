class PlaceHold {
  public int internal_new_GC(GCData data) {
    checkWidget();
    int[] buffer = new int[1];
    int context = 0;
    int paintRgn = 0;
    int visibleRgn = 0;
    if (data.paintEvent != 0) {
      int theEvent = data.paintEvent;
      OS.GetEventParameter(
          theEvent, kEventParamCGContextRef, typeCGContextRef, null, 4, null, buffer);
      context = buffer[0];
      OS.GetEventParameter(theEvent, kEventParamRgnHandle, typeQDRgnHandle, null, 4, null, buffer);
      visibleRgn = paintRgn = buffer[0];
    }
    if (context == 0) {
      int window = OS.GetControlOwner(handle);
      int port = OS.GetWindowPort(window);
      OS.CreateCGContextForPort(port, buffer);
      context = buffer[0];
      if (context != 0) {
        Rect rect = new Rect();
        OS.GetControlBounds(handle, rect);
        Rect portRect = new Rect();
        OS.GetPortBounds(port, portRect);
        visibleRgn = getVisibleRegion(handle);
        if (paintRgn != 0) {
          OS.SectRgn(paintRgn, visibleRgn, visibleRgn);
        }
        OS.ClipCGContextToRegion(context, portRect, visibleRgn);
        int portHeight = portRect.bottom - portRect.top;
        OS.CGContextScaleCTM(context, 1, -1);
        OS.CGContextTranslateCTM(context, rect.left, (-portHeight) + rect.top);
      }
    }
    if (context == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    if (data != null) {
      Display display = getDisplay();
      data.device = display;
      data.foreground =
          (foreground != null) ? foreground : display.getSystemColor(COLOR_BLACK).handle;
      data.background =
          (background != null) ? background : display.getSystemColor(COLOR_WHITE).handle;
      data.font = (font != null) ? font : defaultFont();
      data.visibleRgn = visibleRgn;
      data.control = handle;
    } else if (visibleRgn != paintRgn) {
      OS.DisposeRgn(visibleRgn);
    }
    return context;
  }
}
