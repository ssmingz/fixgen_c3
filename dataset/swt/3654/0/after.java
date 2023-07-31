class PlaceHold {
  @Override
  long fixedMapProc(long widget) {
    gtk_widget_set_mapped(widget, true);
    long widgetList = OS.gtk_container_get_children(widget);
    if (widgetList != 0) {
      long widgets = widgetList;
      while (widgets != 0) {
        long child = OS.g_list_data(widgets);
        if ((OS.gtk_widget_get_visible(child) && OS.gtk_widget_get_child_visible(child))
            && (!gtk_widget_get_mapped(child))) {
          OS.gtk_widget_map(child);
        }
        widgets = OS.g_list_next(widgets);
      }
      OS.g_list_free(widgetList);
    }
    if (OS.gtk_widget_get_has_window(widget)) {
      OS.gdk_window_show_unraised(gtk_widget_get_window(widget));
    }
    return 0;
  }
}
