class PlaceHold {
  int getLigatureStartOffset(int offset) {
    int newOffset = offset;
    int i = offset - 1;
    if (((offset < 0) || (offset >= order.length)) || (isRightToLeft(offset) == false)) {
      return offset;
    }
    while ((i >= 0) && (order[i] == order[offset])) {
      newOffset = i;
      i--;
    }
    return newOffset;
  }
}
