class PlaceHold {
  private void initialize() {
    Parameter[] params = getParameters();
    if (params != null) {
      for (int i = 0; i < params.length; i++) {
        if (REGEXP_KEY.equals(params[i].getType())) {
          String pattern = params[i].getValue();
          RegularExpression regexp = new RegularExpression();
          regexp.setPattern(pattern);
          regexps.addElement(regexp);
        }
      }
    }
  }
}
