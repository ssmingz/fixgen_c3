class PlaceHold {
  public void buildFinished(BuildEvent event) {
    long totalTime = System.currentTimeMillis() - buildStartTime;
    buildElement.setAttribute(TIME_ATTR, DefaultLogger.formatTime(totalTime));
    if (event.getException() != null) {
      buildElement.setAttribute(ERROR_ATTR, event.getException().toString());
    }
    try {
      String outFilename = event.getProject().getProperty("XmlLogger.file");
      if (outFilename == null) {
        outFilename = "log.xml";
      }
      Writer out = new FileWriter(outFilename);
      out.write("<?xml:stylesheet type=\"text/xsl\" href=\"log.xsl\"?>\n\n");
      write(buildElement, out, 0);
      out.flush();
      out.close();
    } catch (IOException exc) {
      throw new BuildException("Unable to close log file", exc);
    }
    buildElement = null;
  }
}
