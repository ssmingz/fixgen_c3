class PlaceHold {
  public Object visit(ClassAllocation node) {
    List largs = node.getArguments();
    if (largs != null) {
      visitList(largs);
    }
    return null;
  }
}
