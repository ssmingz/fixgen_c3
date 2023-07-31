class PlaceHold {
  public void setParameters(Parameter[] parameters) {
    super.setParameters(parameters);
    if (parameters != null) {
      for (int i = 0; i < parameters.length; i++) {
        String paramname = parameters[i].getName();
        if (NAME_KEY.equalsIgnoreCase(paramname)) {
          setName(parameters[i].getValue());
        } else if (CASE_KEY.equalsIgnoreCase(paramname)) {
          setCasesensitive(Project.toBoolean(parameters[i].getValue()));
        } else if (NEGATE_KEY.equalsIgnoreCase(paramname)) {
          setNegate(Project.toBoolean(parameters[i].getValue()));
        } else if (REGEX_KEY.equalsIgnoreCase(paramname)) {
          setRegex(parameters[i].getValue());
        } else {
          setError("Invalid parameter " + paramname);
        }
      }
    }
  }
}
