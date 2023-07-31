class PlaceHold {
  public void swapBuffers() {
    checkWidget();
    long window = OS.GTK_WIDGET_WINDOW(handle);
    long xDisplay = gdk_x11_display_get_xdisplay(window);
    GLX.glXSwapBuffers(xDisplay, xWindow);
  }
}
