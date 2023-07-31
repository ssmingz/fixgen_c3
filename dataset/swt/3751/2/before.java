class PlaceHold {
  public void setImage(Image image) {
    if ((image != null) && image.equals(getImage())) {
      return;
    }
    int oldHeight = parent.getTabHeight();
    super.setImage(image);
    if (oldHeight != parent.getTabHeight()) {
      parent.notifyListeners(Resize, new Event());
    } else {
      parent.layoutItems();
      parent.redraw();
    }
  }
}
