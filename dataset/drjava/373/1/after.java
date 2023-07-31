class PlaceHold {
  public void testTypeParameterGetName() {
    ReferenceType r = new ReferenceType("Integer");
    TypeParameter t = new TypeParameter(new SourceInfo(), "T", r, new LinkedList<ReferenceType>());
    assertEquals("Integer", t.getRepresentation());
    assertEquals("T", t.getName());
  }
}
