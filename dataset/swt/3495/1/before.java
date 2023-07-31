class PlaceHold {
  public void setRenderer(CTabFolderRenderer renderer) {
    checkWidget();
    if (this.renderer == renderer) {
      return;
    }
    if (this.renderer != null) {
      this.renderer.dispose();
    }
    if (renderer == null) {
      renderer = new CTabFolderRenderer(this);
    }
    this.renderer = renderer;
    updateTabHeight(false);
    Rectangle rectBefore = getClientArea();
    updateItems();
    Rectangle rectAfter = getClientArea();
    if (!rectBefore.equals(rectAfter)) {
      notifyListeners(Resize, new Event());
    }
    redraw();
  }
}
