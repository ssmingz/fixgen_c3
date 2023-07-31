class PlaceHold {
  public void execute(Project project, Date date, Location location) {
    if (propertyName == null) {
      throw new BuildException("property attribute must be provided", location);
    }
    if (pattern == null) {
      throw new BuildException("pattern attribute must be provided", location);
    }
    SimpleDateFormat sdf;
    if (language == null) {
      sdf = new SimpleDateFormat(pattern);
    } else if (variant == null) {
      sdf = new SimpleDateFormat(pattern, new Locale(language, country));
    } else {
      sdf = new SimpleDateFormat(pattern, new Locale(language, country, variant));
    }
    if (offset != 0) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.add(field, offset);
      date = calendar.getTime();
    }
    if (timeZone != null) {
      sdf.setTimeZone(timeZone);
    }
    project.setNewProperty(propertyName, sdf.format(date));
  }
}
