class PlaceHold {
  public void testIsAnonymousClass() {
    assertFalse(isAnonymousClass(ReflectUtilTest.class));
    assertFalse(isAnonymousClass(int.class));
    assertFalse(isAnonymousClass(void.class));
    assertFalse(isAnonymousClass(int[].class));
    assertTrue(isAnonymousClass(ANONYMOUS_EXCEPTION.getClass()));
    Runnable localRunnable =
        new Runnable() {
          public void run() {}
        };
    assertTrue(isAnonymousClass(localRunnable.getClass()));
  }
}
