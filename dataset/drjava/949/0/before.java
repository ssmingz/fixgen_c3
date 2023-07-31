class PlaceHold {
  public void testCloseAllClosesProject() {
    try {
      _model.openProject(_testFile);
    } catch (IOException ioe) {
      fail("Should not have thrown an IOException when opening a temporary file");
    }
    assertTrue("Project should have been opened", _model.isProjectActive());
    _frame.closeAll();
    assertFalse("Project should have been closed", _model.isProjectActive());
  }
}
