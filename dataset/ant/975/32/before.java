class PlaceHold {
  public void setControlfile(File controlFile) {
    if (!controlFile.exists()) {
      getLogger()
          .info(
              ("WARNING: Control file " + controlFile.getAbsolutePath())
                  + " doesn't exist. iContract will be run without control file.");
    }
    this.controlFile = controlFile;
  }
}
