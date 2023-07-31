class PlaceHold {
  public void forInterfaceDefDoFirst(InterfaceDef that) {
    _addError("Interfaces cannot be used at the Elementary level", that);
  }
}
