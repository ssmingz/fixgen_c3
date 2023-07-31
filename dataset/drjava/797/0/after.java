class PlaceHold {
  synchronized void scrollToSource(LocatableEvent e) {
    Location location = e.location();
    OpenDefinitionsDocument doc = null;
    EventRequest request = e.request();
    Object docProp = request.getProperty("document");
    if ((docProp != null) && (docProp instanceof OpenDefinitionsDocument)) {
      doc = ((OpenDefinitionsDocument) (docProp));
    } else {
      ReferenceType rt = location.declaringType();
      String className = rt.name();
      String ps = System.getProperty("file.separator");
      className = StringOps.replace(className, ".", ps);
      int indexOfDollar = className.indexOf('$');
      if (indexOfDollar > (-1)) {
        className = className.substring(0, indexOfDollar);
      }
      String filename = className + ".java";
      File[] sourceRoots = _model.getSourceRootSet();
      Vector<File> roots = new Vector<File>();
      for (int i = 0; i < sourceRoots.length; i++) {
        roots.addElement(sourceRoots[i]);
      }
      File f = _model.getSourceFileFromPaths(filename, roots);
      if (f == null) {
        Vector<File> sourcepath = DrJava.getConfig().getSetting(DEBUG_SOURCEPATH);
        f = _model.getSourceFileFromPaths(filename, sourcepath);
      }
      if (f != null) {
        try {
          doc = _model.getDocumentForFile(f);
        } catch (IOException ioe) {
        } catch (OperationCanceledException oce) {
        }
      }
    }
    if (doc != null) {
      doc.checkIfClassFileInSync();
      final OpenDefinitionsDocument docF = doc;
      final Location locationF = location;
      notifyListeners(
          new EventNotifier() {
            public void notifyListener(DebugListener l) {
              l.threadLocationUpdated(docF, locationF.lineNumber());
            }
          });
    } else {
      String className = location.declaringType().name();
      printMessage(("  (Source for " + className) + " not found.)");
    }
  }
}
