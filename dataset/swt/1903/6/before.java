class PlaceHold {
  long getHandle() {
    if (OS.GTK_VERSION >= OS.VERSION(3, 0, 0)) {
      embedHandle = OS.gtk_box_new(GTK_ORIENTATION_HORIZONTAL, 0);
      OS.gtk_box_set_homogeneous(embedHandle, false);
    } else {
      embedHandle = OS.gtk_hbox_new(false, 0);
    }
    OS.gtk_container_add(browser.handle, embedHandle);
    OS.gtk_widget_show(embedHandle);
    return embedHandle;
  }
}
