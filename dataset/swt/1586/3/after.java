class PlaceHold {
  Point minimumSize(int wHint, int hHint, boolean flushCache) {
    Control[] children = _getChildren();
    int width = 0;
    int height = 0;
    for (int i = 0; i < children.length; i++) {
      Control child = children[i];
      int index = 0;
      int count = 0;
      long list = OS.gtk_container_get_children(handle);
      if (list != 0) {
        count = OS.g_list_length(list);
        OS.g_list_free(list);
      }
      while (index < count) {
        if (items[index].control == child) {
          break;
        }
        index++;
      }
      if (index == count) {
        Rectangle rect = child.getBounds();
        width = Math.max(width, rect.x + rect.width);
        height = Math.max(height, rect.y + rect.height);
      } else {
        Point size = child.computeSize(wHint, hHint, flushCache);
        width = Math.max(width, size.x);
        height = Math.max(height, size.y);
      }
    }
    return new Point(width, height);
  }
}
