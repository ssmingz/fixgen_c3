class PlaceHold {
  private void initialize() {
    Parameter[] params = getParameters();
    if (params != null) {
      for (int i = 0; i < params.length; i++) {
        if (CONTAINS_KEY.equals(params[i].getType())) {
          contains.addElement(params[i].getValue());
        } else if (NEGATE_KEY.equals(params[i].getType())) {
          setNegate(Project.toBoolean(params[i].getValue()));
        }
      }
    }
  }
}
