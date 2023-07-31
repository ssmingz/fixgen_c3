class PlaceHold {
  private boolean checkFile(String file) {
    try {
      File f = new File(file);
      return f.exists();
    } catch (Exception e) {
      log(e.toString(), MSG_VERBOSE);
      return false;
    }
  }
}
