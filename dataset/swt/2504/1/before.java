class PlaceHold {
  public DOMDocument DOMDocument() {
    int result = OS.objc_msgSend(this.id, sel_DOMDocument);
    return result != 0 ? new DOMDocument(result) : null;
  }
}
