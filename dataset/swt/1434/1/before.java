class PlaceHold {
  public void setEditor(Control editor) {
    if (editor == null) {
      this.editor = null;
      return;
    }
    this.editor = editor;
    resize();
  }
}
