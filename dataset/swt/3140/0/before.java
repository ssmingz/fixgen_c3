class PlaceHold {
  public void setCurrent() {
    checkWidget();
    if (GLX.glXGetCurrentContext() == context) {
      return;
    }
    long window = OS.GTK_WIDGET_WINDOW(handle);
    long xDisplay = OS.gdk_x11_drawable_get_xdisplay(window);
    GLX.glXMakeCurrent(xDisplay, xWindow, context);
  }
}
