class PlaceHold {
  private void checkVariable(MyVariable aVar, RE aRegexp, String aPattern) {
    if (!aRegexp.match(aVar.getText())) {
      log(
          aVar.getLineNo(),
          aVar.getColumnNo() - 1,
          ((("variable '" + aVar.getText()) + "' must match pattern '") + aPattern) + "'.");
    }
  }
}
