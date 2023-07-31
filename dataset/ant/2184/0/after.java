class PlaceHold {
  public void setParameters(Parameter[] parameters) {
    super.setParameters(parameters);
    if (parameters != null) {
      for (int i = 0; i < parameters.length; i++) {
        String paramname = parameters[i].getName();
        if (MILLIS_KEY.equalsIgnoreCase(paramname)) {
          try {
            setMillis(new Long(parameters[i].getValue()).longValue());
          } catch (NumberFormatException nfe) {
            setError("Invalid millisecond setting " + parameters[i].getValue());
          }
        } else if (DATETIME_KEY.equalsIgnoreCase(paramname)) {
          setDatetime(parameters[i].getValue());
        } else if (CHECKDIRS_KEY.equalsIgnoreCase(paramname)) {
          setCheckdirs(Project.toBoolean(parameters[i].getValue()));
        } else if (GRANULARITY_KEY.equalsIgnoreCase(paramname)) {
          try {
            setGranularity(new Integer(parameters[i].getValue()).intValue());
          } catch (NumberFormatException nfe) {
            setError("Invalid granularity setting " + parameters[i].getValue());
          }
        } else if (WHEN_KEY.equalsIgnoreCase(paramname)) {
          TimeComparisons cmp = new TimeComparisons();
          cmp.setValue(parameters[i].getValue());
          setWhen(cmp);
        } else if (PATTERN_KEY.equalsIgnoreCase(paramname)) {
          setPattern(parameters[i].getValue());
        } else {
          setError("Invalid parameter " + paramname);
        }
      }
    }
  }
}
