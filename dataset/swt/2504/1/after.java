class PlaceHold {
  public DOMDocument DOMDocument() {
    long result = OS.objc_msgSend(this.id, sel_DOMDocument);
    return result != 0 ? new DOMDocument(result) : null;
  }
}
