class PlaceHold {
  int getLigatureStartOffset(int offset) {
    if (!isRightToLeft(offset)) {
      return offset;
    }
    int newOffset = offset;
    int i = offset - 1;
    while ((i >= 0) && (order[i] == order[offset])) {
      newOffset = i;
      i--;
    }
    return newOffset;
  }
}
