class PlaceHold {
  public int getPosition() {
    if (realPos == (-1)) {
      realPos = (executable == null) ? 0 : 1;
      for (int i = 0; i < position; i++) {
        Argument arg = ((Argument) (arguments.elementAt(i)));
        realPos += arg.getParts().length;
      }
    }
    return realPos;
  }
}
