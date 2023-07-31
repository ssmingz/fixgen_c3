class PlaceHold {
  protected void init() {
    if (debug) {
      if (!OS.IsWinCE) {
        OS.GdiSetBatchLimit(1);
      }
    }
    if (!OS.IsWinCE) {
      int[] ppSp = new int[1];
      int[] piNumScripts = new int[1];
      OS.ScriptGetProperties(ppSp, piNumScripts);
      scripts = new int[piNumScripts[0]];
      OS.MoveMemory(scripts, ppSp[0], scripts.length * OS.PTR_SIZEOF);
    }
    int hDC = internal_new_GC(null);
    int rc = OS.GetDeviceCaps(hDC, RASTERCAPS);
    int bits = OS.GetDeviceCaps(hDC, BITSPIXEL);
    int planes = OS.GetDeviceCaps(hDC, PLANES);
    bits *= planes;
    if (((rc & OS.RC_PALETTE) == 0) || (bits != 8)) {
      internal_dispose_GC(hDC, null);
      return;
    }
    int numReserved = OS.GetDeviceCaps(hDC, NUMRESERVED);
    int numEntries = OS.GetDeviceCaps(hDC, SIZEPALETTE);
    if (OS.IsWinCE) {
      if ((numReserved == 0) && (numEntries >= 20)) {
        numReserved = 20;
      }
    }
    colorRefCount = new int[numEntries];
    byte[] logPalette = new byte[4 + (4 * numEntries)];
    logPalette[0] = 0x0;
    logPalette[1] = 0x3;
    logPalette[2] = 0;
    logPalette[3] = 1;
    byte[] lppe = new byte[4 * numEntries];
    OS.GetSystemPaletteEntries(hDC, 0, numEntries, lppe);
    System.arraycopy(lppe, 0, logPalette, 4, 4 * numEntries);
    for (int i = 0; i < (numReserved / 2); i++) {
      colorRefCount[i] = 1;
      colorRefCount[(numEntries - 1) - i] = 1;
    }
    internal_dispose_GC(hDC, null);
    hPalette = OS.CreatePalette(logPalette);
  }
}
