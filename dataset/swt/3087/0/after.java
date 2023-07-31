class PlaceHold {
  public String open() {
    String fullPath = null;
    fileNames = new String[0];
    NSSavePanel panel;
    if ((style & SWT.SAVE) != 0) {
      NSSavePanel savePanel = NSSavePanel.savePanel();
      panel = savePanel;
    } else {
      NSOpenPanel openPanel = NSOpenPanel.openPanel();
      openPanel.setAllowsMultipleSelection((style & SWT.MULTI) != 0);
      panel = openPanel;
    }
    if (filterPath != null) {
      panel.setDirectory(NSString.stringWith(filterPath));
    }
    panel.setTitle(NSString.stringWith(title != null ? title : ""));
    int response = panel.runModal();
    if (response == OS.NSFileHandlingPanelOKButton) {
      NSString filename = panel.filename();
      char[] buffer = new char[filename.length()];
      filename.getCharacters_(buffer);
      fullPath = new String(buffer);
      if ((style & SWT.SAVE) == 0) {
        NSArray filenames = ((NSOpenPanel) (panel)).filenames();
        int count = filenames.count();
        fileNames = new String[count];
        for (int i = 0; i < count; i++) {
          filename = new NSString(filenames.objectAtIndex(i));
          buffer = new char[filename.length()];
          filename.getCharacters_(buffer);
          fileNames[i] = new String(buffer);
        }
      }
      filterIndex = -1;
    }
    return fullPath;
  }
}
