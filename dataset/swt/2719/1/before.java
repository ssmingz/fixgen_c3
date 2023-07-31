class PlaceHold {
  public void setExpanded(boolean expanded) {
    checkWidget();
    if (items.length == 0) {
      return;
    }
    this.expanded = expanded;
    if (tableItem == null) {
      return;
    }
    parent.setRedraw(false);
    for (int i = 0; i < items.length; i++) {
      items[i].setVisible(expanded);
    }
    Image image = (expanded) ? parent.getMinusImage() : parent.getPlusImage();
    tableItem.setImage(0, image);
    parent.setRedraw(true);
  }
}
