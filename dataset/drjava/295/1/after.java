class PlaceHold {
  public void javadocEnded(final boolean success, final File destDir, final boolean allDocs) {
    assert EventQueue.isDispatchThread();
    try {
      showTab(_javadocErrorPanel, true);
      _javadocAllAction.setEnabled(true);
      _javadocCurrentAction.setEnabled(true);
      _javadocErrorPanel.reset();
      _model.refreshActiveDocument();
    } finally {
      hourglassOff();
    }
    if (success) {
      String className;
      try {
        className = _model.getActiveDocument().getQualifiedClassName();
        className = className.replace('.', File.separatorChar);
      } catch (ClassNameNotFoundException cnf) {
        className = "";
      }
      try {
        String fileName = (allDocs || className.equals("")) ? "index.html" : className + ".html";
        File index = new File(destDir, fileName);
        URL address = FileOps.toURL(index.getAbsoluteFile());
        if (!ONLY.openURL(address)) {
          JavadocFrame _javadocFrame = new JavadocFrame(destDir, className, allDocs);
          _javadocFrame.setVisible(true);
        }
      } catch (MalformedURLException me) {
        throw new UnexpectedException(me);
      } catch (IllegalStateException ise) {
        String msg =
            "Javadoc completed successfully, but did not produce any HTML files.\n"
                + "Please ensure that your access level in Preferences is appropriate.";
        JOptionPane.showMessageDialog(
            this, msg, "No output to display.", JOptionPane.INFORMATION_MESSAGE);
      }
    }
  }
}
