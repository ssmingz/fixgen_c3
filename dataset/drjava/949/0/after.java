class PlaceHold {
  public void testCloseAllClosesProject() throws MalformedProjectFileException, IOException {
    _model.openProject(_projFile);
    assertTrue("Project should have been opened", _model.isProjectActive());
    _frame.closeAll();
    assertFalse("Project should have been closed", _model.isProjectActive());
  }
}
