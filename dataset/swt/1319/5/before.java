class PlaceHold {
  public void test_hashCode() {
    FontData fd1 = new FontData(SwtJunit.testFontName, 10, SWT.NORMAL);
    FontData fd2 = new FontData(SwtJunit.testFontName, 10, SWT.NORMAL);
    assertEquals(fd1, fd2);
    assertEquals(fd1.hashCode(), fd2.hashCode());
    FontData fd3 = new FontData(SwtJunit.testFontName, 10, SWT.BOLD);
    assertFalse(fd1.hashCode() == fd3.hashCode());
  }
}
