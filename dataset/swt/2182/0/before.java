class PlaceHold {
  void addLast(RunnableLock lock) {
    synchronized (messageLock) {
      if (messages == null) {
        messages = new RunnableLock[4];
      }
      if (messageCount == messages.length) {
        RunnableLock[] newMessages = new RunnableLock[messageCount + 4];
        System.arraycopy(messages, 0, newMessages, 0, messageCount);
        messages = newMessages;
      }
      messages[messageCount++] = lock;
      if (messageCount == 1) {
        display.wake();
      }
    }
  }
}
