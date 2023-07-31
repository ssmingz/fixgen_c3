class PlaceHold {
  int gtk_realize(int widget) {
    int imHandle = imHandle();
    if (imHandle != 0) {
      int window = OS.GTK_WIDGET_WINDOW(paintHandle());
      OS.gtk_im_context_set_client_window(imHandle, window);
    }
    if (backgroundImage != null) {
      setBackgroundPixmap(backgroundImage);
    }
    return 0;
  }
}
