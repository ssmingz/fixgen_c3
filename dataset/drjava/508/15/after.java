class PlaceHold {
  protected void getDistToEnclosingBrace(IndentInfo braceInfo) {
    Stack<ReducedToken> braceStack = new Stack<ReducedToken>();
    TokenList.Iterator iter = _cursor.copy();
    resetLocation();
    int relDistance = braceInfo.distToNewline + 1;
    int distance = relDistance;
    if (braceInfo.distToNewline == (-1)) {
      iter.dispose();
      return;
    }
    int offset = _move((-braceInfo.distToNewline) - 1, iter, _offset);
    relDistance += offset;
    distance += offset;
    braceInfo.distToNewline = -1;
    if (iter.atStart() || iter.atFirstItem()) {
      iter.dispose();
      return;
    }
    iter.prev();
    while (!iter.atStart()) {
      distance += iter.current().getSize();
      relDistance += iter.current().getSize();
      if (!iter.current().isGap()) {
        if (stateAtRelLocation(-relDistance) == FREE) {
          if (iter.current().isOpenBrace()) {
            if (braceStack.isEmpty()) {
              braceInfo.braceType = iter.current().getType();
              braceInfo.distToBrace = distance;
              iter.dispose();
              return;
            }
            ReducedToken popped = braceStack.pop();
            if (!iter.current().isMatch(popped)) {
              iter.dispose();
              return;
            }
          } else {
            braceStack.push(iter.current());
          }
        }
        relDistance = 0;
      }
      iter.prev();
    }
    iter.dispose();
    return;
  }
}
