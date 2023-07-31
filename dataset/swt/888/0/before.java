class PlaceHold {
  public void dispose() {
    if (isDisposed()) {
      return;
    }
    ToolBar parent = this.parent;
    super.dispose();
    parent.layoutItems();
  }
}
