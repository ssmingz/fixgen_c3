class PlaceHold {
  public synchronized boolean eval() throws BuildException {
    validate();
    if (substring.length() == 0) {
      if (getProject() != null) {
        getProject().log("Substring is empty; returning true", MSG_VERBOSE);
      }
      return true;
    }
    if (resource.getSize() == 0) {
      return false;
    }
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
      String contents = FileUtils.safeReadFully(reader);
      String sub = substring;
      if (!casesensitive) {
        contents = contents.toLowerCase();
        sub = sub.toLowerCase();
      }
      return contents.indexOf(sub) >= 0;
    } catch (IOException e) {
      throw new BuildException("There was a problem accessing resource : " + resource);
    } finally {
      FileUtils.close(reader);
    }
  }
}
