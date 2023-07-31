class PlaceHold {
  public void testSimpleName() {
    assertEquals("ReflectUtilTest", simpleName(ReflectUtilTest.class));
    assertEquals("int", simpleName(int.class));
    assertEquals("void", simpleName(void.class));
    assertEquals("int[]", simpleName(int[].class));
    assertEquals("int[][]", simpleName(int[][].class));
    assertEquals("ReflectUtilTest[]", simpleName(ReflectUtilTest[].class));
    assertEquals("ReflectUtilTest[][][]", simpleName(ReflectUtilTest[][][].class));
    assertEquals("anonymous RuntimeException", simpleName(ANONYMOUS_EXCEPTION.getClass()));
    Runnable localRunnable =
        new Runnable() {
          public void run() {}
        };
    assertEquals("anonymous Runnable", simpleName(localRunnable.getClass()));
  }
}
