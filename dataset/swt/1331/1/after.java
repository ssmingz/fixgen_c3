class PlaceHold {
  int gtk_enter_notify_event(int widget, int event) {
    parent.gtk_enter_notify_event(widget, event);
    drawHotImage = ((parent.style & SWT.FLAT) != 0) && (hotImage != null);
    if (drawHotImage) {
      ImageList imageList = parent.imageList;
      if (imageList != null) {
        int index = imageList.indexOf(hotImage);
        if ((index != (-1)) && (imageHandle != 0)) {
          int pixbuf = imageList.getPixbuf(index);
          OS.gtk_image_set_from_pixbuf(imageHandle, pixbuf);
        }
      }
    }
    return 0;
  }
}
