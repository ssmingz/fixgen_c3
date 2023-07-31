class PlaceHold {
  boolean applyRule(AbstractDJDocument doc, Indenter.IndentReason reason) {
    int charPos = doc.findCharOnLine(doc.getCurrentLocation(), _findChar);
    if (charPos == (-1)) {
      return false;
    } else {
      return true;
    }
  }
}
