class PlaceHold {
  boolean applyRule(AbstractDJDocument doc, Indenter.IndentReason reason) {
    int charPos = doc.findCharOnLine(doc.getCurrentLocation(), _findChar);
    if (charPos == AbstractDJDocument.ERROR_INDEX) {
      return false;
    } else {
      return true;
    }
  }
}
