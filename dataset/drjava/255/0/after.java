class PlaceHold {
  public void testIncrement() throws InterpreterException {
    String text = "X++";
    Object res = interpret(text);
    assertEquals("X should have the Integer value 0", "0", res.toString());
    res = interpret("X");
    assertTrue("X should have been an Integer", res instanceof Integer);
    assertEquals("X should have the Integer value 1", "1", res.toString());
    text = "++X";
    res = interpret(text);
    assertEquals("X should have the Integer value 2", "2", res.toString());
    res = interpret("X");
    assertTrue("X should have been an Integer", res instanceof Integer);
    assertEquals("X should have the Integer value 2", "2", res.toString());
  }
}
