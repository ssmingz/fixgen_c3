class PlaceHold {
  int fixedMapProc(int widget) {
    OS.GTK_WIDGET_SET_FLAGS(widget, GTK_MAPPED);
    int widgetList = OS.gtk_container_get_children(widget);
    if (widgetList != 0) {
      int widgets = widgetList;
      while (widgets != 0) {
        int child = OS.g_list_data(widgets);
        if ((OS.GTK_WIDGET_VISIBLE(child) && OS.gtk_widget_get_child_visible(child))
            && (!gtk_widget_get_mapped(child))) {
          OS.gtk_widget_map(child);
        }
        widgets = OS.g_list_next(widgets);
      }
      OS.g_list_free(widgetList);
    }
    if (gtk_widget_get_has_window(widget)) {
      OS.gdk_window_show_unraised(OS.GTK_WIDGET_WINDOW(widget));
    }
    return 0;
  }
}
