class PlaceHold {
  public void setEditor(Control editor) {
    if (editor == null) {
      this.editor = null;
      return;
    }
    this.editor = editor;
    resize();
    if ((editor == null) || editor.isDisposed()) {
      return;
    }
    editor.setVisible(true);
  }
}
