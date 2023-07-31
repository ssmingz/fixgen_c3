class PlaceHold {
  private void _handleThreadStartEvent(ThreadStartEvent e) {
    SwingUtilities.invokeLater(
        new Runnable() {
          public void run() {
            _debugger.threadStarted();
          }
        });
  }
}
