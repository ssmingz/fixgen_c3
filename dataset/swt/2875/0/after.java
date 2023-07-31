class PlaceHold {
  Image getDragSourceImage(DragSourceEvent event) {
    if (dragSourceImage != null) {
      dragSourceImage.dispose();
    }
    dragSourceImage = null;
    Table table = ((Table) (control));
    if (OS.GTK_VERSION < OS.VERSION(2, 2, 0)) {
      return null;
    }
    if (table.isListening(EraseItem) || table.isListening(PaintItem)) {
      return null;
    }
    int handle = table.handle;
    int selection = OS.gtk_tree_view_get_selection(handle);
    int[] model = (OS.GTK_VERSION < OS.VERSION(2, 2, 4)) ? new int[1] : null;
    int list = OS.gtk_tree_selection_get_selected_rows(selection, model);
    if (list == 0) {
      return null;
    }
    int count = Math.min(10, OS.g_list_length(list));
    Display display = table.getDisplay();
    if (count == 1) {
      int path = OS.g_list_nth_data(list, 0);
      int pixmap = OS.gtk_tree_view_create_row_drag_icon(handle, path);
      dragSourceImage = Image.gtk_new(display, ICON, pixmap, 0);
    } else {
      int width = 0;
      int height = 0;
      int[] w = new int[1];
      int[] h = new int[1];
      int[] yy = new int[count];
      int[] hh = new int[count];
      int[] pixmaps = new int[count];
      GdkRectangle rect = new GdkRectangle();
      for (int i = 0; i < count; i++) {
        int path = OS.g_list_nth_data(list, i);
        OS.gtk_tree_view_get_cell_area(handle, path, 0, rect);
        pixmaps[i] = OS.gtk_tree_view_create_row_drag_icon(handle, path);
        OS.gdk_drawable_get_size(pixmaps[i], w, h);
        width = Math.max(width, w[0]);
        height = (rect.y + h[0]) - yy[0];
        yy[i] = rect.y;
        hh[i] = h[0];
      }
      int source = OS.gdk_pixmap_new(OS.GDK_ROOT_PARENT(), width, height, -1);
      int gcSource = OS.gdk_gc_new(source);
      int mask = OS.gdk_pixmap_new(OS.GDK_ROOT_PARENT(), width, height, 1);
      int gcMask = OS.gdk_gc_new(mask);
      GdkColor color = new GdkColor();
      color.pixel = 0;
      OS.gdk_gc_set_foreground(gcMask, color);
      OS.gdk_draw_rectangle(mask, gcMask, 1, 0, 0, width, height);
      color.pixel = 1;
      OS.gdk_gc_set_foreground(gcMask, color);
      for (int i = 0; i < count; i++) {
        OS.gdk_draw_drawable(source, gcSource, pixmaps[i], 0, 0, 0, yy[i] - yy[0], -1, -1);
        OS.gdk_draw_rectangle(mask, gcMask, 1, 0, yy[i] - yy[0], width, hh[i]);
        OS.g_object_unref(pixmaps[i]);
      }
      OS.g_object_unref(gcSource);
      OS.g_object_unref(gcMask);
      dragSourceImage = Image.gtk_new(display, ICON, source, mask);
    }
    OS.g_list_free(list);
    return dragSourceImage;
  }
}
