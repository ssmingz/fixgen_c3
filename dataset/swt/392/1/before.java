class PlaceHold {
  boolean setBounds(int x, int y, int width, int height, boolean move, boolean resize) {
    boolean changed = super.setBounds(x, y, width, height, move, resize);
    if (changed && resize) {
      int index = getSelectionIndex();
      if (index != (-1)) {
        TabItem item = items[index];
        Control control = item.control;
        if ((control != null) && (!control.isDisposed())) {
          control.setBounds(getClientArea());
        }
      }
    }
    return changed;
  }
}
