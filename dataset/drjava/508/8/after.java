class PlaceHold {
  public int balanceBackward() {
    Stack<ReducedToken> braceStack = new Stack<ReducedToken>();
    TokenList.Iterator iter = _cursor.copy();
    resetLocation();
    int relDistance = 0;
    int distance = 0;
    if ((iter.atStart() || iter.atFirstItem()) || (!closedBraceImmediatelyLeft())) {
      iter.dispose();
      return -1;
    }
    iter.prev();
    relDistance = iter.current().getSize();
    if (iter.current().isClosedBrace()) {
      if (stateAtRelLocation(-relDistance) == FREE) {
        braceStack.push(iter.current());
        distance += iter.current().getSize();
        iter.prev();
        if (!iter.atStart()) {
          distance += iter.current().getSize();
          relDistance = iter.current().getSize();
        }
      } else {
        iter.dispose();
        return -1;
      }
    } else {
      iter.dispose();
      return -1;
    }
    while ((!iter.atStart()) && (!braceStack.isEmpty())) {
      if (!iter.current().isGap()) {
        if (stateAtRelLocation(-relDistance) == FREE) {
          if (iter.current().isOpenBrace()) {
            ReducedToken popped = braceStack.pop();
            if (!iter.current().isMatch(popped)) {
              iter.dispose();
              return -1;
            }
          } else {
            braceStack.push(iter.current());
          }
        }
        relDistance = 0;
      }
      iter.prev();
      if ((!iter.atStart()) && (!braceStack.isEmpty())) {
        distance += iter.current().getSize();
        relDistance += iter.current().getSize();
      }
    }
    if (!braceStack.isEmpty()) {
      iter.dispose();
      return -1;
    } else {
      iter.dispose();
      return distance;
    }
  }
}
