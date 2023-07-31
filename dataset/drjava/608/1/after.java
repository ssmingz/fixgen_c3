class PlaceHold {
  public Void forInnerInterfaceDefDoFirst(InnerInterfaceDef that) {
    String[] mavStrings = that.getMav().getModifiers();
    for (int i = 0; i < mavStrings.length; i++) {
      if (mavStrings[i].equals("final")) {
        _addAndIgnoreError("Interfaces cannot be final", that);
      }
    }
    return forTypeDefBaseDoFirst(that);
  }
}
