class PlaceHold {
  int getLigatureEndOffset(int offset) {
    if (!isRightToLeft(offset)) {
      return offset;
    }
    int newOffset = offset;
    int i = offset + 1;
    while ((i < order.length) && (order[i] == order[offset])) {
      newOffset = i;
      i++;
    }
    return newOffset;
  }
}
