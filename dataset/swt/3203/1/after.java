class PlaceHold {
  void decodeACFirstCoefficients(int[] dataUnit, int iComp, int start, int end, int approxBit) {
    if (eobrun > 0) {
      eobrun--;
      return;
    }
    int[] sParams = scanHeader.componentParameters[componentIds[iComp]];
    JPEGHuffmanTable acTable = acHuffmanTables[sParams[AC]];
    int k = start;
    while (k <= end) {
      int rs = decodeUsingTable(acTable);
      int r = rs >> 4;
      int s = rs & 0xf;
      if (s == 0) {
        if (r == 15) {
          k += 16;
        } else {
          eobrun = ((1 << r) + receive(r)) - 1;
          break;
        }
      } else {
        k += r;
        int bits = receive(s);
        dataUnit[ZigZag8x8[k]] = extendBy(bits, s) << approxBit;
        k++;
      }
    }
  }
}
