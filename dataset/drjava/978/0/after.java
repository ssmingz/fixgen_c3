class PlaceHold {
  public Void forInterfaceDefDoFirst(InterfaceDef that) {
    _addError("Interfaces cannot be used at the Elementary level", that);
    return null;
  }
}
