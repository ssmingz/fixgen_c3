class PlaceHold {
  protected void _doSetCurrentThread(final DebugThreadData t) throws DebugException {
    Utilities.invokeLater(
        new Runnable() {
          public void run() {
            try {
              _debugger.setCurrentThread(t);
            } catch (DebugException e) {
              throw new UnexpectedException(e);
            }
          }
        });
  }
}
