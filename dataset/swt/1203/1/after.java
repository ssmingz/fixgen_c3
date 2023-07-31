class PlaceHold {
  public void dispose() {
    if (!isValidWidget()) {
      return;
    }
    ToolBar parent = this.parent;
    super.dispose();
    parent.relayout();
  }
}
