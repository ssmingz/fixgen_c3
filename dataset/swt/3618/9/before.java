class PlaceHold {
  public void setVisible(boolean visible) {
    super.setVisible(visible);
    Control[] list = getChildren();
    for (Control i : list) {
      if ((i instanceof Table) || (i instanceof Tree)) {
        i.setVisible(visible);
      }
    }
  }
}
