class PlaceHold {
  void menuSaveAs() {
    if (image == null) {
      return;
    }
    animate = false;
    FileDialog fileChooser = new FileDialog(shell, SWT.SAVE);
    fileChooser.setFilterPath(lastPath);
    if (fileName != null) {
      String name = fileName;
      int nameStart = name.lastIndexOf(File.separatorChar);
      if (nameStart > (-1)) {
        name = name.substring(nameStart + 1);
      }
      fileChooser.setFileName(name);
    }
    fileChooser.setFilterExtensions(new String[] {"*.bmp", "*.gif", "*.ico", "*.jpg", "*.png"});
    fileChooser.setFilterNames(
        new String[] {"BMP (*.bmp)", "GIF (*.gif)", "ICO (*.ico)", "JPEG (*.jpg)", "PNG (*.png)"});
    String filename = fileChooser.open();
    lastPath = fileChooser.getFilterPath();
    if (filename == null) {
      return;
    }
    int filetype = determineFileType(filename);
    if (filetype == SWT.IMAGE_UNDEFINED) {
      MessageBox box = new MessageBox(shell, SWT.ICON_ERROR);
      box.setMessage(
          createMsg(
              bundle.getString("Unknown_extension"),
              filename.substring(filename.lastIndexOf('.') + 1)));
      box.open();
      return;
    }
    if (new File(filename).exists()) {
      MessageBox box = new MessageBox(shell, (SWT.ICON_QUESTION | SWT.OK) | SWT.CANCEL);
      box.setMessage(createMsg(bundle.getString("Overwrite"), filename));
      if (box.open() == SWT.CANCEL) {
        return;
      }
    }
    Cursor waitCursor = new Cursor(display, SWT.CURSOR_WAIT);
    shell.setCursor(waitCursor);
    imageCanvas.setCursor(waitCursor);
    try {
      loader.data = new ImageData[] {imageData};
      loader.save(filename, filetype);
      fileName = filename;
      shell.setText(createMsg(bundle.getString("Analyzer_on"), filename));
      typeLabel.setText(createMsg(bundle.getString("Type_string"), fileTypeString(filetype)));
    } catch (SWTException e) {
      showErrorDialog(bundle.getString("Saving_lc"), filename, e);
    } finally {
      shell.setCursor(null);
      imageCanvas.setCursor(crossCursor);
      waitCursor.dispose();
    }
  }
}
