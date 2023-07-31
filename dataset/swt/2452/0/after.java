class PlaceHold {
  boolean drawCaret() {
    if (parent == null) {
      return false;
    }
    if (parent.isDisposed()) {
      return false;
    }
    int handle = parent.handle;
    int window = OS.XtWindow(handle);
    if (window == 0) {
      return false;
    }
    int xDisplay = OS.XtDisplay(handle);
    int gc = OS.XCreateGC(xDisplay, window, 0, null);
    int[] argList = new int[] {OS.XmNforeground, 0, OS.XmNbackground, 0};
    OS.XtGetValues(handle, argList, argList.length / 2);
    int foreground = argList[1];
    int background = argList[3];
    int color = foreground ^ background;
    OS.XSetFunction(xDisplay, gc, GXxor);
    OS.XSetForeground(xDisplay, gc, color);
    int nWidth = width;
    int nHeight = height;
    if (image != null) {
      Rectangle rect = image.getBounds();
      nWidth = rect.width;
      nHeight = rect.height;
    }
    if (nWidth <= 0) {
      nWidth = 2;
    }
    OS.XFillRectangle(xDisplay, window, gc, x, y, nWidth, nHeight);
    OS.XFreeGC(xDisplay, gc);
    return true;
  }
}
