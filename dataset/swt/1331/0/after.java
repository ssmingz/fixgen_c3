class PlaceHold {
  int gtk_leave_notify_event(int widget, int event) {
    parent.gtk_leave_notify_event(widget, event);
    if (drawHotImage) {
      drawHotImage = false;
      if (image != null) {
        ImageList imageList = parent.imageList;
        if (imageList != null) {
          int index = imageList.indexOf(image);
          if ((index != (-1)) && (imageHandle != 0)) {
            int pixbuf = imageList.getPixbuf(index);
            OS.gtk_image_set_from_pixbuf(imageHandle, pixbuf);
          }
        }
      }
    }
    return 0;
  }
}
