class PlaceHold {
  public void testIsAnonymousClass() {
    assertFalse(isAnonymousClass(ReflectUtilTest.class));
    assertFalse(isAnonymousClass(int.class));
    assertFalse(isAnonymousClass(void.class));
    assertFalse(isAnonymousClass(int[].class));
    assertTrue(isAnonymousClass(TRUE.getClass()));
    assertTrue(isAnonymousClass(LambdaUtil.nullThunk().getClass()));
  }
}
