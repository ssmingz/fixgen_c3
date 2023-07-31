class PlaceHold {
  void open(FileOpenSelector openSelector) {
    try {
      hourglassOn();
      _model.openFiles(openSelector);
    } catch (AlreadyOpenException aoe) {
      OpenDefinitionsDocument openDoc = aoe.getOpenDocument();
      String filename = "File";
      try {
        filename = openDoc.getFile().getName();
      } catch (IllegalStateException ise) {
        throw new UnexpectedException(ise);
      } catch (FileMovedException fme) {
        filename = fme.getFile().getName();
      }
      _model.setActiveDocument(openDoc);
      if (openDoc.isModifiedSinceSave()) {
        String title = "Revert to Saved?";
        String message =
            (filename + " is already open and modified.\n")
                + "Would you like to revert to the version on disk?\n";
        int choice = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
          _revert();
        }
      }
      try {
        _recentFileManager.updateOpenFiles(openDoc.getFile());
      } catch (IllegalStateException ise) {
        throw new UnexpectedException(ise);
      } catch (FileMovedException fme) {
        _recentFileManager.updateOpenFiles(fme.getFile());
      }
    } catch (OperationCanceledException oce) {
    } catch (FileNotFoundException fnf) {
      _showFileNotFoundError(fnf);
    } catch (IOException ioe) {
      _showIOError(ioe);
    } finally {
      hourglassOff();
    }
  }
}
