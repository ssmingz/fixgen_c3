class PlaceHold {
  public void forInterfaceDefDoFirst(InterfaceDef that) {
    String[] mavStrings = that.getMav().getModifiers();
    for (int i = 0; i < mavStrings.length; i++) {
      if (mavStrings[i].equals("private")) {
        _addAndIgnoreError("Top level interfaces cannot be private", that);
      }
      if (mavStrings[i].equals("final")) {
        _addAndIgnoreError("Interfaces cannot be final", that);
      }
    }
    forTypeDefBaseDoFirst(that);
  }
}
