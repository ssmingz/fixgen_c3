class PlaceHold {
  public void setImage(Image image) {
    checkWidget();
    if ((image != null) && image.equals(getImage())) {
      return;
    }
    int oldHeight = parent.getTabHeight();
    super.setImage(image);
    if (oldHeight != parent.getTabHeight()) {
      parent.onClientAreaChange();
    } else {
      parent.setItemBounds();
      parent.redraw();
    }
  }
}
