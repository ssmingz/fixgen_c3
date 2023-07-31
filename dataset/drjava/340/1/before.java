class PlaceHold {
  public void testRemainderAssign() throws ExceptionReturnedException {
    String text = "X=7; X %= 5";
    Object res = _interpreter.interpret(text);
    assertEquals("X should have the Integer value 2", "2", res.toString());
    res = _interpreter.interpret("X");
    assertTrue("X should have been an Integer", res instanceof Integer);
    assertEquals("X should have the Integer value 2", "2", res.toString());
  }
}
