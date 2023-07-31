class PlaceHold {
  int setBounds(int x, int y, int width, int height, boolean move, boolean resize) {
    int result = super.setBounds(x, y, width, height, move, resize);
    if ((result & RESIZED) != 0) {
      int index = getSelectionIndex();
      if (index != (-1)) {
        TabItem item = items[index];
        Control control = item.control;
        if ((control != null) && (!control.isDisposed())) {
          control.setBounds(getClientArea());
        }
      }
    }
    return result;
  }
}
