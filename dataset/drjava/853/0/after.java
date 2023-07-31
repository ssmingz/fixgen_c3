class PlaceHold {
  public void preparePrintJob() throws BadLocationException, FileMovedException {
    String fileName = "(Untitled)";
    File sourceFile = getFile();
    if (!AbstractGlobalModel.isUntitled(sourceFile)) {
      fileName = sourceFile.getAbsolutePath();
    }
    _book = new DrJavaBook(getDocument().getText(), fileName, _pageFormat);
  }
}
