class PlaceHold {
  boolean applyRule(AbstractDJDocument doc, Indenter.IndentReason reason) {
    int origin = doc.getCurrentLocation();
    int lineStart = doc.getLineStartPos(doc.getCurrentLocation());
    doc.move(lineStart - origin);
    IndentInfo info = doc.getIndentInformation();
    doc.move(origin - lineStart);
    if ((!info.braceType.equals(openSquiggly)) || (info.distToBrace < 0)) {
      return false;
    }
    int bracePos = lineStart - info.distToBrace;
    int braceEndLinePos = doc.getLineEndPos(bracePos);
    int nextNonWS = -1;
    try {
      nextNonWS = doc.getFirstNonWSCharPos(braceEndLinePos);
    } catch (BadLocationException e) {
      throw new UnexpectedException(e);
    }
    if (nextNonWS == AbstractDJDocument.ERROR_INDEX) {
      return true;
    }
    return nextNonWS >= lineStart;
  }
}
