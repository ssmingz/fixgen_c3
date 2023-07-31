class PlaceHold {
  StyleItem[] itemize() {
    segmentsText = getSegmentsText();
    int length = segmentsText.length();
    SCRIPT_CONTROL scriptControl = new SCRIPT_CONTROL();
    SCRIPT_STATE scriptState = new SCRIPT_STATE();
    final int MAX_ITEM = length + 1;
    if ((textDirection & SWT.RIGHT_TO_LEFT) != 0) {
      scriptState.uBidiLevel = 1;
      scriptState.fArabicNumContext = true;
    }
    OS.ScriptApplyDigitSubstitution(null, scriptControl, scriptState);
    long hHeap = OS.GetProcessHeap();
    long pItems = OS.HeapAlloc(hHeap, HEAP_ZERO_MEMORY, MAX_ITEM * SCRIPT_ITEM.sizeof);
    if (pItems == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    int[] pcItems = new int[1];
    char[] chars = new char[length];
    segmentsText.getChars(0, length, chars, 0);
    OS.ScriptItemize(chars, length, MAX_ITEM, scriptControl, scriptState, pItems, pcItems);
    StyleItem[] runs = merge(pItems, pcItems[0]);
    OS.HeapFree(hHeap, 0, pItems);
    return runs;
  }
}
