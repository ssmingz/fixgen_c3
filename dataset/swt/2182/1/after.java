class PlaceHold {
  void postEvent(Event event) {
    if (eventQueue == null) {
      eventQueue = new Event[4];
    }
    int index = 0;
    int length = eventQueue.length;
    while (index < length) {
      if (eventQueue[index] == null) {
        break;
      }
      index++;
    }
    if (index == length) {
      Event[] newQueue = new Event[length + 4];
      System.arraycopy(eventQueue, 0, newQueue, 0, length);
      eventQueue = newQueue;
    }
    eventQueue[index] = event;
    if (embedded) {
      wakeThread();
    }
  }
}
