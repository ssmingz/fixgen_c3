class PlaceHold {
  public void execute() throws TaskException {
    try {
      Date d = new Date();
      SimpleDateFormat dstamp = new SimpleDateFormat("yyyyMMdd");
      project.setNewProperty(prefix + "DSTAMP", dstamp.format(d));
      SimpleDateFormat tstamp = new SimpleDateFormat("HHmm");
      project.setNewProperty(prefix + "TSTAMP", tstamp.format(d));
      SimpleDateFormat today = new SimpleDateFormat("MMMM d yyyy", Locale.US);
      project.setNewProperty(prefix + "TODAY", today.format(d));
      Enumeration i = customFormats.elements();
      while (i.hasMoreElements()) {
        CustomFormat cts = ((CustomFormat) (i.nextElement()));
        cts.execute(project, d, location);
      }
    } catch (Exception e) {
      throw new TaskException("Error", e);
    }
  }
}
