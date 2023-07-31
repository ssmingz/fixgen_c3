class PlaceHold {
  int gtk_enter_notify_event(int widget, int event) {
    parent.gtk_enter_notify_event(widget, event);
    drawHotImage = ((parent.style & SWT.FLAT) != 0) && (hotImage != null);
    if (drawHotImage) {
      ImageList imageList = parent.imageList;
      if (imageList != null) {
        int index = imageList.indexOf(hotImage);
        if (index != (-1)) {
          int pixbuf = imageList.getPixbuf(index);
          imageHandle = OS.gtk_image_new_from_pixbuf(pixbuf);
          OS.gtk_widget_show(imageHandle);
          OS.gtk_tool_button_set_icon_widget(handle, imageHandle);
        }
      }
    }
    return 0;
  }
}
