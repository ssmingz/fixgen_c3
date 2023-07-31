class PlaceHold {
  long gtk_enter_notify_event(long widget, long event) {
    parent.gtk_enter_notify_event(widget, event);
    drawHotImage = ((parent.style & SWT.FLAT) != 0) && (hotImage != null);
    if (drawHotImage) {
      ImageList imageList = parent.imageList;
      if (imageList != null) {
        int index = imageList.indexOf(hotImage);
        if ((index != (-1)) && (imageHandle != 0)) {
          long pixbuf = imageList.getPixbuf(index);
          gtk_image_set_from_pixbuf(imageHandle, pixbuf);
        }
      }
    }
    return 0;
  }
}
