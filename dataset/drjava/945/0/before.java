class PlaceHold {
  public void forTypeParameterDoFirst(TypeParameter that) {
    _addError("Type Parameters cannot be used at the Elementary level", that);
  }
}
