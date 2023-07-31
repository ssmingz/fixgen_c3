class PlaceHold {
  int gtk_leave_notify_event(int widget, int event) {
    parent.gtk_leave_notify_event(widget, event);
    if (drawHotImage) {
      drawHotImage = false;
      if (image != null) {
        ImageList imageList = parent.imageList;
        if (imageList != null) {
          int index = imageList.indexOf(image);
          if (index != (-1)) {
            int pixbuf = imageList.getPixbuf(index);
            imageHandle = OS.gtk_image_new_from_pixbuf(pixbuf);
            OS.gtk_widget_show(imageHandle);
            OS.gtk_tool_button_set_icon_widget(handle, imageHandle);
          }
        }
      }
    }
    return 0;
  }
}
