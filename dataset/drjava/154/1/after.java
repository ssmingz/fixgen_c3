class PlaceHold {
  protected void _createRequests(Vector<ReferenceType> refTypes) throws DebugException {
    try {
      for (int i = 0; i < refTypes.size(); i++) {
        ReferenceType rt = refTypes.get(i);
        if (!rt.isPrepared()) {
          continue;
        }
        List<Location> lines = rt.locationsOfLine(_manager.LLBreakpointLineNum(this));
        if (lines.size() == 0) {
          setEnabled(false);
          throw new DebugException("Could not find line number: " + _lineNumber);
        }
        Location loc = lines.get(0);
        BreakpointRequest request = _manager.getEventRequestManager().createBreakpointRequest(loc);
        request.setEnabled(_isEnabled);
        _requests.add(request);
      }
    } catch (AbsentInformationException aie) {
      throw new DebugException("Could not find line number: " + aie);
    }
  }
}
