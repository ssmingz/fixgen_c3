class PlaceHold {
  private boolean generateChecksums() throws BuildException {
    boolean checksumMatches = true;
    FileInputStream fis = null;
    FileOutputStream fos = null;
    byte[] buf = new byte[readBufferSize];
    try {
      for (Enumeration e = includeFileMap.keys(); e.hasMoreElements(); ) {
        messageDigest.reset();
        File src = ((File) (e.nextElement()));
        if (!isCondition) {
          log((("Calculating " + algorithm) + " checksum for ") + src, MSG_VERBOSE);
        }
        fis = new FileInputStream(src);
        DigestInputStream dis = new DigestInputStream(fis, messageDigest);
        while (dis.read(buf, 0, readBufferSize) != (-1)) {}
        dis.close();
        fis.close();
        fis = null;
        byte[] fileDigest = messageDigest.digest();
        if (totalproperty != null) {
          allDigests.put(src, fileDigest);
        }
        String checksum = createDigestString(fileDigest);
        Object destination = includeFileMap.get(src);
        if (destination instanceof String) {
          String prop = ((String) (destination));
          if (isCondition) {
            checksumMatches = checksumMatches && checksum.equals(property);
          } else {
            getProject().setNewProperty(prop, checksum);
          }
        } else if (destination instanceof File) {
          if (isCondition) {
            File existingFile = ((File) (destination));
            if (existingFile.exists()) {
              fis = new FileInputStream(existingFile);
              InputStreamReader isr = new InputStreamReader(fis);
              BufferedReader br = new BufferedReader(isr);
              String suppliedChecksum = br.readLine();
              fis.close();
              fis = null;
              br.close();
              isr.close();
              checksumMatches = checksumMatches && checksum.equals(suppliedChecksum);
            } else {
              checksumMatches = false;
            }
          } else {
            File dest = ((File) (destination));
            fos = new FileOutputStream(dest);
            fos.write(checksum.getBytes());
            fos.close();
            fos = null;
          }
        }
      }
      if (totalproperty != null) {
        Set keys = allDigests.keySet();
        Object[] keyArray = keys.toArray();
        Arrays.sort(keyArray);
        messageDigest.reset();
        for (int i = 0; i < keyArray.length; i++) {
          File src = ((File) (keyArray[i]));
          byte[] digest = ((byte[]) (allDigests.get(src)));
          messageDigest.update(digest);
          String fileName = ((String) (relativeFilePaths.get(src)));
          messageDigest.update(fileName.getBytes());
        }
        String totalChecksum = createDigestString(messageDigest.digest());
        getProject().setNewProperty(totalproperty, totalChecksum);
      }
    } catch (Exception e) {
      throw new BuildException(e, getLocation());
    } finally {
      if (fis != null) {
        try {
          fis.close();
        } catch (IOException e) {
        }
      }
      if (fos != null) {
        try {
          fos.close();
        } catch (IOException e) {
        }
      }
    }
    return checksumMatches;
  }
}
