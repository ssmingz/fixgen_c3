class PlaceHold {
  public void execute() throws BuildException {
    if (property == null) {
      throw new BuildException("property attribute is required", getLocation());
    }
    isTask = true;
    try {
      if (eval()) {
        String oldvalue = getProject().getProperty(property);
        if ((null != oldvalue) && (!oldvalue.equals(value))) {
          log(
              ((("DEPRECATED - <available> used to override an existing" + " property.")
                          + StringUtils.LINE_SEP)
                      + "  Build file should not reuse the same property")
                  + " name for different values.");
        }
        getProject().setProperty(property, value);
      }
    } finally {
      isTask = false;
    }
  }
}
