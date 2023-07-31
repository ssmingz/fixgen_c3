class PlaceHold {
  public void setIndent(int indent) {
    checkLayout();
    if (indent < 0) {
      return;
    }
    if (this.indent == indent) {
      return;
    }
    freeRuns();
    this.indent = indent;
  }
}
