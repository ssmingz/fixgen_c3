class PlaceHold {
  public T getErrorAtOffset(OpenDefinitionsDocument odd, int offset) {
    File file = null;
    try {
      file = odd.getFile();
    } catch (IllegalStateException e) {
      return null;
    } catch (FileMovedException e) {
      file = e.getFile();
    }
    StartAndEndIndex saei = _filesToIndexes.get(file);
    if (saei == null) {
      return null;
    }
    int start = saei.getStartPos();
    int end = saei.getEndPos();
    if (start == end) {
      return null;
    }
    int errorAfter;
    for (errorAfter = start; errorAfter < end; errorAfter++) {
      if (_positions[errorAfter] == null) {
        return null;
      }
      if (_positions[errorAfter].getOffset() >= offset) {
        break;
      }
    }
    int errorBefore = errorAfter - 1;
    int shouldSelect = -1;
    if (errorBefore >= start) {
      int errPos = _positions[errorBefore].getOffset();
      try {
        String betweenDotAndErr = odd.getDocument().getText(errPos, offset - errPos);
        if (betweenDotAndErr.indexOf('\n') == (-1)) {
          shouldSelect = errorBefore;
        }
      } catch (BadLocationException willNeverHappen) {
        throw new UnexpectedException(willNeverHappen);
      }
    }
    if ((shouldSelect == (-1)) && (errorAfter < end)) {
      int errPos = _positions[errorAfter].getOffset();
      try {
        String betweenDotAndErr = odd.getDocument().getText(offset, errPos - offset);
        if (betweenDotAndErr.indexOf('\n') == (-1)) {
          shouldSelect = errorAfter;
        }
      } catch (BadLocationException willNeverHappen) {
        throw new UnexpectedException(willNeverHappen);
      }
    }
    if (shouldSelect == (-1)) {
      return null;
    } else {
      return _errors[shouldSelect];
    }
  }
}
