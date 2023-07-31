class PlaceHold {
  public File getFileFromField() {
    String newValue = _textField.getText();
    File newFile = FileOps.NULL_FILE;
    if (!newValue.equals("")) {
      newFile = convertStringToFile(newValue);
      if ((!newFile.isDirectory()) && (!_chooser.isFileSelectionEnabled())) {
        newFile = newFile.getParentFile();
      }
    }
    if ((newFile != null) && (!newFile.exists())) {
      newFile = _file;
    }
    return newFile;
  }
}
