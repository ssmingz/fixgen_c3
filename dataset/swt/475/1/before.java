class PlaceHold {
  void releaseWidget() {
    display.removeMouseHoverTimeout(handle);
    super.releaseWidget();
    int imHandle = imHandle();
    if (imHandle != 0) {
      OS.gtk_im_context_reset(imHandle);
      OS.gtk_im_context_set_client_window(imHandle, 0);
    }
    if (enableWindow != 0) {
      OS.gdk_window_destroy(enableWindow);
      enableWindow = 0;
    }
    if ((menu != null) && (!menu.isDisposed())) {
      menu.dispose();
    }
    menu = null;
    cursor = null;
    toolTipText = null;
    parent = null;
    layoutData = null;
    accessible = null;
  }
}
