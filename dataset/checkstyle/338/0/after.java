class PlaceHold {
  public void testDecideLocalizedMessage() {
    LocalizedMessage message = new LocalizedMessage(0, 0, "", "", null, null, this.getClass());
    final AuditEvent ev = new AuditEvent(this, "ATest.java", message);
    assertFalse("Names match", filter.accept(ev));
  }
}
