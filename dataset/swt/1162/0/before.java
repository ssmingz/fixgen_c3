class PlaceHold {
  public void setExpanded(boolean expanded) {
    checkWidget();
    this.expanded = expanded;
    parent.showItem(parent.indexOf(this));
  }
}
