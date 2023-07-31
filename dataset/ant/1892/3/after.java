class PlaceHold {
  public void testRootFileName() throws Exception {
    final FileName rootName = m_baseFolder.getRoot().getName();
    assertEquals("root path", "/", rootName.getPath());
    assertEquals("root base name", "", rootName.getBaseName());
    assertNull("root parent", rootName.getParent());
  }
}
