class PlaceHold {
  long gtk_leave_notify_event(long widget, long event) {
    parent.gtk_leave_notify_event(widget, event);
    if (drawHotImage) {
      drawHotImage = false;
      if (image != null) {
        ImageList imageList = parent.imageList;
        if (imageList != null) {
          int index = imageList.indexOf(image);
          if ((index != (-1)) && (imageHandle != 0)) {
            long pixbuf = imageList.getPixbuf(index);
            gtk_image_set_from_pixbuf(imageHandle, pixbuf);
          }
        }
      }
    }
    return 0;
  }
}
