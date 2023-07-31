class PlaceHold {
  public void dispose() {
    ToolBar parent = this.parent;
    super.dispose();
    parent.relayout();
  }
}
