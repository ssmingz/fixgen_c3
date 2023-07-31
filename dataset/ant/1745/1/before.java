class PlaceHold {
  public void execute() throws BuildException {
    try {
      Date d = new Date();
      Enumeration i = customFormats.elements();
      while (i.hasMoreElements()) {
        CustomFormat cts = ((CustomFormat) (i.nextElement()));
        cts.execute(getProject(), d, location);
      }
      SimpleDateFormat dstamp = new SimpleDateFormat("yyyyMMdd");
      getProject().setNewProperty(prefix + "DSTAMP", dstamp.format(d));
      SimpleDateFormat tstamp = new SimpleDateFormat("HHmm");
      getProject().setNewProperty(prefix + "TSTAMP", tstamp.format(d));
      SimpleDateFormat today = new SimpleDateFormat("MMMM d yyyy", Locale.US);
      getProject().setNewProperty(prefix + "TODAY", today.format(d));
    } catch (Exception e) {
      throw new BuildException(e);
    }
  }
}
