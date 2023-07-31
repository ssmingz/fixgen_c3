class PlaceHold {
  public void swapBuffers() {
    checkWidget();
    long window = OS.gtk_widget_get_window(handle);
    long xDisplay = gdk_x11_display_get_xdisplay(window);
    GLX.glXSwapBuffers(xDisplay, xWindow);
  }
}
