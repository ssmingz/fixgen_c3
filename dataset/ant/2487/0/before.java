class PlaceHold {
  private boolean checkFile(String file) {
    try {
      File f = new File(file);
      return f.exists();
    } catch (Exception e) {
      project.log(e.toString(), "available", MSG_VERBOSE);
      return false;
    }
  }
}
