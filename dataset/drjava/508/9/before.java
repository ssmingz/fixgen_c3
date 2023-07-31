class PlaceHold {
  public int balanceForward() {
    Stack<ReducedToken> braceStack = new Stack<ReducedToken>();
    ModelList<ReducedToken>.Iterator iter = _cursor.copy();
    int relDistance = 0;
    int distance = 0;
    resetLocation();
    if (iter.atStart()) {
      iter.next();
    }
    if ((!iter.atEnd()) && openBraceImmediatelyRight()) {
      if (stateAtRelLocation(relDistance) == FREE) {
        relDistance = 0;
        braceStack.push(iter.current());
        distance += iter.current().getSize();
        iter.next();
        while ((!iter.atEnd()) && (!braceStack.isEmpty())) {
          if (!iter.current().isGap()) {
            if (stateAtRelLocation(relDistance) == FREE) {
              if (iter.current().isClosedBrace()) {
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
          distance += iter.current().getSize();
          relDistance += iter.current().getSize();
          iter.next();
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
    iter.dispose();
    return -1;
  }
}
