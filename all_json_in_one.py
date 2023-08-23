import json
import os

new_json = dict()
base = f"/Users/yumeng/Desktop/output_sysEdit"
new_result = f"/Users/yumeng/Desktop/output_sysEdit.json"
for f in os.listdir(base):
    path = f"{base}/{f}"
    fjson = json.load(open(path, 'r'))
    for k,v in fjson.items():
        new_json[k] = v
data = json.dumps(new_json, indent=4)
with open(new_result, 'w') as f:
    f.write(data)
    f.close()
