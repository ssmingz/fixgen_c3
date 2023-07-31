class PlaceHold {
  public synchronized Vector<DebugStackData> getCurrentStackFrameData() throws DebugException {
    if (!isReady()) {
      return new Vector<DebugStackData>();
    }
    if ((_runningThread != null) || (_suspendedThreads.size() <= 0)) {
      throw new DebugException("No suspended thread to obtain stack frames.");
    }
    try {
      ThreadReference thread = _suspendedThreads.peek();
      Iterator<StackFrame> iter = thread.frames().iterator();
      Vector<DebugStackData> frames = new Vector<DebugStackData>();
      while (iter.hasNext()) {
        frames.add(new DebugStackData(iter.next()));
      }
      return frames;
    } catch (IncompatibleThreadStateException itse) {
      _log("Unable to obtain stack frame.", itse);
      return new Vector<DebugStackData>();
    } catch (VMDisconnectedException vmde) {
      _log("VMDisconnected when getting the current stack frame data.", vmde);
      return new Vector<DebugStackData>();
    }
  }
}
