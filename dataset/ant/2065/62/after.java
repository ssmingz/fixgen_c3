class PlaceHold {
  public void execute() throws BuildException {
    if ((file == null) && (filesets.size() == 0)) {
      throw new BuildException("Specify at least one source - a file or a fileset.");
    }
    if (((file != null) && file.exists()) && file.isDirectory()) {
      throw new BuildException("Use a fileset to touch directories.");
    }
    if (dateTime != null) {
      DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.US);
      try {
        setMillis(df.parse(dateTime).getTime());
        if (millis < 0) {
          throw new BuildException(
              ("Date of " + dateTime)
                  + " results in negative milliseconds value relative to epoch (January 1, 1970,"
                  + " 00:00:00 GMT).");
        }
      } catch (ParseException pe) {
        throw new BuildException(pe.getMessage(), pe);
      }
    }
    touch();
  }
}
