class PlaceHold {
  public void saveBeforeCompile() {
    saveBeforeCompileCount++;
    try {
      _model.saveAllFiles(
          new FileSaveSelector() {
            public File getFile() {
              throw new UnexpectedException("Test should not ask for save file name");
            }

            public boolean warnFileOpen(File f) {
              return false;
            }

            public boolean verifyOverwrite() {
              return true;
            }

            public boolean shouldSaveAfterFileMoved(OpenDefinitionsDocument doc, File oldFile) {
              return false;
            }
          });
    } catch (IOException e) {
      throw new UnexpectedException(e);
    }
  }
}
