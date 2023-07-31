class PlaceHold {
  public void testConstructorLambdas() {
    Thunk<Object> t1 = constructorAsThunk(Object.class);
    assertNotNull(t1.value());
    Thunk<String> t2 = constructorAsThunk(String.class);
    assertEquals("", t2.value());
    Lambda<char[], String> l1 = constructorAsLambda(String.class, char[].class);
    assertEquals("abcd", l1.value(new char[] {'a', 'b', 'c', 'd'}));
    Lambda3<char[], Integer, Integer, String> l2 =
        constructorAsLambda3(String.class, char[].class, int.class, int.class);
    assertEquals("bc", l2.value(new char[] {'a', 'b', 'c', 'd'}, 1, 2));
    @SuppressWarnings("unchecked")
    Lambda<Object, SimpleBox> l3 = constructorAsLambda(SimpleBox.class, Object.class);
    assertEquals(23, l3.value(23).value());
    Thunk<Cloneable> t3 = constructorAsThunk(Cloneable.class);
    try {
      t3.value();
      fail("expected exception");
    } catch (WrappedException e) {
      assertCorrectException(e, "NoSuchMethod");
    }
    Thunk<Process> t4 = constructorAsThunk(Process.class);
    try {
      t4.value();
      fail("expected exception");
    } catch (WrappedException e) {
      assertCorrectException(e, "Instantiation");
    }
    Lambda<String, Integer> l4 = constructorAsLambda(Integer.class, String.class);
    assertEquals(144, l4.value("144"));
    try {
      l4.value("14fish");
      fail("expected exception");
    } catch (WrappedException e) {
      assertCorrectException(e, "InvocationTarget");
    }
  }
}
