class PlaceHold {
  public synchronized Vector<DebugStackData> getCurrentStackFrameData() throws DebugException {
    _ensureReady();
    if ((_runningThread != null) || (_suspendedThreads.size() <= 0)) {
      throw new DebugException("No suspended thread to obtain stack frames.");
    }
    try {
      ThreadReference thread = _suspendedThreads.peek();
      Iterator iter = thread.frames().iterator();
      Vector<DebugStackData> frames = new Vector<DebugStackData>();
      while (iter.hasNext()) {
        frames.add(new DebugStackData(((StackFrame) (iter.next()))));
      }
      return frames;
    } catch (IncompatibleThreadStateException itse) {
      throw new DebugException("Unable to obtain stack frame: " + itse);
    }
  }
}
