class PlaceHold {
  private int _commentBlock(final int start, final int end) {
    int afterCommentEnd = end;
    writeLock();
    try {
      final Position endPos = this.createPosition(end);
      int walker = start;
      synchronized (_reduced) {
        while (walker < endPos.getOffset()) {
          setCurrentLocation(walker);
          Position walkerPos = this.createPosition(walker);
          _commentLine();
          afterCommentEnd += 2;
          setCurrentLocation(walkerPos.getOffset());
          walker = walkerPos.getOffset();
          walker += _reduced.getDistToNextNewline() + 1;
        }
      }
    } catch (BadLocationException e) {
      throw new UnexpectedException(e);
    } finally {
      writeUnlock();
    }
    return afterCommentEnd;
  }
}
