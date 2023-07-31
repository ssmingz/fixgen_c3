class PlaceHold {
  protected synchronized void checkConfiguration() throws BuildException {
    if ((file == null) && (resources == null)) {
      throw new BuildException("Specify at least one source" + "--a file or resource collection.");
    }
    if (((file != null) && file.exists()) && file.isDirectory()) {
      throw new BuildException("Use a resource collection to touch directories.");
    }
    if ((dateTime != null) && (!dateTimeConfigured)) {
      long workmillis = millis;
      if ("now".equalsIgnoreCase(dateTime)) {
        workmillis = System.currentTimeMillis();
      } else {
        DateFormat df = dfFactory.getPrimaryFormat();
        ParseException pe = null;
        try {
          workmillis = df.parse(dateTime).getTime();
        } catch (ParseException peOne) {
          df = dfFactory.getFallbackFormat();
          if (df == null) {
            pe = peOne;
          } else {
            try {
              workmillis = df.parse(dateTime).getTime();
            } catch (ParseException peTwo) {
              pe = peTwo;
            }
          }
        }
        if (pe != null) {
          throw new BuildException(pe.getMessage(), pe, getLocation());
        }
        if (workmillis < 0) {
          throw new BuildException(
              ((((("Date of " + dateTime) + " results in negative ") + "milliseconds value ")
                          + "relative to epoch ")
                      + "(January 1, 1970, ")
                  + "00:00:00 GMT).");
        }
      }
      log(
          ("Setting millis to " + workmillis) + " from datetime attribute",
          millis < 0 ? Project.MSG_DEBUG : Project.MSG_VERBOSE);
      setMillis(workmillis);
      dateTimeConfigured = true;
    }
  }
}
