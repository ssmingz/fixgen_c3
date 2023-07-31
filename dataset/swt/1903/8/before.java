class PlaceHold {
  Image getDragSourceImage(DragSourceEvent event) {
    if (dragSourceImage != null) {
      dragSourceImage.dispose();
    }
    dragSourceImage = null;
    Tree tree = ((Tree) (control));
    if (tree.isListening(EraseItem) || tree.isListening(PaintItem)) {
      return null;
    }
    if (OS.GTK_VERSION >= OS.VERSION(3, 0, 0)) {
      return null;
    }
    long handle = tree.handle;
    long selection = OS.gtk_tree_view_get_selection(handle);
    long[] model = null;
    long list = OS.gtk_tree_selection_get_selected_rows(selection, model);
    if (list == 0) {
      return null;
    }
    int count = Math.min(10, OS.g_list_length(list));
    Display display = tree.getDisplay();
    if (count == 1) {
      long path = OS.g_list_nth_data(list, 0);
      long pixmap = OS.gtk_tree_view_create_row_drag_icon(handle, path);
      dragSourceImage = Image.gtk_new(display, ICON, pixmap, 0);
      OS.gtk_tree_path_free(path);
    } else {
      int width = 0;
      int height = 0;
      int[] w = new int[1];
      int[] h = new int[1];
      int[] yy = new int[count];
      int[] hh = new int[count];
      long[] pixmaps = new long[count];
      GdkRectangle rect = new GdkRectangle();
      for (int i = 0; i < count; i++) {
        long path = OS.g_list_nth_data(list, i);
        OS.gtk_tree_view_get_cell_area(handle, path, 0, rect);
        pixmaps[i] = OS.gtk_tree_view_create_row_drag_icon(handle, path);
        if (OS.GTK_VERSION >= OS.VERSION(2, 24, 0)) {
          OS.gdk_pixmap_get_size(pixmaps[i], w, h);
        } else {
          OS.gdk_drawable_get_size(pixmaps[i], w, h);
        }
        width = Math.max(width, w[0]);
        height = (rect.y + h[0]) - yy[0];
        yy[i] = rect.y;
        hh[i] = h[0];
        OS.gtk_tree_path_free(path);
      }
      long source = OS.gdk_pixmap_new(OS.gdk_get_default_root_window(), width, height, -1);
      long gcSource = OS.gdk_gc_new(source);
      long mask = OS.gdk_pixmap_new(OS.gdk_get_default_root_window(), width, height, 1);
      long gcMask = OS.gdk_gc_new(mask);
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
