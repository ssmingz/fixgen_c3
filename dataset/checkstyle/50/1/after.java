class PlaceHold {
  public void fireFileStarted(String aFileName) {
    final String stripped = getStrippedFileName(aFileName);
    final AuditEvent evt = new AuditEvent(this, stripped);
    if (accept(evt)) {
      final Iterator it = mListeners.iterator();
      while (it.hasNext()) {
        final AuditListener listener = ((AuditListener) (it.next()));
        listener.fileStarted(evt);
      }
    }
  }
}
