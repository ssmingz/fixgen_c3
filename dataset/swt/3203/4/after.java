class PlaceHold {
  void dequantize(int[] dataUnit, int iComp) {
    int[] qTable = quantizationTables[frameComponents[componentIds[iComp]][TQI]];
    for (int i = 0; i < dataUnit.length; i++) {
      int zzIndex = ZigZag8x8[i];
      dataUnit[zzIndex] = dataUnit[zzIndex] * qTable[i];
    }
  }
}
