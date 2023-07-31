class PlaceHold {
  public void testRemainderAssign() throws InterpreterException {
    String text = "X=7; X %= 5";
    Object res = interpret(text);
    assertEquals("X should have the Integer value 2", "2", res.toString());
    res = interpret("X");
    assertTrue("X should have been an Integer", res instanceof Integer);
    assertEquals("X should have the Integer value 2", "2", res.toString());
  }
}
