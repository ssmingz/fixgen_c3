class PlaceHold {
  public void setImage(Image value) {
    checkWidget();
    if (value == image) {
      return;
    }
    if ((value != null) && value.equals(image)) {
      return;
    }
    super.setImage(value);
    if (parent.getHeaderImageHeight() == 0) {
      int oldHeaderHeight = parent.getHeaderHeight();
      parent.setHeaderImageHeight(value.getBounds().height);
      if (oldHeaderHeight != parent.getHeaderHeight()) {
        parent.redrawHeader();
        parent.redraw();
        return;
      }
    }
    parent.redraw(x, 0, width, parent.getHeaderHeight(), true);
  }
}
