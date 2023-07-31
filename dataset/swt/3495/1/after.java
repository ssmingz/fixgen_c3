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
    updateFolder(REDRAW);
  }
}
