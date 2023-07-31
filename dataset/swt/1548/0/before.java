class PlaceHold {
  int gtk_size_allocate(int widget, int allocation) {
    int[] width = new int[1];
    int[] height = new int[1];
    OS.gtk_window_get_size(shellHandle, width, height);
    if ((oldWidth != width[0]) || (oldHeight != height[0])) {
      oldWidth = width[0];
      oldHeight = height[0];
      resizeBounds(width[0], height[0], true);
    }
    return 0;
  }
}
