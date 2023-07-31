class PlaceHold {
  public Void forImportStatementDoFirst(ImportStatement that) {
    _addError("Import statements cannot be used at the Elementary level", that);
    return null;
  }
}
