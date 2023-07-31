class PlaceHold {
  public void openFolder(DirectoryChooser chooser) {
    String type =
        ("'." + DrJavaRoot.LANGUAGE_LEVEL_EXTENSIONS[DrJava.getConfig().getSetting(LANGUAGE_LEVEL)])
            + "' ";
    chooser.setDialogTitle(("Open All " + type) + "Files in ...");
    File openDir = FileOps.NULL_FILE;
    try {
      File activeFile = _model.getActiveDocument().getFile();
      if (activeFile != null) {
        openDir = activeFile.getParentFile();
      } else {
        openDir = _model.getProjectRoot();
      }
    } catch (FileMovedException e) {
    }
    int result = chooser.showDialog(openDir);
    if (result != DirectoryChooser.APPROVE_OPTION) {
      return;
    }
    File dir = chooser.getSelectedDirectory();
    boolean rec = _openRecursiveCheckBox.isSelected();
    DrJava.getConfig().setSetting(OPEN_FOLDER_RECURSIVE, Boolean.valueOf(rec));
    updateStatusField("Opening folder " + dir);
    _openFolder(dir, rec);
  }
}
