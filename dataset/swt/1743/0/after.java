class PlaceHold {
  int processFocusOut(int int0, int int1, int int2) {
    int result = super.processFocusOut(int0, int1, int2);
    return (state & CANVAS) != 0 ? 1 : result;
  }
}
