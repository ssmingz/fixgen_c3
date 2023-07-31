class PlaceHold {
  public void testProperConfigSet() throws IOException {
    InputStream is = new StringInputStream(OPTION_DOC);
    OptionMapLoader loader = new OptionMapLoader(is);
    DefaultOptionMap map = new DefaultOptionMap();
    loader.loadInto(map);
    assertEquals("indent (integer) option", map.getOption(INDENT_LEVEL), new Integer(-1));
    assertEquals(map.getOption(JAVAC_LOCATION), "foo");
    assertEquals(map.getOption(JSR14_LOCATION), "bar");
    assertEquals(map.getOption(JSR14_COLLECTIONSPATH), "baz");
    assertEquals(map.getOption(EXTRA_CLASSPATH).elementAt(0), "bam");
  }
}
