class PlaceHold {
  void setBounds(int x, int y, int width, int height, boolean move, boolean resize) {
    if ((style & SWT.READ_ONLY) == 0) {
      NSControl widget = ((NSControl) (view));
      NSSize size = widget.cell().cellSize();
      height = Math.min(height, ((int) (0.5F + size.height)));
    }
    super.setBounds(x, y, width, height, move, resize);
  }
}
