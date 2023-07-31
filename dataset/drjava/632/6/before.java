class PlaceHold {
  public void forImportStatementDoFirst(ImportStatement that) {
    _addError("Import statements cannot be used at the Elementary level", that);
  }
}
